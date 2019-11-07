package com.mountain.springboot.autoconfiguration;

// 标有 @ConfigurationProperties 的类的所有属性和配置文件中相关的配置项进行绑定，默认从全局配置文件中获取配置值。

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component // 必须标记为组件
@ConfigurationProperties(prefix = "person") // 必须加前缀
public class PersonInfo {
  private String name;
  private String sex;
  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
