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
    private String name;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 角色类型
     */
    private Integer roleType;

    /**
     * 创建用户
     */
    private String creater;


    /**
     * 状态：0：未激活，1：激活
     */
    private Integer status;

    /**
     * 描述
     */
    private String memo;
}
