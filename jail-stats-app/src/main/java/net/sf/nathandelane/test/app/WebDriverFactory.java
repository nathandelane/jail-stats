package net.sf.nathandelane.test.app;


import org.openqa.selenium.firefox.FirefoxDriver;

public final class WebDriverFactory {

  private static FirefoxDriver INSTANCE;

  public static FirefoxDriver getWebDriver() {
    if (INSTANCE == null) {
      synchronized(WebDriverFactory.class) {
        if (INSTANCE == null) {
          INSTANCE = new FirefoxDriver();
        }
      }
    }

    return INSTANCE;
  }

  public static void quit() {
    INSTANCE.quit();
  }

  private WebDriverFactory() {}

}