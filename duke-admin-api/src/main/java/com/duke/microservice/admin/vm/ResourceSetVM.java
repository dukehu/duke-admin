package com.duke.microservice.admin.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created duke on 2018/8/31
 */
@ApiModel(description = "资源设置VM")
@Getter
@Setter
public class ResourceSetVM {

    @ApiModelProperty(value = "父资源id", required = true)
    @NotBlank(message = "父资源id不能为空！")
    @Length(max = 50, min = 1, message = "父资源id长度应为1-50之间！")
    private String parentId;

    @ApiModelProperty(value = "资源图标（无传'/'）", required = true)
    @NotBlank(message = "资源图标不能为空！")
    @Length(max = 50, min = 1, message = "资源图标长度应为1-50之间！")
    private String icon;

    @ApiModelProperty(value = "前端路由（无传'/'）", required = true)
    @NotBlank(message = "前端路由不能为空！")
    @Length(max = 50, min = 1, message = "前端路由长度应为1-50之间！")
    private String router;

    @ApiModelProperty(value = "序号", required = true)
    @NotNull(message = "序号不能为空！")
    private Integer sort;

    @ApiModelProperty(value = "资源名称", required = true)
    @NotBlank(message = "资源名称不能为空！")
    @Length(max = 50, min = 1, message = "资源名称长度应为1-50之间！")
    private String name;

    @ApiModelProperty(value = "资源码", required = true)
    @NotBlank(message = "资源码不能为空！")
    @Length(max = 50, min = 1, message = "资源码长度应为1-50之间！")
    private String code;

    @ApiModelProperty(value = "资源类型（0：栏目 1：按钮）", required = true, allowableValues = "0, 1")
    @NotNull(message = "资源类型不能为空！")
    private Integer type;

    @ApiModelProperty(value = "资源描述")
    @Length(max = 50, min = 0, message = "资源描述长度应为1-50之间！")
    private String memo;
}
