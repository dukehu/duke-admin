package com.duke.microservice.admin.service;

import com.duke.microservice.admin.mapper.basic.UserMapper;
import com.duke.microservice.admin.mapper.extend.UserExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtendMapper userExtendMapper;
}
