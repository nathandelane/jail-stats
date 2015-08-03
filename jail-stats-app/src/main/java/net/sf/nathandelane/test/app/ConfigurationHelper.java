package net.sf.nathandelane.test.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import com.google.common.io.Resources;


public class ConfigurationHelper {
  
  private static final String APP_CONFIG_PROPERTIES_NAME = "app-config.properties";
  
  private static ConfigurationHelper INSTANCE;

  private final Properties properties;
  
  private ConfigurationHelper() throws IOException {
    properties = new Properties();
    properties.load(Resources.getResource(APP_CONFIG_PROPERTIES_NAME).openStream());
  }
  
  public String getStartingUrl() throws MalformedURLException {
    return properties.getProperty("url");
  }
  
  public static ConfigurationHelper get() throws IOException {
    if (INSTANCE == null) {
      synchronized (ConfigurationHelper.class) {
        if (INSTANCE == null) {
          INSTANCE = new ConfigurationHelper();
        }
      }
    }
    
    return INSTANCE;
  }
  
}
