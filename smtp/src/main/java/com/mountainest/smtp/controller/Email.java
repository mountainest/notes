package com.mountainest.smtp.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Email {
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping(value = "/send/email")
    public boolean SendEmail() {
        return send();
    }

    private boolean send() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo("pfanr@163.com");
            helper.setSubject("subject");
            helper.setText("arg0", true);
            
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return false;
        }

        return true;
    }
}