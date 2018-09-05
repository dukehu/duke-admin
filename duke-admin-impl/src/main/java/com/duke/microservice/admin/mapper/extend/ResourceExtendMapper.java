package com.duke.microservice.admin.mapper.extend;

import com.duke.microservice.admin.domain.basic.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Mapper
public interface ResourceExtendMapper {

    /**
     * 更具父id查找资源集合
     *
     * @param parentId 父级资源id
     * @return List<Resource>
     */
    List<Resource> selectByParentId(@Param("parentId") String parentId);

    /**
     * 查找某一个栏目下的所有资源
     *
     * @param parentId 栏目id
     * @return List<Resource>
     */
    List<Resource> selectByPathTreeLikeParentId(@Param("parentId") String parentId);

    List<Resource> selectByIds(@Param("ids") List<String> ids);
}
