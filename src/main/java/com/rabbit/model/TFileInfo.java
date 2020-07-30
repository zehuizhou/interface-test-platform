package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TFileInfo implements Serializable {
    private Long id;

    private String name;

    private Long projectId;

    /**
     * 文件md5
     */
    private String md5;

    private String contentType;

    private Long size;

    private Long sourceId;

    /**
     * 1、ui  2、接口
     */
    private Integer sourceType;

    /**
     * 物理路径
     */
    private String path;

    /**
     * 0 非图片 1图片
     */
    private Integer type;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}