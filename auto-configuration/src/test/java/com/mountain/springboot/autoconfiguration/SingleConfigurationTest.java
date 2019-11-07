package com.mountain.springboot.autoconfiguration;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SingleConfigurationTest extends AutoConfigurationApplicationTests{
  @Autowired
  private SingleConfiguration singleConfiguration;

  @Test
  void testSingleConfiguration() {
    Assert.assertNotEquals("Failed to test SingleConfiguration.", "mountain", singleConfiguration.getPath());
    Assert.assertEquals("Failed to test SingleConfiguration.", 18, singleConfiguration.getAge());
  }
}