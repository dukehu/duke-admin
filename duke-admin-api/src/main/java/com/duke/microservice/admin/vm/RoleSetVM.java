package com.duke.microservice.admin.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created duke on 2018/9/4
 */
@ApiModel(description = "角色设置VM")
@Getter
@Setter
public class RoleSetVM {

    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "角色名称不能为空！")
    @Length(max = 50, min = 1, message = "角色名称长度应为1-50之间！")
    private String name;

    @ApiModelProperty(value = "用户")
    private List<String> userIds;

    @ApiModelProperty(value = "角色序号", required = true)
    @NotNull(message = "角色序号不能为空！")
    private Integer sort;

    private String memo;

    @ApiModelProperty(value = "资源")
    private List<String> resourceIds;
}
