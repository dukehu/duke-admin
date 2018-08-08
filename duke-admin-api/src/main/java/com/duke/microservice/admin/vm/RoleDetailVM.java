package com.duke.microservice.admin.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created duke on 2018/8/5
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "角色模型")
public class RoleDetailVM {

    /**
     * 主键
     */
    @ApiModelProperty(hidden = true)
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    @Length(max = 50, min = 1, message = "角色名称长度应为1-50之间！")
    private String name;

    /**
     * 状态：0：未激活，1：激活
     */
    @ApiModelProperty(value = "角色状态（0：未激活，1：激活）", required = true, allowableValues = "0,1")
    private Integer status;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @NotBlank(message = "描述不能为空")
    @Length(max = 100, min = 1, message = "角色描述长度应为1-100之间！")
    private String memo;
}
