package com.duke.microservice.admin.service.impl;

import com.duke.framework.CoreConstants;
import com.duke.framework.exception.BusinessException;
import com.duke.framework.security.AuthUserDetails;
import com.duke.framework.utils.SecurityUtils;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.domain.basic.Resource;
import com.duke.microservice.admin.mapper.basic.ResourceMapper;
import com.duke.microservice.admin.mapper.extend.ResourceExtendMapper;
import com.duke.microservice.admin.service.IModuleService;
import com.duke.microservice.admin.service.IResourceOperationCodeRService;
import com.duke.microservice.admin.service.IResourceService;
import com.duke.microservice.admin.service.IRoleResourceRService;
import com.duke.microservice.admin.vm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceExtendMapper resourceExtendMapper;

    @Autowired
    private IResourceOperationCodeRService resourceOperationCodeRService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IRoleResourceRService roleResourceRService;

    @Override
    public void saveOrUpdate(String type, String id, ResourceSetVM vm) {
        ValidationUtils.validate(vm, "resourceSetVM", "参数校验失败！");

        // todo 校验父级资源
        String parentId = vm.getParentId();
        Resource parentResource = this.exist(parentId);

        // 资源类型
        Integer resourceType = vm.getType();
        String memo = vm.getMemo();

        Resource resource;
        Date date = new Date();
        AuthUserDetails userDetails = SecurityUtils.getCurrentUserInfo();
        String userId = userDetails.getUserId();
        if (CoreConstants.UPDATE.equals(type)) {
            ValidationUtils.notEmpty(id, "resourceId", "资源id不能为空！");
            // 修改
            resource = this.exist(id);
        } else {
            // 新增
            resource = new Resource();
            resource.setId(vm.getCode());
            resource.setCreater(userId);
            resource.setCreateTime(date);
        }


        resource.setParentId(parentId);
        resource.setName(vm.getName());
        resource.setCode(vm.getCode());
        resource.setType(resourceType);
        resource.setIcon(vm.getIcon());
        resource.setSort(vm.getSort());
        // todo
        resource.setStatus(1);
        resource.setRouter(vm.getRouter());
        resource.setPathTree(parentResource.getPathTree() + resource.getId() + "/");
        resource.setMemo(ObjectUtils.isEmpty(memo) ? "" : memo);
        resource.setModifier(userId);
        resource.setModifyTime(date);

        if (CoreConstants.UPDATE.equals(type)) {
            // 修改
            resourceMapper.updateByPrimaryKey(resource);
        } else {
            // 新增
            resourceMapper.insert(resource);
        }

    }

    @Override
    public void delete(String id) {
        this.exist(id);
        resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ResourceDetailVM select(String id) {
        Resource resource = this.exist(id);
        List<AuthTreeVM> authTreeVMS = resourceOperationCodeRService.authTree(id);
        return new ResourceDetailVM(
                resource.getName(), resource.getCode(),
                resource.getIcon(), resource.getRouter(),
                resource.getSort(), resource.getType(),
                resource.getMemo(), authTreeVMS
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResourceTreeVM> resourceTree(String parentId, String roleId) {
        List<ResourceTreeVM> resourceTreeVMS = new ArrayList<>();
        List<Resource> resources = resourceExtendMapper.selectByPathTreeLikeParentId(parentId);
        if (!CollectionUtils.isEmpty(resources)) {
            List<String> resourceIds = new ArrayList<>();
            if (!ObjectUtils.isEmpty(roleId)) {
                resourceIds = roleResourceRService.selectByRoleId(roleId);
            }
            List<String> finalResourceIds = resourceIds;
            resources.forEach(resource -> {
                ResourceTreeVM resourceTreeVM = new ResourceTreeVM(resource.getId(), resource.getName(), resource.getCode(),
                        resource.getId(), resource.getParentId(), resource.getName(), finalResourceIds.contains(resource.getId()), true, null
                );
                resourceTreeVMS.add(resourceTreeVM);
            });
            return this.buildTree(resourceTreeVMS, parentId);
        }

        return resourceTreeVMS;
    }

    private List<ResourceTreeVM> buildTree(List<ResourceTreeVM> treeNodes, String rootId) {
        List<ResourceTreeVM> resourceTreeVMS = new ArrayList<>();
        if (!ObjectUtils.isEmpty(rootId)) {
            treeNodes.forEach(treeNode -> {
                if (rootId.equals(treeNode.getKey())) {
                    resourceTreeVMS.add(findChildren(treeNode, treeNodes));
                }
            });
        } else {
            List<ModuleDetailVM> moduleDetailVMS = moduleService.select();
            moduleDetailVMS.forEach(moduleDetailVM -> {
                treeNodes.forEach(treeNode -> {
                    if (moduleDetailVM.getId().equals(treeNode.getKey())) {
                        resourceTreeVMS.add(findChildren(treeNode, treeNodes));
                    }
                });
            });
        }

        return resourceTreeVMS;
    }

    private ResourceTreeVM findChildren(ResourceTreeVM treeNode, List<ResourceTreeVM> treeNodes) {
        treeNodes.forEach(tmp -> {
            if (treeNode.getKey().equals(tmp.getParentId())) {
                if (CollectionUtils.isEmpty(treeNode.getChildren())) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(tmp, treeNodes));
            }
        });
        return treeNode;
    }

    @Override
    @Transactional(readOnly = true)
    public Resource exist(String id) {
        ValidationUtils.notEmpty(id, "id", "主键不能为空！");
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(resource)) {
            throw new BusinessException("id为" + id + "的资源不存在！");
        }
        return resource;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resource> exist(List<String> ids) {
        ValidationUtils.notEmpty(ids, "resourceIds", "资源id集合不能为空！");
        List<Resource> resources = resourceExtendMapper.selectByIds(ids);

        if (!CollectionUtils.isEmpty(resources) && (resources.size() != ids.size())) {
            throw new BusinessException("存在无效的或者不合法的资源id！");
        }
        return resources;
    }
}
