package com.duke.microservice.admin.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created duke on 2018/9/1
 */
@Setter
@Getter
@ApiModel(description = "模块设置VM")
public class ModuleSetVM {

    @ApiModelProperty(value = "模块名称", required = true)
    @NotBlank(message = "模块名称不能为空！")
    @Length(max = 50, min = 1, message = "模块名称长度应为1-50之间！")
    private String name;

    @ApiModelProperty(value = "模块code", required = true)
    @NotBlank(message = "模块code不能为空！")
    @Length(max = 50, min = 1, message = "模块code长度应为1-50之间！")
    private String code;

    @ApiModelProperty(value = "前端路由", required = true)
    @NotBlank(message = "前端路由不能为空！")
    @Length(max = 50, min = 1, message = "前端路由长度应为1-50之间！")
    private String router;


    @ApiModelProperty(value = "模块序号", required = true)
    @NotNull(message = "模块序号不能为空！")
    private Integer sort;

    @ApiModelProperty(value = "模块描述")
    @Length(max = 50, message = "模块描述长度应为0-50之间！")
    private String memo;
}
