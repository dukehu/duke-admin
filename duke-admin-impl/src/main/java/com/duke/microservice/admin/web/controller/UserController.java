package com.duke.microservice.admin.web.controller;

import com.duke.framework.security.AuthUserDetails;
import com.duke.framework.utils.SecurityUtils;
import com.duke.framework.web.Response;
import com.duke.microservice.admin.api.UserRestService;
import com.duke.microservice.admin.service.IUserService;
import com.duke.microservice.admin.vm.UserDetailVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created duke on 2018/8/4
 */
@Api(description = "用户接口文档")
@RestController
public class UserController implements UserRestService {

    @Autowired
    private IUserService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "当前页码", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页条数", dataType = "string", paramType = "query", required = true)
    })
    @ApiOperation(value = "用户列表查询", notes = "用户列表查询")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_user_select')")
    @Override
    public Response<PageInfo<UserDetailVM>> select(String keyword, Integer page, Integer size) {
        return Response.ok(userService.select(keyword, page, size));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", dataType = "string", paramType = "path", required = true)
    })
    @ApiOperation(value = "用户详情", notes = "用户详情")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_user_selectById')")
    @Override
    public Response<UserDetailVM> selectById(@PathVariable(value = "id", required = false) String id) {
        return Response.ok(userService.selectById(id));
    }

    @ApiOperation(value = "获取当前登陆用户信息接口", notes = "获取当前登陆用户信息接口")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('admin_user_userInfo')")
    public Response<UserDetailVM> userInfo() {
        AuthUserDetails authUserDetails = SecurityUtils.getCurrentUserInfo();
        UserDetailVM userDetailVM = new UserDetailVM(authUserDetails.getUserId(), authUserDetails.getGender(),
                authUserDetails.getRealName(), authUserDetails.getNickName(), authUserDetails.getLoginName(),
                null, null, null, authUserDetails.getAvatar());
        return Response.ok(userDetailVM);
    }
}
