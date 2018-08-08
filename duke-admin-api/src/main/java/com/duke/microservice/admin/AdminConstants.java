package com.duke.microservice.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created duke on 2018/6/23
 */
public class AdminConstants {

    public static final String SERVICE_ID = "duke-admin";

    /**
     * 用户状态
     */
    @AllArgsConstructor
    public enum USER_STATUS {
        LOGOUT(0, "注销"),
        NORMAL(1, "正常"),
        LOCK(2, "锁定");
        @Getter
        private Integer statusCode;
        @Getter
        private String statusDesc;
    }

    /**
     * 角色状态
     */
    @AllArgsConstructor
    public enum ROLE_STATUS {
        ACTIVE(1, "激活"),
        INACTIVE(0, "未激活");

        @Getter
        private Integer statusCode;
        @Getter
        private String statusDesc;
    }
}
