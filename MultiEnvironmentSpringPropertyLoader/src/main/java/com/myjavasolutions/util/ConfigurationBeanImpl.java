package com.myjavasolutions.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ConfigurationBeanImpl implements ConfigurationBean {

    private Map<String, String> props = new HashMap<String, String>();

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(props);
    }

    //Sets the properties for the configuration bean based on Properties loaded by the Property Placeholder Configurer
    public void setProperties(final Properties props) {
        for (Iterator<String> iterator = ((Map) props).keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            this.props.put(key, props.getProperty(key));
        }
    }

    /**
     * Returns the value for the given key. If key does not exists, returns null.
     *
     * @param key
     * @return the value
     */
    public String getValue(String key) {
        if (props == null) {
            return null;
        }

        return props.get(key);
    }

    /**
     * Sets the given key with the given value
     *
     * @param key
     * @param value
     * @return the previous value of the property
     */
    public String setProperty(String key, String value) {
        if (props == null) {
            props = new HashMap<String, String>();
        }

        return props.put(key, value);
    }

   
}

