package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Project implements Serializable {
    private Long id;

    private String projectName;

    private String description;

    /**
     * 1.andorid 2.iOS
     */
    private Integer platform;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private static final long serialVersionUID = 1L;
}