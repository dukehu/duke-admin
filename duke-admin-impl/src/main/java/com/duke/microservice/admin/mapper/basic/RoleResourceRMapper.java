package com.duke.microservice.admin.mapper.basic;

import com.duke.microservice.admin.domain.basic.RoleResourceR;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleResourceRMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleResourceR record);

    RoleResourceR selectByPrimaryKey(String id);

    List<RoleResourceR> selectAll();

    int updateByPrimaryKey(RoleResourceR record);
}