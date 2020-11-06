package com.duke.microservice.admin.web.controller;

import com.duke.framework.CoreConstants;
import com.duke.framework.web.Response;
import com.duke.microservice.admin.api.OperationCodeRestService;
import com.duke.microservice.admin.service.IOperationCodeService;
import com.duke.microservice.admin.vm.OperationCodeDetailVM;
import com.duke.microservice.admin.vm.OperationCodeSetVM;
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
@Api(description = "操作码接口文档")
@RestController
public class OperationCodeController implements OperationCodeRestService {

    @Autowired
    private IOperationCodeService operationCodeService;

    @ApiOperation(value = "新增操作码", notes = "新增操作码")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_operation_code_save')")
    @Override
    public Response<String> save(OperationCodeSetVM operationCodeSaveVM) {
        operationCodeService.saveOrUpdate(CoreConstants.SAVE, null, operationCodeSaveVM);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "删除操作码", notes = "删除操作码")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_operation_code_delete')")
    @Override
    public Response<String> delete(@PathVariable(value = "id", required = false) String id) {
        operationCodeService.delete(id);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "修改操作码", notes = "修改操作码")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_operation_code_update')")
    @Override
    public Response<String> update(@PathVariable(value = "id", required = false) String id,
                                   OperationCodeSetVM operationCodeUpdateVM) {
        operationCodeService.saveOrUpdate(CoreConstants.UPDATE, id, operationCodeUpdateVM);
        return Response.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceId", value = "服务id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "controller", value = "controller", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页条数", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "操作码列表", notes = "操作码列表")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_operation_code_select')")
    @Override
    public Response<PageInfo<OperationCodeDetailVM>> select(String serviceId, String controller, Integer page, Integer size) {
        return Response.ok(operationCodeService.select(serviceId, controller, page, size));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "详情", notes = "详情")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_operation_code_detail')")
    @Override
    public Response<OperationCodeDetailVM> select(@PathVariable(value = "id", required = false) String id) {
        return Response.ok(operationCodeService.selectById(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestMethod", value = "请求方式", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "url", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "根据url和请求方式查找", notes = "根据url和请求方式查找")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_operation_code_selectByRequestMethodAndUrl')")
    @Override
    public Response<PageInfo<OperationCodeDetailVM>> selectByRequestMethodAndUrl(String requestMethod, String url, Integer page,
                                                                                 Integer size) {
        return Response.ok(operationCodeService.selectByRequestMethodAndUrl(requestMethod, url, page, size));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceId", value = "服务id", dataType = "string", paramType = "path")
    })
    @ApiOperation(value = "操作码controller集合", notes = "操作码controller集合")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_resource_controller')")
    @Override
    public Response<List<String>> controller(@PathVariable(value = "serviceId", required = false) String serviceId) {
        return Response.ok(operationCodeService.controllerList(serviceId));
    }
}
