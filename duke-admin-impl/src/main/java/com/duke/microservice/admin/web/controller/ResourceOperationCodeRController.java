package com.duke.microservice.admin.web.controller;

import com.duke.microservice.admin.api.ResourceOperationCodeRRestService;
import com.duke.microservice.admin.service.ResourceOperationCodeRService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "资源操作码接口文档")
@RestController
public class ResourceOperationCodeRController implements ResourceOperationCodeRRestService {

    @Autowired
    private ResourceOperationCodeRService resourceOperationCodeRService;

}
