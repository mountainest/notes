package com.mountainest.smtp.controller;

public class MailInfo {
    private String tos;
    private String ccs;
    private String subject;
    private String text;

    public String getTos() {
        return this.tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    public String getCcs() {
        return this.ccs;
    }

    public void setCcs(String ccs) {
        this.ccs = ccs;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}