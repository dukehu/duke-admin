package com.duke.microservice.admin.service;

import com.duke.microservice.admin.domain.basic.UserRoleR;
import com.duke.microservice.admin.mapper.basic.UserRoleRMapper;
import com.duke.microservice.admin.mapper.extend.UserRoleRExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class UserRoleRService {

    @Autowired
    private UserRoleRMapper userRoleRMapper;

    @Autowired
    private UserRoleRExtendMapper userRoleRExtendMapper;

    @Autowired
    private RoleService roleService;

    /**
     * 根据角色id删除用户角色关系数据
     *
     * @param roleId 角色id
     */
    public void deleteByRoleId(String roleId) {
        roleService.exit(roleId);
        userRoleRExtendMapper.deleteByRoleId(roleId);
    }

    /**
     * 批量保存角色用户关系数据
     *
     * @param roleId  角色id
     * @param userIds 用户id集合
     */
    public void batchSave(String roleId, List<String> userIds) {
        if (!CollectionUtils.isEmpty(userIds)) {
            roleService.exit(roleId);
            List<UserRoleR> userRoleRS = new ArrayList<>();
            userIds.forEach(userId -> {
                UserRoleR userRoleR = new UserRoleR();
                userRoleR.setId(UUID.randomUUID().toString());
                userRoleR.setRoleId(roleId);
                userRoleR.setUserId(userId);
                userRoleRS.add(userRoleR);
            });

            userRoleRExtendMapper.batchSave(userRoleRS);
        }
    }
}
