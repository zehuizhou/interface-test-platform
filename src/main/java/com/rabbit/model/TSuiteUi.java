package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TSuiteUi implements Serializable {
    private Long id;

    private String name;

    private Long projectId;

    private Long parentId;

    /**
     * 1、文件夹，2、用例
     */
    private Integer type;

    private Integer sort;

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private List<TSuiteUi> children;

    private static final long serialVersionUID = 1L;
}