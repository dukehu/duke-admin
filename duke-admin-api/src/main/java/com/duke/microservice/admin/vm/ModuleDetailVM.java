package com.duke.microservice.admin.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created duke on 2018/9/1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDetailVM {

    private String id;

    private String name;

    private String code;

    private String router;

    private String memo;
}
