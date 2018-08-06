package com.duke.microservice.admin.api;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.vm.RoleDetailVM;
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
public interface RoleRestService {

    /**
     * 新增角色
     *
     * @param roleVM 角色新增vm
     * @return String
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    Response<String> save(@RequestParam(value = "roleVM", required = false) RoleDetailVM roleVM);

    /**
     * 修改角色
     *
     * @param id     主键
     * @param roleVM 角色修改vm
     * @return String
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    Response<String> update(@PathVariable(value = "id", required = false) String id,
                            @RequestParam(value = "roleVM", required = false) RoleDetailVM roleVM);

    /**
     * 删除角色
     *
     * @param id 主键
     * @return String
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    Response<String> delete(@PathVariable(value = "id", required = false) String id);

    /**
     * 角色列表
     *
     * @return List<RoleDetailVM>
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    Response<List<RoleDetailVM>> select();

    /**
     * 角色详情
     *
     * @param id 角色主键
     * @return RoleDetailVM
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    Response<RoleDetailVM> selectById(@PathVariable(value = "id", required = false) String id);

}
