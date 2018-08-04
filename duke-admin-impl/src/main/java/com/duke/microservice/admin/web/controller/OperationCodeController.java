package com.duke.microservice.admin.web.controller;

import com.duke.microservice.admin.api.OperationCodeRestService;
import com.duke.microservice.admin.service.OperationCodeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "操作码接口文档")
@RestController
public class OperationCodeController implements OperationCodeRestService {

    @Autowired
    private OperationCodeService operationCodeService;
}
