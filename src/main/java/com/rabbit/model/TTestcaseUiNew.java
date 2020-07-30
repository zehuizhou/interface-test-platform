package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TTestcaseUiNew implements Serializable {
    private Long id;

    private String name;

    /**
     * 环境id
     */
    private Long envId;

    private Long projectId;

    /**
     * 超时时间，单位分
     */
    private Long timoutTime;

    /**
     * 失败了是否继续0：不继续，1：继续
     */
    private Integer failContinue;

    /**
     * 标签
     */
    private String flags;

    /**
     * 业务名参数
     */
    private String params;

    /**
     * 用例内部参数
     */
    private String caseVars;

    /**
     * 1,用例、2：业务
     */
    private Long caseType;

    /**
     * 备注
     */
    private String remark;

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}