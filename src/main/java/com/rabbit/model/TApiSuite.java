package com.rabbit.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class TApiSuite implements Serializable {
    /**
    * id
    */
    private Long id;

    /**
    * 接口名称
    */
    private String name;

    /**
    * 项目id
    */
    private Long projectId;

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