package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SysDict implements Serializable {
    private Long id;

    /**
     * 字典组code
     */
    private String key;

    /**
     * 字典组name
     */
    private String name;

    private Integer isEnable;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}