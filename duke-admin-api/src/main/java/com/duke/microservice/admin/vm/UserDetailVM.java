package com.duke.microservice.admin.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created duke on 2018/8/4
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailVM {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 头像
     */
    private String avatar;
}
