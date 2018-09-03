package com.duke.microservice.admin.api;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.vm.ResourceDetailVM;
import com.duke.microservice.admin.vm.ResourceSetVM;
import com.duke.microservice.admin.vm.ResourceTreeVM;
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
public interface ResourceRestService {

    /**
     * 新增资源
     *
     * @param resourceSaveVM 资源新增vm
     * @return String
     */
    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    Response<String> save(@RequestParam(value = "resourceSaveVM", required = false) ResourceSetVM resourceSaveVM);

    /**
     * 删除资源
     *
     * @param id 主键
     * @return String
     */
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
    Response<String> delete(@PathVariable(value = "id", required = false) String id);

    /**
     * 修改资源
     *
     * @param id             主键
     * @param resourceSaveVM 资源修改vm
     * @return String
     */
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.POST)
    Response<String> update(
            @PathVariable(value = "id", required = false) String id,
            @RequestParam(value = "resourceSaveVM", required = false) ResourceSetVM resourceSaveVM);

    /**
     * 资源详情
     *
     * @param id 主键
     * @return ResourceDetailVM
     */
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
    Response<ResourceDetailVM> select(@PathVariable(value = "id", required = false) String id);

    /**
     * 资源树
     *
     * @param parentId 模块id
     * @return List<ResourceTreeVM>
     */
    @RequestMapping(value = "/resource/tree", method = RequestMethod.GET)
    Response<List<ResourceTreeVM>> resourceTree(@RequestParam(value = "parentId", required = false) String parentId);
}
