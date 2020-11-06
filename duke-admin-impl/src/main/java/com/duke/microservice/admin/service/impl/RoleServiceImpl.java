package com.duke.microservice.admin.service.impl;

import com.duke.framework.CoreConstants;
import com.duke.framework.exception.BusinessException;
import com.duke.framework.security.AuthUserDetails;
import com.duke.framework.utils.SecurityUtils;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.domain.basic.Role;
import com.duke.microservice.admin.mapper.basic.RoleMapper;
import com.duke.microservice.admin.mapper.extend.RoleExtendMapper;
import com.duke.microservice.admin.service.*;
import com.duke.microservice.admin.vm.RoleDetailVM;
import com.duke.microservice.admin.vm.RoleSetVM;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleExtendMapper roleExtendMapper;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleResourceRService roleResourceRService;

    @Autowired
    private IUserRoleRService userRoleRService;


    @Override
    public void saveOrUpdate(String type, String id, RoleSetVM vm) {
        ValidationUtils.validate(vm, "roleSetVM", "参数校验失败！");
        List<String> resourceIds = vm.getResourceIds();
        List<String> userIds = vm.getUserIds();
        if (!CollectionUtils.isEmpty(resourceIds)) {
            resourceService.exist(resourceIds);
        }
        if (!CollectionUtils.isEmpty(userIds)) {
            userService.exist(userIds);
        }

        Date date = new Date();
        AuthUserDetails userDetails = SecurityUtils.getCurrentUserInfo();
        String userId = userDetails.getUserId();
        String memo = vm.getMemo();
        Role role;
        if (CoreConstants.UPDATE.equals(type)) {
            ValidationUtils.notEmpty(id, "roleId", "角色id不能为空！");
            role = this.exit(id);
        } else {
            role = new Role();
            role.setId(UUID.randomUUID().toString());
            role.setCode(UUID.randomUUID().toString());
            role.setCreater(userId);
            role.setCreateTime(date);
        }

        role.setName(vm.getName());
        role.setStatus(AdminConstants.ROLE_STATUS.ACTIVE.getStatusCode());
        role.setRoleType(AdminConstants.ROLE_TYPE.CUSTOMIZE.getTypeCode());
        role.setMemo(ObjectUtils.isEmpty(memo) ? "" : memo);
        role.setModifier(userId);
        role.setModifyTime(date);

        String roleId = role.getId();

        if (CoreConstants.UPDATE.equals(type)) {
            // 修改
            roleResourceRService.deleteByRoleId(roleId);
            userRoleRService.deleteByRoleId(roleId);

            roleMapper.updateByPrimaryKey(role);
        } else {
            roleMapper.insert(role);
        }

        roleResourceRService.batchSave(roleId, resourceIds);
        userRoleRService.batchSave(roleId, userIds);
    }

    @Override
    public void delete(String id) {
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<RoleDetailVM> select(String name, Integer page, Integer size) {
        List<RoleDetailVM> roleDetailVMS = new Page<>();
        if (ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(size)) {
            page = 0;
            size = 10;
        }
        PageHelper.startPage(page, size);
        List<Role> roles = roleExtendMapper.selectByName(name);
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> roleDetailVMS.add(buildRoleDetail(role)));
        }
        BeanUtils.copyProperties(roles, roleDetailVMS);
        return new PageInfo<>(roleDetailVMS);
    }

    @Override
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
        return new RoleDetailVM(role.getId(), role.getName(), 99, role.getRoleType(), "胡萝卜", role.getStatus(), role.getMemo());
    }

    @Override
    @Transactional
    public Role exit(String id) {
        ValidationUtils.notEmpty(id, "role", "角色id不能为空！");
        Role role = roleMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(role)) {
            throw new BusinessException("id为：" + id + "的角色不存在！");
        }
        return role;
    }

    @Override
    public void saveAuth(String roleId, List<String> resourceIds) {
        if (!CollectionUtils.isEmpty(resourceIds)) {
            this.exit(roleId);
            resourceService.exist(resourceIds);
            roleResourceRService.deleteByRoleId(roleId);
            roleResourceRService.batchSave(roleId, resourceIds);
        }
    }
}
