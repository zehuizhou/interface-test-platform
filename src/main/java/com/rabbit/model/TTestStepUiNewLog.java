package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TTestStepUiNewLog implements Serializable {
    /**
    * 日志ID
    */
    private Long id;

    /**
    * 计划日志id
    */
    private Long planJobId;

    private Integer caseLogId;

    /**
    * 用例执行ID
    */
    private Long stepId;

    /**
    * 任务ID
    */
    private String stepName;

    private Long caseId;

    private String caseName;

    /**
    * 日志明细
    */
    private String logDetail;

    /**
    * 执行状态 0成功 1失败 2跳过
    */
    private Integer status;

    /**
    * 1.用例步骤 2.业务流步骤
    */
    private Integer type;

    /**
    * 结束时间
    */
    private Date endTime;

    /**
    * 日志用例步骤
    */
    private Integer endFlag;

    /**
    * UI自动化自动截图地址
    */
    private String imgname;

    private String createBy;

    /**
    * 创建时间
    */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}