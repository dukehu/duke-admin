package com.duke.microservice.admin.mapper.basic;

import com.duke.microservice.admin.domain.basic.OperationCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationCodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(OperationCode record);

    OperationCode selectByPrimaryKey(String id);

    List<OperationCode> selectAll();

    int updateByPrimaryKey(OperationCode record);
}