package com.duke.microservice.admin.service;

import com.duke.microservice.admin.mapper.basic.RoleMapper;
import com.duke.microservice.admin.mapper.extend.RoleExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleExtendMapper roleExtendMapper;
}
