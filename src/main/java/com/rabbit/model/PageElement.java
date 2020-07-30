package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PageElement implements Serializable {
    /**
     * id
     */
    private Long id;

    private Long pageId;

    /**
     * 元素名称
     */
    private String elementName;

    /**
     * 定位方式
     */
    private String byType;

    /**
     * 定位值
     */
    private String byValue;

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