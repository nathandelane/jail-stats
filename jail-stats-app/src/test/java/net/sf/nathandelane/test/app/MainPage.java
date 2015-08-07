package net.sf.nathandelane.test.app;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;


public class MainPage extends BasePage {

  @FindByName("identifier")
  private WebElement identifierField;
  
  @FindByXPath("/html/body/form[2]/table[2]/tbody/tr[4]/td[3]/table/tbody/tr[3]/td[5]/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td/a[1]")
  private WebElement submitButton;

  public MainPage() throws MalformedURLException, IOException {
    super(ConfigurationHelper.get().getStartingUrl(), true);
  }
  
  public SearchResultsPage submit(String value) throws MalformedURLException, IOException {
    Waiter.waitForSeconds(1);
    
    submitButton.click();
    
    return new SearchResultsPage();
  }

}
