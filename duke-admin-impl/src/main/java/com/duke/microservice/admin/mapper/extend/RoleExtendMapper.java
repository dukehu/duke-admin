package com.duke.microservice.admin.mapper.extend;

import com.duke.microservice.admin.domain.basic.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Mapper
public interface RoleExtendMapper {

    /**
     * 根据角色名称模糊查找
     *
     * @param name 角色名称
     * @return List<Role>
     */
    List<Role> selectByName(@Param("name") String name);
}
