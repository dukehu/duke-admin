package com.duke.microservice.admin.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created duke on 2018/8/30
 */

@Setter
@Getter
@AllArgsConstructor
public class OperationCodeDetailVM {

    private String id;

    private String serviceId;

    private String name;

    private String code;

    private String requestMethod;

    private String url;

    private String controller;

    private String memo;
}
