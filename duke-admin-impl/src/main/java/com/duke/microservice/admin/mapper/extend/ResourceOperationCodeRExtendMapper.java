package com.duke.microservice.admin.mapper.extend;

import com.duke.microservice.admin.domain.basic.ResourceOperationCodeR;
import com.duke.microservice.admin.domain.extend.ResourceOperationCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Mapper
public interface ResourceOperationCodeRExtendMapper {

    /**
     * 根据资源查找操作码
     *
     * @param resourceId 资源id
     * @return List<ResourceOperationCode>
     */
    List<ResourceOperationCode> selectByResourceId(@Param("resourceId") String resourceId);

    /**
     * 根据资源id和操作码id查找
     *
     * @param resourceId      资源id
     * @param operationCodeId 操作码id
     * @return ResourceOperationCodeR
     */
    ResourceOperationCodeR selectByResourceIdAndOperationCodeId(@Param("resourceId") String resourceId,
                                                                @Param("operationCodeId") String operationCodeId);

    /**
     * 根据资源id和操作码
     *
     * @param resourceId       资源id
     * @param operationCodeIds 操作id集合
     */
    void deleteByResourceIdAndOperationCodeIds(@Param("resourceId") String resourceId,
                                               @Param("operationCodeIds") List<String> operationCodeIds);
}
