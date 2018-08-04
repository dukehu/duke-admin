package com.duke.microservice.admin.service;

import com.duke.microservice.admin.mapper.basic.ResourceMapper;
import com.duke.microservice.admin.mapper.extend.ResourceExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceExtendMapper resourceExtendMapper;
}
