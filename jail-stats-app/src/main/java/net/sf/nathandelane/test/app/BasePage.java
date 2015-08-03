package net.sf.nathandelane.test.app;

import java.lang.reflect.Field;
import java.util.Collection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

  protected BasePage(String url, boolean loadPage) {
    if (loadPage) {
      WebDriverFactory.getWebDriver().get(url);
    }

    try {
      initializeFields();
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  protected String getTitle() {
    return WebDriverFactory.getWebDriver().getTitle();
  }

  protected WebElement getWebElementByCssSelector(String cssSelector) {
    return WebDriverFactory.getWebDriver().findElement(By.cssSelector(cssSelector));
  }

  protected String getCssSelector(Field field) {
    return field.getAnnotation(FindByCssSelector.class).value();
  }

  protected WebElement getWebElementByXPath(String xpath) {
    return WebDriverFactory.getWebDriver().findElement(By.xpath(xpath));
  }

  protected String getXPath(Field field) {
    return field.getAnnotation(FindByXPath.class).value();
  }

  private void initializeFields() throws IllegalArgumentException, IllegalAccessException {
    for (Field nextField : this.getClass().getDeclaredFields()) {
      final FindByCssSelector cssSelectorAnnotation = nextField.getAnnotation(FindByCssSelector.class);

      if (cssSelectorAnnotation != null) {
        nextField.setAccessible(true);
        
        if (Collection.class.isAssignableFrom(nextField.getType())) {
          nextField.set(this, WebDriverFactory.getWebDriver().findElementsByCssSelector(cssSelectorAnnotation.value()));
        }
        else {
          nextField.set(this, WebDriverFactory.getWebDriver().findElementByCssSelector(cssSelectorAnnotation.value()));
        }
      }
      
      final FindByXPath xpathAnnotation = nextField.getAnnotation(FindByXPath.class);

      if (xpathAnnotation != null) {
        nextField.setAccessible(true);

        if (Collection.class.isAssignableFrom(nextField.getType())) {
          nextField.set(this, WebDriverFactory.getWebDriver().findElementsByXPath(xpathAnnotation.value()));
        }
        else {
          nextField.set(this, WebDriverFactory.getWebDriver().findElementByXPath(xpathAnnotation.value()));
        }
      }
      
      final FindByName nameAnnotation = nextField.getAnnotation(FindByName.class);

      if (nameAnnotation != null) {
        nextField.setAccessible(true);
        
        if (Collection.class.isAssignableFrom(nextField.getType())) {
          nextField.set(this, WebDriverFactory.getWebDriver().findElementsByName(nameAnnotation.value()));
        }
        else {
          nextField.set(this, WebDriverFactory.getWebDriver().findElementByName(nameAnnotation.value()));
        }
      }
    }
  }
  
}
