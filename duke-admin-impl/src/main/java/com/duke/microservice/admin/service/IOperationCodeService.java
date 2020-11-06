package com.duke.microservice.admin.service;

import com.duke.microservice.admin.domain.basic.OperationCode;
import com.duke.microservice.admin.vm.OperationCodeDetailVM;
import com.duke.microservice.admin.vm.OperationCodeSetVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOperationCodeService {

    /**
     * 添加操作码
     *
     * @param type 类型，save/update
     * @param id   主键，update时必传
     * @param vm   操作码新增vm
     */
    void saveOrUpdate(String type, String id, OperationCodeSetVM vm);

    /**
     * 根据主键删除
     *
     * @param id 主键
     */
    void delete(String id);

    /**
     * 操作码列表
     *
     * @param serviceId  服务
     * @param controller controller
     * @param page       起始页
     * @param size       每页条数
     * @return PageInfo
     */
    PageInfo<OperationCodeDetailVM> select(String serviceId, String controller, Integer page, Integer size);

    /**
     * 根据主键查找
     *
     * @param id 主键
     * @return OperationCodeDetailVM
     */
    OperationCodeDetailVM selectById(String id);

    /**
     * 根据url和请求方式查找
     *
     * @param requestMethod 请求方式
     * @param url           url
     * @return List<OperationCodeDetailVM>
     */
    PageInfo<OperationCodeDetailVM> selectByRequestMethodAndUrl(String requestMethod, String url, Integer page, Integer size);

    /**
     * 操作码controller集合
     *
     * @param serviceId 服务id
     * @return List<String>
     */
    List<String> controllerList(String serviceId);

    /**
     * 校验主键有效性
     *
     * @param id 主键
     * @return OperationCode
     */
    OperationCode exist(String id);

    /**
     * 校验一批操作码id是否合法
     *
     * @param ids 操作码id集合
     * @return List<OperationCode>
     */
    List<OperationCode> exist(List<String> ids);
}
