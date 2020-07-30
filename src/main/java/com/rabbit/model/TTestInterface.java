package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TTestInterface implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 业务流id
     */
    private Long businessId;

    /**
     * 业务流名称
     */
    private String businessName;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 类型 0公共接口  业务流接口
     */
    private Integer type;

    /**
     * 状态 0启用 1禁用
     */
    private Integer status;

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
     * 排序
     */
    private Integer sort;

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