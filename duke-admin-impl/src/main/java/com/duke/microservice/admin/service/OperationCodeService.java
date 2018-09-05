package com.duke.microservice.admin.service;

import com.duke.framework.CoreConstants;
import com.duke.framework.exception.BusinessException;
import com.duke.framework.security.AuthUserDetails;
import com.duke.framework.utils.SecurityUtils;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.domain.basic.OperationCode;
import com.duke.microservice.admin.domain.extend.ResourceOperationCode;
import com.duke.microservice.admin.mapper.basic.OperationCodeMapper;
import com.duke.microservice.admin.mapper.extend.OperationCodeExtendMapper;
import com.duke.microservice.admin.vm.AuthTreeVM;
import com.duke.microservice.admin.vm.OperationCodeDetailVM;
import com.duke.microservice.admin.vm.OperationCodeSetVM;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class OperationCodeService {

    @Autowired
    private OperationCodeMapper operationCodeMapper;

    @Autowired
    private OperationCodeExtendMapper operationCodeExtendMapper;

    @Autowired
    private ResourceService resourceService;

    /**
     * 添加操作码
     *
     * @param type 类型，save/update
     * @param id   主键，update时必传
     * @param vm   操作码新增vm
     */
    public void saveOrUpdate(String type, String id, OperationCodeSetVM vm) {
        ValidationUtils.validate(vm, "operationCodeSaveVM", "参数校验失败！");
        // 服务id
        String serviceId = vm.getServiceId();
        // 请求方式
        String requestMethod = vm.getRequestMethod();
        String memo = vm.getMemo();
        // todo 校验服务id

        // 校验请求方式
        if (!AdminConstants.RequestMethod.containsKey(requestMethod)) {
            throw new BusinessException("错误的请求方式！");
        }

        // 当前登陆用户信息
        AuthUserDetails userDetails = SecurityUtils.getCurrentUserInfo();
        String userId = userDetails.getUserId();
        Date date = new Date();
        OperationCode operationCode;
        if (CoreConstants.UPDATE.equals(type)) {
            ValidationUtils.notEmpty(id, "operationCodeId", "操作码id不能为空！");
            // 修改
            operationCode = this.exist(id);
        } else {
            // 新增
            operationCode = new OperationCode();
            operationCode.setId(UUID.randomUUID().toString());
            operationCode.setCreaterTime(date);
            operationCode.setCreater(userId);
        }

        operationCode.setServiceId(serviceId);
        operationCode.setName(vm.getName());
        operationCode.setCode(vm.getCode());
        operationCode.setMemo(ObjectUtils.isEmpty(memo) ? "" : memo);
        operationCode.setPath(vm.getUrl());
        operationCode.setController(vm.getController());
        operationCode.setRequestMethod(vm.getRequestMethod());
        operationCode.setModifier(userId);
        operationCode.setModifiedTime(date);

        if (CoreConstants.UPDATE.equals(type)) {
            // 修改
            operationCodeMapper.updateByPrimaryKey(operationCode);
        } else {
            // 新增
            operationCodeMapper.insert(operationCode);
        }
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     */
    public void delete(String id) {
        this.exist(id);
        operationCodeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 操作码列表
     *
     * @param serviceId  服务
     * @param controller controller
     * @param page       起始页
     * @param size       每页条数
     * @return PageInfo
     */
    @Transactional(readOnly = true)
    public PageInfo<OperationCodeDetailVM> select(String serviceId, String controller, Integer page, Integer size) {
        if (ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(size)) {
            page = 0;
            size = 10;
        }
        PageHelper.startPage(page, size);
        List<OperationCode> operationCodes = operationCodeExtendMapper.selectByServiceIdAndController(serviceId, controller);
        List<OperationCodeDetailVM> operationCodeDetailVMS = new Page<>();

        if (!CollectionUtils.isEmpty(operationCodes)) {
            operationCodes.forEach(operationCode -> operationCodeDetailVMS.add(buildOperationCodeDetailVM(operationCode)));
        }

        BeanUtils.copyProperties(operationCodes, operationCodeDetailVMS);
        return new PageInfo<>(operationCodeDetailVMS);
    }

    private OperationCodeDetailVM buildOperationCodeDetailVM(OperationCode operationCode) {
        return new OperationCodeDetailVM(
                operationCode.getId(), operationCode.getServiceId(), operationCode.getName(),
                operationCode.getCode(), operationCode.getRequestMethod(), operationCode.getPath(),
                operationCode.getController(), operationCode.getMemo()
        );
    }

    /**
     * 根据主键查找
     *
     * @param id 主键
     * @return OperationCodeDetailVM
     */
    @Transactional(readOnly = true)
    public OperationCodeDetailVM selectById(String id) {
        return buildOperationCodeDetailVM(this.exist(id));
    }


    /**
     * 根据url和请求方式查找
     *
     * @param requestMethod 请求方式
     * @param url           url
     * @return List<OperationCodeDetailVM>
     */
    @Transactional(readOnly = true)
    public List<OperationCodeDetailVM> selectByRequestMethodAndUrl(String requestMethod, String url) {
        if (!ObjectUtils.isEmpty(requestMethod) && !AdminConstants.RequestMethod.containsKey(requestMethod)) {
            throw new BusinessException("错误的请求方式！");
        }
        List<OperationCode> operationCodes = operationCodeExtendMapper.selectByRequestMethodAndUrl(requestMethod, url);
        List<OperationCodeDetailVM> operationCodeDetailVMS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(operationCodes)) {
            operationCodes.forEach(operationCode -> operationCodeDetailVMS.add(buildOperationCodeDetailVM(operationCode)));
        }
        return operationCodeDetailVMS;
    }


    /**
     * 操作码controller集合
     *
     * @param serviceId 服务id
     * @return List<String>
     */
    @Transactional(readOnly = true)
    public List<String> controllerList(String serviceId) {
        // todo 校验服务id
        return operationCodeExtendMapper.controllerList(serviceId);
    }


    /**
     * 校验主键有效性
     *
     * @param id 主键
     * @return OperationCode
     */
    @Transactional(readOnly = true)
    public OperationCode exist(String id) {
        ValidationUtils.notEmpty(id, "id", "主键不能为空！");
        OperationCode operationCode = operationCodeMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(operationCode)) {
            throw new BusinessException("id为" + id + "的操作码不存在！");
        }
        return operationCode;
    }

    /**
     * 校验一批操作码id是否合法
     *
     * @param ids 操作码id集合
     * @return List<OperationCode>
     */
    @Transactional(readOnly = true)
    public List<OperationCode> exist(List<String> ids) {
        ValidationUtils.notEmpty(ids, "ids", "操作码id集合不能为空！");
        List<OperationCode> operationCodes = operationCodeExtendMapper.selectByIds(ids);
        if (CollectionUtils.isEmpty(operationCodes) || (operationCodes.size() != ids.size())) {
            throw new BusinessException("存在不合法的操作码id！");
        }
        return operationCodes;
    }
}
