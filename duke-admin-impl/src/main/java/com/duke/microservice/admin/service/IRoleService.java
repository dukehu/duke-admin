package com.duke.microservice.admin.service;

import com.duke.microservice.admin.domain.basic.Role;
import com.duke.microservice.admin.vm.RoleDetailVM;
import com.duke.microservice.admin.vm.RoleSetVM;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoleService {

    /**
     * 新增/修改
     *
     * @param type save/update
     * @param id   主键
     * @param vm   角色设置vm
     */
    void saveOrUpdate(String type, String id, RoleSetVM vm);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(String id);

    /**
     * 角色列表
     *
     * @param name 角色名称
     * @param page 当前页码
     * @param size 每页条数
     * @return List<RoleDetailVM>
     */
    PageInfo<RoleDetailVM> select(String name, Integer page, Integer size);

    /**
     * 角色详情
     *
     * @param id 主键
     * @return RoleDetailVM
     */
    RoleDetailVM selectById(String id);

    /**
     * 校验角色id有效性
     *
     * @param id 角色id
     * @return Role
     */
    Role exit(String id);

    /**
     * 保存授权
     *
     * @param roleId      角色id
     * @param resourceIds 资源id集合
     */
    void saveAuth(String roleId, List<String> resourceIds);
}
