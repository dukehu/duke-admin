package com.duke.microservice.admin.service.impl;

import com.duke.framework.domain.DBProperties;
import com.duke.framework.security.AuthUserDetails;
import com.duke.framework.utils.OperationCodeUtils;
import com.duke.framework.utils.SecurityUtils;
import com.duke.microservice.admin.domain.basic.Menu;
import com.duke.microservice.admin.mapper.basic.MenuMapper;
import com.duke.microservice.admin.service.IMenuService;
import com.duke.microservice.admin.vm.MenuVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVM> selectMenus() {
        AuthUserDetails userDetails = SecurityUtils.getCurrentUserInfo();
        // todo 根据当前登陆用户

        List<Menu> menus = menuMapper.selectAll();

        if (CollectionUtils.isEmpty(menus)) {

        }
        return null;
    }

    private List<Menu> buildTree(List<Menu> menus) {
        List<Menu> menuList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menus)) {

        }
        return menuList;
    }

    public static void main(String[] args) {
        DBProperties dbProperties = new DBProperties("dukehu.top:3306", "duke_auth", "root", "Duke@0417");
        try {
            OperationCodeUtils.createOperationCodeSqlFile(dbProperties, "duke-admin", com.duke.microservice.admin.web.controller.ModuleController.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
