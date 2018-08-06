package com.duke.microservice.admin.mapper.extend;

import com.duke.microservice.admin.domain.basic.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Mapper
public interface UserExtendMapper {

    /**
     * 查找用户
     *
     * @return List<User>
     */
    List<User> select();
}
