package com.mountain.springboot.jasypt.controller;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicEncryptor {

  private BasicTextEncryptor encryptor;

  BasicEncryptor() {
    encryptor = new BasicTextEncryptor();
    encryptor.setPassword("mountain"); // 加盐
  }

  @GetMapping("/basic/{clear-text}")
  public String encrypt(@PathVariable("clear-text") String clearText) {
    return encryptor.encrypt(clearText);
  }

  @GetMapping("/basic/{cipher-text}")
  public String decrypt(@PathVariable("cipher-text") String cipherText) {
    return encryptor.decrypt(cipherText);
  }
}
