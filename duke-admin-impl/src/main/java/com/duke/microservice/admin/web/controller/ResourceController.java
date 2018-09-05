package com.duke.microservice.admin.web.controller;

import com.duke.framework.CoreConstants;
import com.duke.framework.web.Response;
import com.duke.microservice.admin.api.ResourceRestService;
import com.duke.microservice.admin.service.ResourceService;
import com.duke.microservice.admin.vm.ResourceDetailVM;
import com.duke.microservice.admin.vm.ResourceSetVM;
import com.duke.microservice.admin.vm.ResourceTreeVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "资源接口文档")
@RestController
public class ResourceController implements ResourceRestService {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "新增资源", notes = "新增资源")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_save')")
    @Override
    public Response<String> save(ResourceSetVM resourceSaveVM) {
        resourceService.saveOrUpdate(CoreConstants.SAVE, null, resourceSaveVM);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "删除资源", notes = "删除资源")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_delete')")
    @Override
    public Response<String> delete(@PathVariable(value = "id", required = false) String id) {
        resourceService.delete(id);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "修改资源", notes = "修改资源")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_update')")
    @Override
    public Response<String> update(@PathVariable(value = "id", required = false) String id, ResourceSetVM resourceSaveVM) {
        resourceService.saveOrUpdate(CoreConstants.UPDATE, id, resourceSaveVM);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "资源详情", notes = "资源详情")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_detail')")
    @Override
    public Response<ResourceDetailVM> select(@PathVariable(value = "id", required = false) String id) {
        return Response.ok(resourceService.select(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "模块id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "string", paramType = "query")
            })
    @ApiOperation(value = "资源树", notes = "资源树")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_resourceTree')")
    @Override
    public Response<List<ResourceTreeVM>> resourceTree(String parentId, String roleId) {
        return Response.ok(resourceService.resourceTree(parentId, roleId));
    }
}
