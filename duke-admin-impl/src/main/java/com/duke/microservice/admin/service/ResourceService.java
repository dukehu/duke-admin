package com.duke.microservice.admin.service;

import com.duke.framework.CoreConstants;
import com.duke.framework.exception.BusinessException;
import com.duke.framework.security.AuthUserDetails;
import com.duke.framework.utils.SecurityUtils;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.domain.basic.Resource;
import com.duke.microservice.admin.mapper.basic.ResourceMapper;
import com.duke.microservice.admin.mapper.extend.ResourceExtendMapper;
import com.duke.microservice.admin.vm.AuthTreeVM;
import com.duke.microservice.admin.vm.ResourceDetailVM;
import com.duke.microservice.admin.vm.ResourceSetVM;
import com.duke.microservice.admin.vm.ResourceTreeVM;
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
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceExtendMapper resourceExtendMapper;

    @Autowired
    private ResourceOperationCodeRService resourceOperationCodeRService;

    /**
     * 添加模块
     *
     * @param name   模块名称
     * @param code   模块code
     * @param router 前端路由
     * @param sort   模块序号
     * @param memo   模块描述
     */
    public void saveModule(String name, String code, String router, Integer sort, String memo) {
        ValidationUtils.notEmpty(name, "moduleName", "模块名称不能为空！");
        ValidationUtils.notEmpty(code, "moduleName", "模块名称不能为空！");
        ValidationUtils.notEmpty(router, "moduleName", "模块名称不能为空！");
        ValidationUtils.notEmpty(sort, "moduleName", "模块名称不能为空！");

        Date date = new Date();
        AuthUserDetails userDetails = SecurityUtils.getCurrentUserInfo();
        String userId = userDetails.getUserId();
        Resource resource = new Resource();
        resource.setId(code);
        resource.setParentId("");
        resource.setName(name);
        resource.setCode(code);
        resource.setType(AdminConstants.RESOURCE_TYPE.MODULE.getType());
        resource.setIcon("");
        resource.setSort(sort);
        // todo
        resource.setStatus(1);
        resource.setRouter(router);
        resource.setMemo(ObjectUtils.isEmpty(memo) ? "" : memo);
        resource.setPathTree("/" + code + "/");
        resource.setCreater(userId);
        resource.setCreateTime(date);
        resource.setModifier(userId);
        resource.setModifyTime(date);
        resourceMapper.insert(resource);
    }

    /**
     * 新增/修改
     *
     * @param type save/update
     * @param id   主键，update必传
     * @param vm   新增修改vm
     */
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

    /**
     * 删除资源
     *
     * @param id 主键
     */
    public void delete(String id) {
        this.exist(id);
        resourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 资源详情
     *
     * @param id 主键
     * @return ResourceDetailVM
     */
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

    /**
     * 资源树
     *
     * @param parentId 父节点id
     * @return List<ResourceTreeVM>
     */
    @Transactional(readOnly = true)
    public List<ResourceTreeVM> resourceTree(String parentId) {
        ValidationUtils.notEmpty(parentId, "moduleId", "父节点id不能为空！");

        List<ResourceTreeVM> resourceTreeVMS = new ArrayList<>();
        List<Resource> resources = resourceExtendMapper.selectByPathTreeLikeParentId(parentId);
        if (!CollectionUtils.isEmpty(resources)) {
            resources.forEach(resource -> {
                ResourceTreeVM resourceTreeVM = new ResourceTreeVM(
                        resource.getId(), resource.getParentId(), resource.getName(), null
                );
                resourceTreeVMS.add(resourceTreeVM);
            });
            return this.buildTree(resourceTreeVMS, parentId);
        }

        return resourceTreeVMS;
    }

    private List<ResourceTreeVM> buildTree(List<ResourceTreeVM> treeNodes, String rootId) {
        List<ResourceTreeVM> resourceTreeVMS = new ArrayList<>();
        treeNodes.forEach(treeNode -> {
            if (rootId.equals(treeNode.getKey())) {
                resourceTreeVMS.add(findChildren(treeNode, treeNodes));
            }
        });
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

    /**
     * 校验资源主键
     *
     * @param id 主键
     * @return Resource
     */
    @Transactional(readOnly = true)
    public Resource exist(String id) {
        ValidationUtils.notEmpty(id, "id", "主键不能为空！");
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(resource)) {
            throw new BusinessException("id为" + id + "的资源不存在！");
        }
        return resource;
    }
}
