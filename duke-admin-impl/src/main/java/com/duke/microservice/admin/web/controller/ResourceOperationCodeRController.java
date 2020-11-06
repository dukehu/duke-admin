package com.duke.microservice.admin.web.controller;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.api.ResourceOperationCodeRRestService;
import com.duke.microservice.admin.service.IResourceOperationCodeRService;
import com.duke.microservice.admin.vm.AuthTreeVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "资源操作码接口文档")
@RestController
public class ResourceOperationCodeRController implements ResourceOperationCodeRRestService {

    @Autowired
    private IResourceOperationCodeRService resourceOperationCodeRService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "资源id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "operationCodeId", value = "操作码id", dataType = "string", paramType = "query", required = true)
    })
    @ApiOperation(value = "保存资源与操作码的关系", notes = "保存资源与操作码的关系")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_operationcode_save')")
    @Override
    public Response<String> save(String resourceId, String operationCodeId) {
        resourceOperationCodeRService.save(resourceId, operationCodeId);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "资源id", dataType = "string", paramType = "query", required = true)
    })
    @ApiOperation(value = "取消授权", notes = "取消授权")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_operationcode_cancelAuthorize')")
    @Override
    public Response<String> cancelAuthorize(String resourceId,
                                            @RequestParam(value = "operationCodeIds", required = false) List<String> operationCodeIds) {
        resourceOperationCodeRService.cancelAuthorize(resourceId, operationCodeIds);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "资源id", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "查询某一个资源有哪些接口的权限", notes = "查询某一个资源有哪些接口的权限")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_operationcode_selectAuthTreeByRId')")
    @Override
    public Response<List<AuthTreeVM>> selectAuthTreeByResourceId(String resourceId) {
        return Response.ok(resourceOperationCodeRService.authTree(resourceId));
    }
}
