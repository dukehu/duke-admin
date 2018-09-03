package com.duke.microservice.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created duke on 2018/6/23
 */
public class AdminConstants {

    public static final String SERVICE_ID = "duke-admin";

    /**
     * 请求方式
     */
    public static final Map<String, String> RequestMethod = new HashMap<>();

    static {
        RequestMethod.put(org.springframework.web.bind.annotation.RequestMethod.GET.name(), "查询");
        RequestMethod.put(org.springframework.web.bind.annotation.RequestMethod.PUT.name(), "修改");
        RequestMethod.put(org.springframework.web.bind.annotation.RequestMethod.DELETE.name(), "删除");
        RequestMethod.put(org.springframework.web.bind.annotation.RequestMethod.POST.name(), "新增");
    }

    /**
     * 资源类型
     */
    @AllArgsConstructor
    public enum RESOURCE_TYPE {
        MODULE(1, "模块"),
        CATEGORY(2, "栏目"),
        BUTTON(3, "按钮");

        @Getter
        private Integer type;
        @Getter
        private String name;
    }

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
