package com.rabbit.service.Impl;

import com.rabbit.service.SendMailSevice;
import com.rabbit.utils.FreemarkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SendMailSeviceImpl implements SendMailSevice {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String serverMail;

    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    @Override
    public void sendMail(List<String> toUser, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(serverMail);
            helper.setTo(toUser.toArray(new String[toUser.size()]));
            helper.setSubject(subject);
//			helper.addAttachment("附件",new File("H:/tup"));
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendMail(String toUser, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(serverMail);
        helper.setTo(toUser);
        helper.setSubject(subject);
        helper.setText(text, true);

        javaMailSender.send(message);
    }
    @Override
    public void sendMail(String toUser, String subject, String text, String attachmentName, String attachment) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(serverMail);
        helper.setTo(toUser);
        helper.setSubject(subject);
        helper.setText(text, true);

        if (attachment != null) {
            InputStreamSource inputStreamSource = new ByteArrayResource(attachment.getBytes("UTF-8"));
            helper.addAttachment(attachmentName, inputStreamSource);
        }
        javaMailSender.send(message);
    }

    @Override
    public void sendMailTemplate(List<String> toUser, String title, String templateName, Object templateParam, String attachmentName, String attachment) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(serverMail);
        helper.setTo(toUser.toArray(new String[toUser.size()]));
        helper.setSubject(title);

        if (attachment != null) {
            InputStreamSource inputStreamSource = new ByteArrayResource(attachment.getBytes("UTF-8"));
            helper.addAttachment(attachmentName == null ? "attachment" : attachmentName, inputStreamSource);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("params", templateParam);

//        Configuration configuration = configurer.getConfiguration();
//        String path = RabbitConfig.projectPath + File.separator + "profile/email_template";
//        configuration.setDirectoryForTemplateLoading(new File(path));
//        configuration.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "/email_template");
//        configuration.setDefaultEncoding("utf-8");
//        Template template = configuration.getTemplate(templateName);
//        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(FreemarkerUtil.process(templateName, model), true);
        javaMailSender.send(message);
        log.info("邮件[{}]发送成功", title);
    }

    @Override
    public void sendMailTemplate(List<String> toUser, String title, String templateName, Object templateParam) throws Exception {
        sendMailTemplate(toUser, title, templateName, templateParam, null, null);
    }
}
