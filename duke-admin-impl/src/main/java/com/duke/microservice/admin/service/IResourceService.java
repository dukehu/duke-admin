package com.duke.microservice.admin.service;

import com.duke.microservice.admin.domain.basic.Resource;
import com.duke.microservice.admin.vm.ResourceDetailVM;
import com.duke.microservice.admin.vm.ResourceSetVM;
import com.duke.microservice.admin.vm.ResourceTreeVM;

import java.util.List;

public interface IResourceService {

    /**
     * 新增/修改
     *
     * @param type save/update
     * @param id   主键，update必传
     * @param vm   新增修改vm
     */
    void saveOrUpdate(String type, String id, ResourceSetVM vm);

    /**
     * 删除资源
     *
     * @param id 主键
     */
    void delete(String id);

    /**
     * 资源详情
     *
     * @param id 主键
     * @return ResourceDetailVM
     */
    ResourceDetailVM select(String id);

    /**
     * 资源树
     *
     * @param roleId   角色id
     * @param parentId 父节点id
     * @return List<ResourceTreeVM>
     */
    List<ResourceTreeVM> resourceTree(String parentId, String roleId);

    /**
     * 校验资源主键
     *
     * @param id 主键
     * @return Resource
     */
    Resource exist(String id);

    /**
     * 批量校验资源id合法性
     *
     * @param ids 资源id集合
     */
    List<Resource> exist(List<String> ids);
}
