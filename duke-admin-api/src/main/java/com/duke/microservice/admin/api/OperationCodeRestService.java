package com.duke.microservice.admin.api;

import com.duke.framework.web.Response;
import com.duke.microservice.admin.AdminConstants;
import com.duke.microservice.admin.vm.AuthTreeVM;
import com.duke.microservice.admin.vm.OperationCodeDetailVM;
import com.duke.microservice.admin.vm.OperationCodeSetVM;
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
public interface OperationCodeRestService {

    /**
     * 添加操作码
     *
     * @param operationCodeSaveVM 操作码新增vm
     * @return String
     */
    @RequestMapping(value = "/operation_code", method = RequestMethod.POST)
    Response<String> save(@RequestParam(value = "operationCodeSaveVM", required = false) OperationCodeSetVM operationCodeSaveVM);

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return String
     */
    @RequestMapping(value = "/operation_code/{id}", method = RequestMethod.DELETE)
    Response<String> delete(@PathVariable(value = "id", required = false) String id);

    /**
     * 修改操作码
     *
     * @param id                    主键
     * @param operationCodeUpdateVM 操作码修改vm
     * @return String
     */
    @RequestMapping(value = "/operation_code/{id}", method = RequestMethod.PUT)
    Response<String> update(
            @PathVariable(value = "id", required = false) String id,
            @RequestParam(value = "operationCodeSaveVM", required = false) OperationCodeSetVM operationCodeUpdateVM);

    /**
     * 操作码列表
     *
     * @param serviceId  服务
     * @param controller controller
     * @param page       起始页
     * @param size       每页条数
     * @return PageInfo
     */
    @RequestMapping(value = "/operation_code", method = RequestMethod.GET)
    Response<PageInfo<OperationCodeDetailVM>> select(
            @RequestParam(value = "serviceId", required = false) String serviceId,
            @RequestParam(value = "controller", required = false) String controller,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    /**
     * 根据主键查找
     *
     * @param id 主键
     * @return OperationCodeDetailVM
     */
    @RequestMapping(value = "/operation_code/{id}", method = RequestMethod.GET)
    Response<OperationCodeDetailVM> select(@PathVariable(value = "id", required = false) String id);

    /**
     * 根据url和请求方式查找
     *
     * @param requestMethod 请求方式
     * @param url           url
     *                           * @param page       起始页
     *      * @param size       每页条数
     * @return List<OperationCodeDetailVM>
     */
    @RequestMapping(value = "/operation_code/request_method_url", method = RequestMethod.GET)
    Response<PageInfo<OperationCodeDetailVM>> selectByRequestMethodAndUrl(
            @RequestParam(value = "requestMethod", required = false) String requestMethod,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    /**
     * 操作码controller集合
     *
     * @param serviceId 服务id
     * @return List<String>
     */
    @RequestMapping(value = "/operation_code/controller/{serviceId}", method = RequestMethod.GET)
    Response<List<String>> controller(@PathVariable(value = "serviceId", required = false) String serviceId);
}
