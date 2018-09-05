package com.duke.microservice.admin.service;

import com.duke.microservice.admin.domain.basic.RoleResourceR;
import com.duke.microservice.admin.mapper.basic.RoleResourceRMapper;
import com.duke.microservice.admin.mapper.extend.RoleResourceRExtendMapper;
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
public class RoleResourceRService {

    @Autowired
    private RoleResourceRMapper roleResourceRMapper;

    @Autowired
    private RoleResourceRExtendMapper roleResourceRExtendMapper;

    @Autowired
    private RoleService roleService;

    /**
     * 根据角色id删除角色资源关系数据
     *
     * @param roleId 角色id
     */
    public void deleteByRoleId(String roleId) {
        roleService.exit(roleId);
        roleResourceRExtendMapper.deleteByRoleId(roleId);
    }

    /**
     * 根据资源id集合和角色id新增
     *
     * @param roleId      角色id
     * @param resourceIds 资源id集合
     */
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

    /**
     * 根据角色id查找资源id集合
     *
     * @param roleId 角色id
     * @return List<String>
     */
    @Transactional(readOnly = true)
    public List<String> selectByRoleId(String roleId) {
        roleService.exit(roleId);
        return roleResourceRExtendMapper.selectByRoleId(roleId);
    }
}
