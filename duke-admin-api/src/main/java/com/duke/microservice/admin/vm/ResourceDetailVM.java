package com.duke.microservice.admin.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created duke on 2018/8/31
 */
@Getter
@Setter
@AllArgsConstructor
public class ResourceDetailVM {

    private String name;

    private String code;

    private String icon;

    private String router;

    private Integer sort;

    private Integer type;

    private String memo;

    private List<AuthTreeVM> authTree;
}
