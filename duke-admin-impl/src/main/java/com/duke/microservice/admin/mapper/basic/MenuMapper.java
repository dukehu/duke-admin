package com.duke.microservice.admin.mapper.basic;

import com.duke.microservice.admin.domain.basic.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    Menu selectByPrimaryKey(String id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);
}