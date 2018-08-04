package com.duke.microservice.admin.mapper.basic;

import com.duke.microservice.admin.domain.basic.ResourceOperationCodeR;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceOperationCodeRMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourceOperationCodeR record);

    ResourceOperationCodeR selectByPrimaryKey(String id);

    List<ResourceOperationCodeR> selectAll();

    int updateByPrimaryKey(ResourceOperationCodeR record);
}