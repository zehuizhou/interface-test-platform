package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;

public class TGlobalParam implements Serializable {
    /**
    * id
    */
    private Long id;

    /**
    * 参数名称
    */
    private String paramName;

    private Long projectId;

    /**
    * 参数类型：1、字符串 2、数值 3、数据库 4、布尔 5、函数
    */
    private Integer paramType;

    /**
    * 参数值
    */
    private String paramValue;

    /**
    * 类型：1、ui自动化  2、接口自动化 、3 、app自动化
    */
    private Integer type;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}