package com.duke.microservice.admin.api;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.vm.AuthTreeVM;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@FeignClient(value = AdminConstants.SERVICE_ID)
public interface ResourceOperationCodeRRestService {

    /**
     * 保存资源与操作码的关系
     *
     * @param resourceId      资源id
     * @param operationCodeId 操作码id
     * @return String
     */
    @RequestMapping(value = "/resource_operationcode", method = RequestMethod.POST)
    Response<String> save(@RequestParam(value = "resourceId", required = false) String resourceId,
                          @RequestParam(value = "operationCodeId", required = false) String operationCodeId);

    /**
     * 取消授权
     *
     * @param resourceId       资源id
     * @param operationCodeIds 操作码id集合
     * @return String
     */
    @RequestMapping(value = "/resource_operationcode/cancel_authorize", method = RequestMethod.POST)
    Response<String> cancelAuthorize(@RequestParam(value = "resourceId", required = false) String resourceId,
                                     @RequestParam(value = "operationCodeIds", required = false) List<String> operationCodeIds);

    /**
     * 查询某一个资源有哪些接口的权限
     *
     * @param resourceId 资源id
     * @return List<AuthTreeVM>
     */
    @RequestMapping(value = "/resource_operationcode/auth_tree", method = RequestMethod.GET)
    Response<List<AuthTreeVM>> selectAuthTreeByResourceId(@RequestParam(value = "resourceId", required = false) String resourceId);

}
