package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ProjectPage implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * projectId
     */
    private Long projectId;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 1：有效
     */
    private Integer isEnable;

    /**
     * 备注
     */
    private String remark;

    /**
     * createBy
     */
    private String createBy;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateBy
     */
    private String updateBy;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}