package com.myjavasolutions.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;


public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private boolean loadFromExternalPropertiesFile = true;
    private boolean loadFromInternalPropertiesFile = true;

    private Resource internalProp;
    private Resource externalProp;
    
    private BeanFactory beanFactory;
    private String configBeanName;

    public CustomPropertyPlaceholderConfigurer(Resource internalProp, Resource externalProp, String configBeanName) {
        super();
        this.configBeanName = configBeanName;
        this.internalProp = internalProp;
        this.externalProp = externalProp;

        if(this.internalProp == null) {
            loadFromInternalPropertiesFile = false;
        }

        if(this.externalProp == null) {
            loadFromExternalPropertiesFile = false;
        }
    }
    
    //The Spring framework will call this method to load the properties.
    @Override
    protected void loadProperties(final Properties props) throws IOException {
        List<Resource> resources = new ArrayList<Resource>();

        if(loadFromInternalPropertiesFile) {
            resources.add(this.internalProp);
        }

        if(loadFromExternalPropertiesFile) {
            resources.add(this.externalProp);
        }

        Resource[] locations = new Resource[resources.size()];
        
        resources.toArray(locations);
        
        //Properties defined in later files will override properties defined earlier files, in case of overlapping keys.
        setLocations(locations);

        //Load properties into the given instance. props is the Properties instance to load into
        super.loadProperties(props);
        
        loadServerConfigBean(props);
    }
    

    //need bean factory to look up the ConfigurationBean instance. 
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		super.setBeanFactory(beanFactory);
	}
	
	/**
	 * Populates the Configuration bean with the properties that have been loaded.
	 *
	 * @param props the props
	 */
	protected void loadServerConfigBean(final Properties props) {
	    ((ConfigurationBean) beanFactory.getBean(this.configBeanName, ConfigurationBean.class)).setProperties(props);
	}


    
}
