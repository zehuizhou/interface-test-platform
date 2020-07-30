package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TJmeterScript implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * job名称
     */
    private String name;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 类型 0公共脚本  1业务流脚本
     */
    private Integer type;

    /**
     * 脚本内容
     */
    private String scriptContent;

    /**
     * 数据路径
     */
    private String dataPath;

    /**
     * 脚本路径
     */
    private String scriptPath;

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
     * update_by
     */
    private String updateBy;

    /**
     * update_time
     */
    private Date updateTime;

    /**
     * 状态 0初始状态 1调试中 2成功 3失败
     */
    private Integer debugStatus;

    /**
     * debug开始时间
     */
    private Date debugStartTime;

    /**
     * debug结束时间
     */
    private Date debugEndTime;

    /**
     * debug_by
     */
    private String debugBy;

    /**
     * debug成功数
     */
    private Integer debugSuccess;

    /**
     * debug总数
     */
    private Integer debugTotal;

    /**
     * debug失败数
     */
    private Integer debugFail;

    /**
     * debug耗时，毫秒
     */
    private Long debugDuration;

    private static final long serialVersionUID = 1L;
}