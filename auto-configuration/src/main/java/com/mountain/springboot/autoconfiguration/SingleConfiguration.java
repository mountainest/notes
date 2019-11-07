package com.mountain.springboot.autoconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SingleConfiguration {
  @Value("${person.name}")
  private String name;
  @Value("${person.age}")
  private int age;

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
