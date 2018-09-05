package com.duke.microservice.admin.mapper.extend;

import com.duke.microservice.admin.domain.basic.RoleResourceR;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Mapper
public interface RoleResourceRExtendMapper {

    /**
     * 根据角色id删除
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(@Param("roleId") String roleId);

    /**
     * 批量新增
     *
     * @param roleResourceRS 数据集合
     */
    void batchSave(@Param("roleResourceRS") List<RoleResourceR> roleResourceRS);

    /**
     * 根据角色id查找资源id集合
     *
     * @param roleId 角色id
     * @return List<String>
     */
    List<String> selectByRoleId(@Param("roleId") String roleId);
}
