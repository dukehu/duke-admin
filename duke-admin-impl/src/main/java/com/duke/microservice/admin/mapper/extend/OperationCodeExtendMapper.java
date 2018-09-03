package com.duke.microservice.admin.mapper.extend;

import com.duke.microservice.admin.domain.basic.OperationCode;
import com.duke.microservice.admin.domain.extend.ResourceOperationCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Mapper
public interface OperationCodeExtendMapper {

    /**
     * 操作码列表
     *
     * @param serviceId  服务id
     * @param controller controller
     * @return List<OperationCode>
     */
    List<OperationCode> selectByServiceIdAndController(@Param("serviceId") String serviceId, @Param("controller") String controller);

    /**
     * 根据url和请求方式查找
     *
     * @param requestMethod 请求方式
     * @param url           url
     * @return List<OperationCode>
     */
    List<OperationCode> selectByRequestMethodAndUrl(@Param("requestMethod") String requestMethod, @Param("url") String url);


    /**
     * 操作码controller集合
     *
     * @param serviceId 服务id
     * @return List<String>
     */
    List<String> controllerList(@Param("serviceId") String serviceId);

    /**
     * 根据id集合查找
     *
     * @param ids 操作码id集合
     * @return List<OperationCode>
     */
    List<OperationCode> selectByIds(@Param("ids") List<String> ids);
}
