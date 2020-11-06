package com.duke.microservice.admin.service.impl;

import com.duke.microservice.admin.domain.basic.RoleResourceR;
import com.duke.microservice.admin.mapper.basic.RoleResourceRMapper;
import com.duke.microservice.admin.mapper.extend.RoleResourceRExtendMapper;
import com.duke.microservice.admin.service.IRoleResourceRService;
import com.duke.microservice.admin.service.IRoleService;
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
public class RoleResourceRServiceImpl implements IRoleResourceRService {

    @Autowired
    private RoleResourceRMapper roleResourceRMapper;

    @Autowired
    private RoleResourceRExtendMapper roleResourceRExtendMapper;

    @Autowired
    private IRoleService roleService;

    @Override
    public void deleteByRoleId(String roleId) {
        roleService.exit(roleId);
        roleResourceRExtendMapper.deleteByRoleId(roleId);
    }

    @Override
    public void batchSave(String roleId, List<String> resourceIds) {
        if (!CollectionUtils.isEmpty(resourceIds)) {
            roleService.exit(roleId);
            List<RoleResourceR> roleResourceRS = new ArrayList<>();

            resourceIds.forEach(resourceId -> {
                RoleResourceR roleResourceR = new RoleResourceR();
                roleResourceR.setId(UUID.randomUUID().toString());
                roleResourceR.setRoleId(roleId);
                roleResourceR.setResourceId(resourceId);
                roleResourceRS.add(roleResourceR);
            });

            roleResourceRExtendMapper.batchSave(roleResourceRS);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> selectByRoleId(String roleId) {
        roleService.exit(roleId);
        return roleResourceRExtendMapper.selectByRoleId(roleId);
    }
}
