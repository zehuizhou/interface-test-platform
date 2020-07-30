package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TEnv implements Serializable {
    private Long id;

    /**
    * 环境名字
    */
    private String name;

    private String remark;

    /**
    * 所属项目的id
    */
    private Long projectId;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 创建时间
    */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}