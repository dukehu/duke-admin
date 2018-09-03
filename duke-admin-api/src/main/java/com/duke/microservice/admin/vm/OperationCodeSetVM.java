package com.duke.microservice.admin.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created duke on 2018/8/30
 */
@ApiModel(description = "操作码设置VM")
@Getter
@Setter
public class OperationCodeSetVM {

    @ApiModelProperty(value = "服务id", required = true)
    @NotBlank(message = "服务id不能为空！")
    @Length(max = 50, min = 1, message = "服务id长度应为1-50之间！")
    private String serviceId;

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空！")
    @Length(max = 200, min = 1, message = "操作码名称长度应为1-200之间！")
    private String name;

    @ApiModelProperty(value = "操作码", required = true)
    @NotBlank(message = "操作码不能为空！")
    @Length(max = 50, min = 1, message = "操作码长度应为1-50之间！")
    private String code;

    @ApiModelProperty(value = "请求方式", required = true)
    @NotBlank(message = "请求方式不能为空！")
    @Length(max = 10, min = 1, message = "请求方式长度应为1-10之间！")
    private String requestMethod;

    @ApiModelProperty(value = "请求地址", required = true)
    @NotBlank(message = "请求地址不能为空！")
    @Length(max = 50, min = 1, message = "请求地址长度应为1-50之间！")
    private String url;

    @ApiModelProperty(value = "controller", required = true)
    @NotBlank(message = "controller不能为空！")
    @Length(max = 50, min = 1, message = "controller长度应为1-50之间！")
    private String controller;

    @ApiModelProperty(value = "描述")
    @Length(max = 200, message = "描述地址长度应为0-200之间！")
    private String memo;
}
