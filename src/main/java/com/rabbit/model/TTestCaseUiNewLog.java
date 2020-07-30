package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TTestCaseUiNewLog implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 计划日志id
     */
    private Long planLogId;

    /**
     * 测试业务日志id
     */
    private Long suiteLogId;

    private Long suiteId;

    private String suiteName;

    private Long caseId;

    /**
     * 用例名称
     */
    private String caseName;

    /**
     * 执行状态 0成功 1失败 2跳过
     */
    private Integer status;

    /**
     * 结束时间
     */
    private Date endTime;

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

    private static final long serialVersionUID = 1L;
}