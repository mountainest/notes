package com.mountainest.smtp.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.mail.MailProperties;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Email {
    // @Autowired
    // private MailProperties mailProperties;
    // @Autowired
    // private JavaMailSender mailSender;

    @PostMapping(value = "/send/email")
    public boolean SendEmail() {
        return send();
    }

    private boolean send() {
        //MimeMessage mimeMessage = mailSender.createMimeMessage();
        return true;
    }
}