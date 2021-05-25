package com.mountain.springboot.autoconfiguration.demo;

import com.mountain.springboot.autoconfiguration.AutoConfigurationApplicationTests;
import org.junit.Test;

public class SingletonTest extends AutoConfigurationApplicationTests {

  @Test
  public void test() {
    Singleton instance = Singleton.getInstance();
//    instance.setStr("abc");
//    instance.setStr("bcd");
    Singleton.getInstance().print();
  }
}