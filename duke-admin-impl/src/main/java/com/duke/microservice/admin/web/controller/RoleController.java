package com.duke.microservice.admin.web.controller;

import com.duke.framework.CoreConstants;
import com.duke.framework.web.Response;
import com.duke.microservice.admin.api.RoleRestService;
import com.duke.microservice.admin.service.RoleService;
import com.duke.microservice.admin.vm.RoleDetailVM;
import com.duke.microservice.admin.vm.RoleSetVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "角色接口文档")
@RestController
public class RoleController implements RoleRestService {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_role_save')")
    @Override
    public Response<String> save(RoleSetVM roleSaveVM) {
        roleService.saveOrUpdate(CoreConstants.SAVE, null, roleSaveVM);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_role_update')")
    @Override
    public Response<String> update(@PathVariable(value = "id", required = false) String id,
                                   RoleSetVM roleUpdateVM) {
        roleService.saveOrUpdate(CoreConstants.UPDATE, id, roleUpdateVM);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_role_delete')")
    @Override
    public Response<String> delete(@PathVariable(value = "id", required = false) String id) {
        roleService.delete(id);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页条数", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "角色列表", notes = "角色列表")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_role_select')")
    @Override
    public Response<PageInfo<RoleDetailVM>> select(String name, Integer page, Integer size) {
        return Response.ok(roleService.select(name, page, size));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "角色详情", notes = "角色详情")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_role_selectById')")
    @Override
    public Response<RoleDetailVM> selectById(@PathVariable(value = "id", required = false) String id) {
        return Response.ok(roleService.selectById(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "string", paramType = "query", required = true)
    })
    @ApiOperation(value = "保存授权", notes = "保存授权")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_role_saveAuth')")
    @Override
    public Response<String> saveAuth(String roleId, @RequestParam(value = "resourceIds", required = false) List<String> resourceIds) {
        roleService.saveAuth(roleId, resourceIds);
        return Response.ok();
    }
}
