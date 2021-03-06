package com.mountain.springboot.autoconfiguration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonInfoTest extends AutoConfigurationApplicationTests{
  @Autowired
  private PersonInfo personInfo;

  @Test
  public void testPersonInfo() {
    Assert.assertEquals("Failed to test PersonInfo.", "mountain", personInfo.getName());
    Assert.assertEquals("Failed to test PersonInfo.", 18, personInfo.getAge());
  }
}