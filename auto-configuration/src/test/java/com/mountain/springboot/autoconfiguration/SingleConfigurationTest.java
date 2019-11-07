package com.mountain.springboot.autoconfiguration;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SingleConfigurationTest extends AutoConfigurationApplicationTests{
  @Autowired
  private SingleConfiguration singleConfiguration;

  @Test
  void testSingleConfiguration() {
    Assert.assertEquals("Failed to test SingleConfiguration.", "mountain", singleConfiguration.getName());
    Assert.assertEquals("Failed to test SingleConfiguration.", 18, singleConfiguration.getAge());
  }
}