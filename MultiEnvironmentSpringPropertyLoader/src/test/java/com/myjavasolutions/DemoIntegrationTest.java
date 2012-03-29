package com.myjavasolutions;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.myjavasolutions.client.Demo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml" })
public class DemoIntegrationTest {

	@Value("#{demo}")
	private Demo demo;
	
	@Test
	public void is_UserId_Dev_User() throws Exception {
		
		String expectedUserId = "devuser";
		
		//userid will read from internal prop file since it is not defined in external prop file.
		assertEquals(expectedUserId, demo.getDBUserId());
	}
}
