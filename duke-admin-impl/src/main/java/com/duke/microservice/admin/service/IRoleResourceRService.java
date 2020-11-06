package com.duke.microservice.admin.service;

import java.util.List;

public interface IRoleResourceRService {

    /**
     * 根据角色id删除角色资源关系数据
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(String roleId);

    /**
     * 根据资源id集合和角色id新增
     *
     * @param roleId      角色id
     * @param resourceIds 资源id集合
     */
    void batchSave(String roleId, List<String> resourceIds);

    /**
     * 根据角色id查找资源id集合
     *
     * @param roleId 角色id
     * @return List<String>
     */
    List<String> selectByRoleId(String roleId);
}
