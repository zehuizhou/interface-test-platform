package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TJmeterExcDetail implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * jmeter脚本id
     */
    private Long jmeterId;

    /**
     * 1、debug 2、任务执行
     */
    private Integer type;

    private String metaValidation;

    /**
     * 请求url
     */
    private String metaUrl;

    /**
     * 请求头
     */
    private String metaRequestHeaders;

    /**
     * 请求体
     */
    private String metaRequestBody;

    /**
     * 请求时间
     */
    private Date metaRequestAt;

    /**
     * cookies
     */
    private String metaCookies;

    /**
     * 请求方式
     */
    private String metaMethod;

    /**
     * 响应码
     */
    private String metaStatusCode;

    /**
     * 响应头
     */
    private String metaResponseHeaders;

    /**
     * 响应体
     */
    private String metaResponseBody;

    /**
     * 耗时，毫秒
     */
    private Long metaResponseTime;

    /**
     * 外部变量
     */
    private String metaExtractedVars;

    /**
     * 附件
     */
    private String attachment;

    /**
     * 状态 0 成功 1 失败
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

    private static final long serialVersionUID = 1L;
}