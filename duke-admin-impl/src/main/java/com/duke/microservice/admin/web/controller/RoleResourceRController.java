package com.duke.microservice.admin.web.controller;

import com.duke.microservice.admin.api.RoleResourceRRestService;
import com.duke.microservice.admin.service.IRoleResourceRService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "角色资源接口文档")
@RestController
public class RoleResourceRController implements RoleResourceRRestService {

    @Autowired
    private IRoleResourceRService roleResourceRService;

}
