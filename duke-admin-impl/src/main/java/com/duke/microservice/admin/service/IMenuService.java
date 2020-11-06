package com.duke.microservice.admin.service;

import com.duke.microservice.admin.vm.MenuVM;

import java.util.List;

public interface IMenuService {

    /**
     * 查询菜单
     *
     * @return list
     */
    List<MenuVM> selectMenus();

}
