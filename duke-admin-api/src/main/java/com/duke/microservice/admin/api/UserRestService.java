package com.duke.microservice.admin.api;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.vm.UserDetailVM;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@FeignClient(value = AdminConstants.SERVICE_ID)
public interface UserRestService {

    /**
     * 用户列表查询
     *
     * @return List<UserListVM>
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    Response<List<UserDetailVM>> select();

    /**
     * 根据用户id查找
     *
     * @param id 用户id
     * @return UserDetailVM
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    Response<UserDetailVM> selectById(@PathVariable(value = "id", required = false) String id);

    /**
     * 获取当前登陆用户信息接口
     *
     * @return UserDetailVM
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    Response<UserDetailVM> userInfo();
}
