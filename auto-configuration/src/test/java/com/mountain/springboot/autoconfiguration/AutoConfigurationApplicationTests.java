package com.mountain.springboot.autoconfiguration;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AutoConfigurationApplicationTests {
	@Autowired
	private PersonInfo personInfo;

	@Test
	void contextLoads() {
		Assert.assertEquals("error.", "mountain", personInfo.getName());
	}

}
