package com.duke.microservice.admin.service;

import com.duke.microservice.admin.domain.basic.User;
import com.duke.microservice.admin.vm.UserDetailVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {

    /**
     * 用户列表查询
     *
     * @param keyword 关键字
     * @param page    当前页码
     * @param size    每页条数
     * @return List<UserDetailVM>
     */
    PageInfo<UserDetailVM> select(String keyword, Integer page, Integer size);

    /**
     * 根据用户id查找
     *
     * @param id 用户id
     * @return UserDetailVM
     */
    UserDetailVM selectById(String id);

    /**
     * 校验用户id有效性
     *
     * @param id 用户id
     * @return User
     */
    User exist(String id);

    /**
     * 批量校验用户id集合有效性
     *
     * @param ids 用户id集合
     */
    List<User> exist(List<String> ids);
}
