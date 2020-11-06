package com.duke.microservice.admin.web.controller;

import com.duke.framework.CoreConstants;
import com.duke.framework.web.Response;
import com.duke.microservice.admin.api.ModuleRestService;
import com.duke.microservice.admin.service.IModuleService;
import com.duke.microservice.admin.vm.ModuleDetailVM;
import com.duke.microservice.admin.vm.ModuleSetVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created duke on 2018/9/1
 */
@Api(description = "模块接口文档")
@RestController
public class ModuleController implements ModuleRestService {

    @Autowired
    private IModuleService moduleService;

    @ApiOperation(value = "新增模块", notes = "新增模块")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_module_save')")
    @Override
    public Response<String> save(ModuleSetVM moduleSaveVM) {
        moduleService.saveOrUpdate(CoreConstants.SAVE, null, moduleSaveVM);
        return Response.ok();
    }

    @ApiOperation(value = "模块列表", notes = "模块列表")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_module_select')")
    @Override
    public Response<List<ModuleDetailVM>> select() {
        return Response.ok(moduleService.select());
    }
}
