package com.rabbit.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 设备表
 */
@Data
public class Device implements Serializable {
    public static final Integer OFFLINE_STATUS = 0;
    public static final Integer USING_STATUS = 1;
    public static final Integer IDLE_STATUS = 2;
    /**
     * 设备id
     */
    private String id;

    /**
     * 设备名
     */
    private String name;

    /**
     * 设备所在的agent的ip
     */
    private String agentIp;

    /**
     * 设备所在的agent的端口
     */
    private Integer agentPort;

    /**
     * 设备系统版本
     */
    private String systemVersion;

    /**
     * cpu信息
     */
    private String cpuInfo;

    /**
     * 内存大小：GB
     */
    private String memSize;

    /**
     * 屏幕宽（像素）
     */
    private Integer screenWidth;

    /**
     * 屏幕高（像素）
     */
    private Integer screenHeight;

    /**
     * 服务端保存的文件路径
     */
    private String imgPath;

    /**
     * 平台：1.android  2.ios
     */
    private Integer platform;

    /**
     * 设备状态：0.离线 1.使用中 2.空闲
     */
    private Integer status;

    /**
     * 最近一次在线时间
     */
    private Date lastOnlineTime;

    /**
     * 最近一次离线时间
     */
    private Date lastOfflineTime;

    /**
     * 使用人
     */
    private String username;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}