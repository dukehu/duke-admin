package com.duke.microservice.admin.web.controller;

import com.duke.microservice.admin.api.RoleRestService;
import com.duke.microservice.admin.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "角色接口文档")
@RestController
public class RoleController implements RoleRestService {

    @Autowired
    private RoleService roleService;
}
