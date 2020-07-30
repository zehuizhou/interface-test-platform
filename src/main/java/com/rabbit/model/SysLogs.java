package com.rabbit.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SysLogs extends BaseEntity<Long> {

    private static final long serialVersionUID = -7809315432127036583L;
    private SysUser user;
    private String module;
    private Boolean flag;
    private String remark;

}
