package com.mountain.springboot.autoconfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public  class AutoConfigurationApplicationTests {

	@Before
	public void start( ) {
		System.out.println("Start test " + this.getClass() + ".");
	}

	@After
	public void end() {
		System.out.println("End test " + this.getClass() + ".");
	}

}
