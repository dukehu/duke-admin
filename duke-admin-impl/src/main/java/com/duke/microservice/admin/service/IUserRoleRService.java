package com.duke.microservice.admin.service;

import java.util.List;

public interface IUserRoleRService {

    /**
     * 根据角色id删除用户角色关系数据
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(String roleId);

    /**
     * 批量保存角色用户关系数据
     *
     * @param roleId  角色id
     * @param userIds 用户id集合
     */
    void batchSave(String roleId, List<String> userIds);
}
