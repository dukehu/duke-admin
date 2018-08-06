package com.duke.microservice.admin.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created duke on 2018/8/5
 */
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

    public RoleDetailVM() {
    }

    public RoleDetailVM(String id, String name, Integer status, String memo) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.memo = memo;
    }

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @NotBlank(message = "描述不能为空")
    @Length(max = 100, min = 1, message = "角色描述长度应为1-100之间！")


    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
