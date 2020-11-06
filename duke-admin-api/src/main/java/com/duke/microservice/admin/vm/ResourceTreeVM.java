package com.duke.microservice.admin.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created duke on 2018/8/31
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceTreeVM {

    private String id;

    private String name;

    private String code;

    private String memo;

    private Integer type;

    private String key;

    private String parentId;

    private String title;

    private Boolean checked = false;

    private Boolean expand = true;

    private List<ResourceTreeVM> children;
}
