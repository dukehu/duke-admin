package com.duke.microservice.admin.web.controller;

import com.duke.microservice.admin.api.ResourceRestService;
import com.duke.microservice.admin.service.ResourceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "资源接口文档")
@RestController
public class ResourceController implements ResourceRestService {

    @Autowired
    private ResourceService resourceService;
}
