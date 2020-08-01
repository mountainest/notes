package com.mountainest.smtp.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Email {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping(value = "/send/email")
    private Result send(@RequestBody MailInfo mailInfo) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        String tos = mailInfo.getTos();
        String text = mailInfo.getText();
        if (StringUtils.isEmpty(tos)
            || StringUtils.isEmpty(text)) {
            return new Result("收件人和正文不可缺省。");
        }

        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(tos.split(",|;"));
            helper.setText(text, true);

            String ccs = mailInfo.getCcs();
            if (!StringUtils.isEmpty(ccs)) {
                helper.setCc(ccs.split(",|;"));
            }

            String subject = mailInfo.getSubject();
            if (!StringUtils.isEmpty(subject)) {
                helper.setSubject(subject);
            }

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error(e.toString());
            return new Result(e.toString());
        }

        return new Result();
    }
}