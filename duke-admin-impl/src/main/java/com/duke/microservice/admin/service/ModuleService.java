package com.duke.microservice.admin.service;

import com.duke.framework.security.AuthUserDetails;
import com.duke.framework.utils.SecurityUtils;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.domain.basic.Resource;
import com.duke.microservice.admin.mapper.basic.ResourceMapper;
import com.duke.microservice.admin.mapper.extend.ResourceExtendMapper;
import com.duke.microservice.admin.vm.ModuleDetailVM;
import com.duke.microservice.admin.vm.ModuleSetVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created duke on 2018/9/1
 */
@Service
@Transactional
public class ModuleService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceExtendMapper resourceExtendMapper;

    /**
     * 添加模块
     *
     * @param type save/update
     * @param id   主键 update 时必传
     * @param vm   模块设置vm
     */
    public void saveOrUpdate(String type, String id, ModuleSetVM vm) {
        ValidationUtils.validate(vm, "moduleSetVM", "参数校验失败！");

        String code = vm.getCode();
        String memo = vm.getMemo();

        Date date = new Date();
        AuthUserDetails userDetails = SecurityUtils.getCurrentUserInfo();
        String userId = userDetails.getUserId();
        Resource resource = new Resource();
        resource.setId(code);
        resource.setParentId("");
        resource.setName(vm.getName());
        resource.setCode(code);
        resource.setType(AdminConstants.RESOURCE_TYPE.MODULE.getType());
        resource.setIcon("");
        resource.setSort(vm.getSort());
        // todo
        resource.setStatus(1);
        resource.setRouter(vm.getRouter());
        resource.setMemo(ObjectUtils.isEmpty(memo) ? "" : memo);
        resource.setPathTree("/" + code + "/");
        resource.setCreater(userId);
        resource.setCreateTime(date);
        resource.setModifier(userId);
        resource.setModifyTime(date);
        resourceMapper.insert(resource);
    }

    /**
     * 模块列表
     *
     * @return List<ModuleDetailVM>
     */
    @Transactional(readOnly = true)
    public List<ModuleDetailVM> select() {
        List<ModuleDetailVM> moduleDetailVMS = new ArrayList<>();
        List<Resource> resources = resourceExtendMapper.selectByParentId("");
        if (!CollectionUtils.isEmpty(resources)) resources.forEach(resource -> {
            moduleDetailVMS.add(buildModuleDetailVM(resource));
        });
        return moduleDetailVMS;
    }

    private ModuleDetailVM buildModuleDetailVM(Resource resource) {
        return new ModuleDetailVM(
                resource.getId(), resource.getName(), resource.getCode(),
                resource.getRouter(), resource.getMemo()
        );
    }
}
