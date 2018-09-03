package com.duke.microservice.admin.service;

import com.duke.microservice.admin.domain.basic.ResourceOperationCodeR;
import com.duke.microservice.admin.domain.extend.ResourceOperationCode;
import com.duke.microservice.admin.mapper.basic.ResourceOperationCodeRMapper;
import com.duke.microservice.admin.mapper.extend.ResourceOperationCodeRExtendMapper;
import com.duke.microservice.admin.vm.AuthTreeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class ResourceOperationCodeRService {

    @Autowired
    private ResourceOperationCodeRMapper resourceOperationCodeRMapper;

    @Autowired
    private ResourceOperationCodeRExtendMapper resourceOperationCodeRExtendMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private OperationCodeService operationCodeService;

    /**
     * 保存资源与操作码的关系
     *
     * @param resourceId      资源id
     * @param operationCodeId 操作码id
     */
    public void save(String resourceId, String operationCodeId) {
        resourceService.exist(resourceId);
        operationCodeService.exist(operationCodeId);
        ResourceOperationCodeR resourceOperationCodeR =
                resourceOperationCodeRExtendMapper.selectByResourceIdAndOperationCodeId(resourceId, operationCodeId);
        if (ObjectUtils.isEmpty(resourceOperationCodeR)) {
            resourceOperationCodeR = new ResourceOperationCodeR();
            resourceOperationCodeR.setId(UUID.randomUUID().toString());
            resourceOperationCodeR.setResourceId(resourceId);
            resourceOperationCodeR.setOperationCodeId(operationCodeId);
            resourceOperationCodeRMapper.insert(resourceOperationCodeR);
        }
    }

    /**
     * 权限树
     *
     * @param resourceId 资源id
     * @return List<AuthTreeVM>
     */
    @Transactional(readOnly = true)
    public List<AuthTreeVM> authTree(String resourceId) {
        resourceService.exist(resourceId);
        List<AuthTreeVM> authTreeVMS = new ArrayList<>();
        List<ResourceOperationCode> resourceOperationCodes = resourceOperationCodeRExtendMapper.selectByResourceId(resourceId);
        if (!CollectionUtils.isEmpty(resourceOperationCodes)) {
            Map<String, Map<String, List<ResourceOperationCode>>> serviceIdControllerMap = new HashMap<>();
            Map<String, List<ResourceOperationCode>> serviceIdMap = resourceOperationCodes.stream().collect(Collectors.groupingBy(ResourceOperationCode::getServiceId));
            serviceIdMap.keySet().forEach(serviceId -> {
                List<ResourceOperationCode> tmpList = serviceIdMap.get(serviceId);
                Map<String, List<ResourceOperationCode>> controllerMap = tmpList.stream().collect(Collectors.groupingBy(ResourceOperationCode::getController));
                serviceIdControllerMap.put(serviceId, controllerMap);
            });

            serviceIdControllerMap.keySet().forEach(serviceId -> {
                Map<String, List<ResourceOperationCode>> controllerMap = serviceIdControllerMap.get(serviceId);
                List<AuthTreeVM> controllerList = new ArrayList<>();
                controllerMap.keySet().forEach(controller -> {
                    List<ResourceOperationCode> tmpList = controllerMap.get(controller);
                    List<AuthTreeVM> operationCodes = new ArrayList<>();
                    tmpList.forEach(operationCode -> {
                        String id = operationCode.getOperationCodeId();
                        String name = operationCode.getOperationName();
                        String requestMethod = operationCode.getRequestMethod();
                        String path = operationCode.getPath();
                        AuthTreeVM authTreeVM = new AuthTreeVM(id, name + "：(" + requestMethod + path + ")", null);
                        operationCodes.add(authTreeVM);
                    });
                    AuthTreeVM controllerAuthTreeVM = new AuthTreeVM("", controller, operationCodes);
                    controllerList.add(controllerAuthTreeVM);
                });
                AuthTreeVM authTreeVM = new AuthTreeVM("", serviceId, controllerList);
                authTreeVMS.add(authTreeVM);
            });
        }
        return authTreeVMS;
    }

    /**
     * 取消授权
     *
     * @param resourceId       资源id
     * @param operationCodeIds 操作码id集合
     */
    public void cancelAuthorize(String resourceId, List<String> operationCodeIds) {
        resourceService.exist(resourceId);
        operationCodeService.exist(operationCodeIds);
        resourceOperationCodeRExtendMapper.deleteByResourceIdAndOperationCodeIds(resourceId, operationCodeIds);
    }
}
