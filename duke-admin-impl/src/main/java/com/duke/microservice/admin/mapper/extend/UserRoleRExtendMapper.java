package com.duke.microservice.admin.mapper.extend;

import com.duke.microservice.admin.domain.basic.UserRoleR;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Mapper
public interface UserRoleRExtendMapper {

    /**
     * 根据角色id删除
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(@Param("roleId") String roleId);

    /**
     * 批量保存数据
     *
     * @param userRoleRS 数据集合
     */
    void batchSave(@Param("userRoleRS") List<UserRoleR> userRoleRS);
}
