package com.mountainest.smtp.controller;

public class Result {
    private int status = 0;
    private String msg = "邮件发送成功。";

    Result() {
    }

    Result(String msg) {
        this.status = 1;
        this.msg = "邮件发送失败。" + msg;
    }
    
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}