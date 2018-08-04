package com.duke.microservice.admin.api;

import com.duke.microservice.admin.AdminConstants;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created duke on 2018/8/4
 */
@FeignClient(value = AdminConstants.SERVICE_ID)
public interface RoleRestService {
}
