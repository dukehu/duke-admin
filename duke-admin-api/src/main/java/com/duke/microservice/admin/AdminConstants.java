package com.duke.microservice.admin;

/**
 * Created duke on 2018/6/23
 */
public class AdminConstants {

    public static final String SERVICE_ID = "duke-admin";

    /**
     * 用户状态
     */
    public enum USER_STATUS {
        LOGOUT(0, "注销"),
        NORMAL(1, "正常"),
        LOCK(2, "锁定");
        private Integer statusCode;
        private String statusDesc;

        USER_STATUS(Integer statusCode, String statusDesc) {
            this.statusCode = statusCode;
            this.statusDesc = statusDesc;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public String getStatusDesc() {
            return statusDesc;
        }
    }

    /**
     * 角色状态
     */
    public enum ROLE_STATUS {
        ACTIVE(1, "激活"),
        INACTIVE(0, "未激活");

        private Integer statusCode;
        private String statusDesc;

        ROLE_STATUS(Integer statusCode, String statusDesc) {
            this.statusCode = statusCode;
            this.statusDesc = statusDesc;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public String getStatusDesc() {
            return statusDesc;
        }
    }
}
