package com.duke.microservice.admin.vm;

/**
 * Created duke on 2018/8/4
 */
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

    public UserDetailVM() {
    }

    public UserDetailVM(String id, Integer gender, String realName, String nickName, String loginName, String mobile, String email, Integer status, String avatar) {
        this.id = id;
        this.gender = gender;
        this.realName = realName;
        this.nickName = nickName;
        this.loginName = loginName;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
