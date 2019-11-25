package com.mountain.springboot.jasypt;

import com.mountain.springboot.jasypt.controller.BasicEncryptor;
import com.mountain.springboot.jasypt.controller.StrongEncryptor;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public  class JasyptTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Autowired
	private BasicEncryptor basicEncryptor;
	@Autowired
	private StrongEncryptor strongEncryptor;

	@Before
	public void start( ) {
		System.out.println("Start test " + this.getClass() + ".");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void basicEncryptor() {
		String clearText = "root";
		String cipherText = basicEncryptor.encrypt(clearText);
		Assert.assertThat(basicEncryptor.decrypt(cipherText), Matchers.equalTo(clearText));
	}

	@After
	public void end() {
		System.out.println("End test " + this.getClass() + ".");
	}

}
