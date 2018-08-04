package com.duke.microservice.admin.web.controller;

import com.duke.microservice.admin.api.UserRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "用户接口文档")
@RestController
public class UserController implements UserRestService {

    @Autowired
    private UserRestService userRestService;

}
