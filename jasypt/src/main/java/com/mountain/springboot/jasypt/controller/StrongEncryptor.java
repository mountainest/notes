package com.mountain.springboot.jasypt.controller;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StrongEncryptor {

  private StrongTextEncryptor encryptor;

  StrongEncryptor() {
    encryptor = new StrongTextEncryptor();
    encryptor.setPassword("mountain");
  }

  @GetMapping("/strong/{clear-text}")
  public String encrypt(@PathVariable("clear-text") String clearText) {
    return encryptor.encrypt(clearText);
  }

  @GetMapping("/strong/{cipher-text}")
  public String decrypt(@PathVariable("cipher-text") String cipherText) {
    return encryptor.decrypt(cipherText);
  }
}
