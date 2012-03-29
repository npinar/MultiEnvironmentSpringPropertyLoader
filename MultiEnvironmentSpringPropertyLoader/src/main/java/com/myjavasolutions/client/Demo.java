package com.myjavasolutions.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

    @Value("#{configurationBean.getValue('db.username')}")
    private String user;
    
    @Value("#{configurationBean.getProperties()}")
    private  Map<String, String> properties;

    public String getDBUserId() {
    	return this.user;
    }
    
    public static void main(String args[]) {
    	
    	ApplicationContext context =
    			new ClassPathXmlApplicationContext("applicationContext.xml");
    	
    	Demo demo = context.getBean("demo", Demo.class);
    	
    	//print out the complete properties file
    	System.out.println("Content of Prop file");
    	System.out.println("-----------------------");
    	for(Map.Entry<String, String> entry : demo.properties.entrySet()) {
    		System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
    	}
    	
    }
    
    
}
