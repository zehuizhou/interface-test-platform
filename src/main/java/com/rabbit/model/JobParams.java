package com.rabbit.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Data
public class JobParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long envId;//环境id
    private Long clientId;//客户端IP
    private String browserType;
    private Integer isSendEmail;//执行完毕是否发送邮件
    private String receivers;//执行完毕是否发送邮件
}
