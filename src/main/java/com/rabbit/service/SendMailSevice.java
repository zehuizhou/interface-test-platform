package com.rabbit.service;

import org.springframework.core.io.InputStreamSource;

import javax.mail.MessagingException;
import java.io.InputStream;
import java.util.List;

public interface SendMailSevice {

    /**
     * @param toUser
     * @param subject 标题
     * @param text    内容（支持html格式）
     */
    void sendMail(List<String> toUser, String subject, String text);

    void sendMail(String toUser, String subject, String text) throws MessagingException;

	void sendMail(String toUser, String subject, String text, String attachmentName, String attachment) throws Exception;

    void sendMailTemplate(List<String> toUser, String title, String templateName, Object templateParam) throws Exception;

    void sendMailTemplate(List<String> toUser, String title, String templateName, Object templateParam, String attachmentName, String attachment) throws Exception;
}
