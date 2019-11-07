package com.mountain.springboot.autoconfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AutoConfigurationApplicationTests {

	@Before
	public void start() {
		System.out.println("Start test.");
	}

	@After
	public void end() {
		System.out.println("End test.");
	}

}
