package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Client implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 客户端名称
     */
    private String name;

    /**
     * 所属项目
     */
    private String projectIds;

    /**
     * 客户端ip
     */
    private String clientIp;

    /**
     * 客户端端口
     */
    private Integer clientPort;

    /**
     * 1：有效
     */
    private Integer isEnable;

    /**
     * 客户端状态 1 正常 2 链接失败 3 状态未知
     */
    private Integer status;

    /**
     * java版本
     */
    private String javaVersion;

    /**
     * jmeter版本
     */
    private String jmeterVersion;

    /**
     * appium版本
     */
    private String appiumVersion;

    /**
     * 操作系统
     */
    private String osName;

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