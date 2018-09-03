package com.duke.microservice.admin.domain.extend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created duke on 2018/9/3
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceOperationCode {

    /**
     * 操作嘛id
     */
    private String operationCodeId;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * controller
     */
    private String controller;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * url
     */
    private String path;

    /**
     * 操作码名称
     */
    private String operationName;

    /**
     * 服务id
     */
    private String serviceId;
}
