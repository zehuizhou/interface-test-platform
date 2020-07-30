package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SysDictValue implements Serializable {
    private Long id;

    private Long dictId;

    /**
     * 字典code
     */
    private String key;

    /**
     * 字典name
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 状态 1启用 0失效
     */
    private Integer isEnable;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}