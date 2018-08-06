package com.duke.microservice.admin.service;

import com.duke.framework.exception.BusinessException;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.domain.basic.Role;
import com.duke.microservice.admin.mapper.basic.RoleMapper;
import com.duke.microservice.admin.mapper.extend.RoleExtendMapper;
import com.duke.microservice.admin.vm.RoleDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleExtendMapper roleExtendMapper;


    /**
     * 新增
     *
     * @param roleVM 新增vm
     */
    public void save(RoleDetailVM roleVM) {
    }

    /**
     * 修改
     *
     * @param id     主键
     * @param roleVM 修改vm
     */
    public void update(String id, RoleDetailVM roleVM) {
    }

    /**
     * 删除
     *
     * @param id 主键
     */
    public void delete(String id) {
    }

    /**
     * 角色列表
     *
     * @return List<RoleDetailVM>
     */
    @Transactional(readOnly = true)
    public List<RoleDetailVM> select() {
        List<RoleDetailVM> roleDetailVMS = new ArrayList<>();
        List<Role> roles = roleMapper.selectAll();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> roleDetailVMS.add(buildRoleDetail(role)));
        }
        return roleDetailVMS;
    }

    /**
     * 角色详情
     *
     * @param id 主键
     * @return RoleDetailVM
     */
    @Transactional(readOnly = true)
    public RoleDetailVM selectById(String id) {
        Role role = this.exit(id);
        return buildRoleDetail(role);
    }

    /**
     * 构建角色详情
     *
     * @param role 角色对象
     * @return RoleDetailVM
     */
    private RoleDetailVM buildRoleDetail(Role role) {
        return new RoleDetailVM(role.getId(), role.getName(), role.getStatus(), role.getMemo());
    }

    /**
     * 校验角色id有效性
     *
     * @param id 角色id
     * @return Role
     */
    private Role exit(String id) {
        ValidationUtils.notEmpty(id, "role", "角色id不能为空！");
        Role role = roleMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(role)) {
            throw new BusinessException("id为：" + id + "的角色不存在！");
        }
        return role;
    }
}
