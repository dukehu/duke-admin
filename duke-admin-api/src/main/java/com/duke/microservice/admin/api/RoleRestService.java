package com.duke.microservice.admin.api;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.vm.RoleDetailVM;
import com.duke.microservice.admin.vm.RoleSetVM;
import com.github.pagehelper.PageInfo;
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
     * @param roleSaveVM 角色新增vm
     * @return String
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    Response<String> save(@RequestParam(value = "roleSaveVM", required = false) RoleSetVM roleSaveVM);

    /**
     * 修改角色
     *
     * @param id           主键
     * @param roleUpdateVM 角色修改vm
     * @return String
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    Response<String> update(@PathVariable(value = "id", required = false) String id,
                            @RequestParam(value = "roleUpdateVM", required = false) RoleSetVM roleUpdateVM);

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
     * @param name 角色名称
     * @param page 当前页码
     * @param size 每页条数
     * @return List<RoleDetailVM>
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    Response<PageInfo<RoleDetailVM>> select(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    /**
     * 角色详情
     *
     * @param id 角色主键
     * @return RoleDetailVM
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    Response<RoleDetailVM> selectById(@PathVariable(value = "id", required = false) String id);

    /**
     * 保存授权
     *
     * @param roleId      角色id
     * @param resourceIds 资源id集合
     * @return String
     */
    @RequestMapping(value = "/role/auth", method = RequestMethod.POST)
    Response<String> saveAuth(
            @RequestParam(value = "roleId", required = false) String roleId,
            @RequestParam(value = "resourceIds", required = false) List<String> resourceIds);

}
