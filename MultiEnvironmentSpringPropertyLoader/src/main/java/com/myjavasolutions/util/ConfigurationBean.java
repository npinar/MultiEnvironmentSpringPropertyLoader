package com.myjavasolutions.util;

import java.util.Map;
import java.util.Properties;

public interface ConfigurationBean {

	public Map<String, String> getProperties();
    public void setProperties(final Properties props);
    public String getValue(String key);
    public String setProperty(String key, String value);
}
