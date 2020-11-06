package com.duke.microservice.admin.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuVM {

    private String id;

    private String code;

    private String name;

    private String icon;

    private String parentId;

    private Integer levelNo;

    private Integer isLeaf;

    private String url;

    private Integer menuOrder;

    private String params;

    private String remark;

    public List<MenuVM> menuVMS;
}
