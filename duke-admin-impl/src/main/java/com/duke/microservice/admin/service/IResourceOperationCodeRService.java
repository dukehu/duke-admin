package com.duke.microservice.admin.service;

import com.duke.microservice.admin.vm.AuthTreeVM;

import java.util.List;

public interface IResourceOperationCodeRService {

    /**
     * 保存资源与操作码的关系
     *
     * @param resourceId      资源id
     * @param operationCodeId 操作码id
     */
    void save(String resourceId, String operationCodeId);

    /**
     * 权限树
     *
     * @param resourceId 资源id
     * @return List<AuthTreeVM>
     */
    List<AuthTreeVM> authTree(String resourceId);

    /**
     * 取消授权
     *
     * @param resourceId       资源id
     * @param operationCodeIds 操作码id集合
     */
    void cancelAuthorize(String resourceId, List<String> operationCodeIds);

}
