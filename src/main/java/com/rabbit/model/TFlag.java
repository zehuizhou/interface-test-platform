package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TFlag implements Serializable {
    private Long id;

    /**
     * flag名字
     */
    private String name;

    /**
     * 类型：1.接口用例,2.api用例集
     */
    private Integer type;

    private String remark;

    /**
     * 所属项目的id
     */
    private Long projectId;

    /**
     * 1启用，0禁用
     */
    private Integer isValid;

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