package com.myjavasolutions;

import static junit.framework.Assert.assertEquals;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.myjavasolutions.util.ConfigurationBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml" })
public class ConfigurationBeanImplTest {

	@Value("#{configurationBean.getValue('db.url')}")
	private String url;
	
	@Value("#{configurationBean.getValue('db.schema')}")
	private String schema;
	
	 @Value("#{configurationBean.getProperties()}")
	 private  Map<String, String> properties;
	 
	 @Autowired
	 private ConfigurationBean configurationBean;
	
	@Test
	public void are_Url_And_Schema_Production_Values() throws Exception {
		
		String expectedUrl = "proddatabase";
		String expectedDbSchema = "prod";
		
		//url will be read from external prop file
		assertEquals(expectedUrl, this.url);
		//schema will be read from external prop file
		assertEquals(expectedDbSchema, this.schema);
	}
	
	@Test
	public void test_getProperties_For_User_Id() throws Exception {
		
		String expectedUserId = "devuser";
		
    	for(Map.Entry<String, String> entry : this.properties.entrySet()) {
    		if(entry.getKey().equalsIgnoreCase("db.username")) {
    			assertEquals(expectedUserId, entry.getValue());
    		}
    	}
	}
	
	@Test
	public void add_New_KeyValue_Pair_To_Existing_Properties_File() throws Exception {
		
		configurationBean.setProperty("uniqueId", "12345");
		
		//make sure the new key-value pair added to our existing properties file.
		for(Map.Entry<String, String> entry : this.properties.entrySet()) {
    		if(entry.getKey().equalsIgnoreCase("db.username")) {
    			assertEquals("devuser", entry.getValue());
    		}
    		if(entry.getKey().equalsIgnoreCase("uniqueId")) {
    			assertEquals("12345", entry.getValue());
    		}
    		
    	}
	}
}
