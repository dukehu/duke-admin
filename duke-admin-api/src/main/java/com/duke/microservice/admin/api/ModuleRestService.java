package com.duke.microservice.admin.api;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.vm.ModuleDetailVM;
import com.duke.microservice.admin.vm.ModuleSetVM;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created duke on 2018/9/1
 */
@FeignClient(value = AdminConstants.SERVICE_ID)
public interface ModuleRestService {

    /**
     * 添加模块
     *
     * @param moduleSaveVM 模块新增vm
     * @return String
     */
    @RequestMapping(value = "/module", method = RequestMethod.POST)
    Response<String> save(@RequestParam(value = "name", required = false) ModuleSetVM moduleSaveVM);

    /**
     * 模块列表
     *
     * @return List<ModuleDetailVM>
     */
    @RequestMapping(value = "/module", method = RequestMethod.GET)
    Response<List<ModuleDetailVM>> select();

}
