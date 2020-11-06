package com.duke.microservice.admin.service;

import com.duke.microservice.admin.vm.ModuleDetailVM;
import com.duke.microservice.admin.vm.ModuleSetVM;

import java.util.List;

public interface IModuleService {

    /**
     * 添加模块
     *
     * @param type save/update
     * @param id   主键 update 时必传
     * @param vm   模块设置vm
     */
    void saveOrUpdate(String type, String id, ModuleSetVM vm);

    /**
     * 模块列表
     *
     * @return List<ModuleDetailVM>
     */
    List<ModuleDetailVM> select();
}
