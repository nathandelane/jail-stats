package net.sf.nathandelane.test.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


public class DetailsPage extends BasePage {
  
  @FindByXPath("/html/body/table[2]/tbody/tr[4]/td[3]/table/tbody/tr[3]/td/table/tbody/tr/td/table/tbody/tr/td/table[6]/tbody/tr")
  private List<WebElement> chargeTableRows;

  protected DetailsPage() throws MalformedURLException, IOException {
    super(ConfigurationHelper.get().getStartingUrl(), false);
  }
  
  public List<WebElement> getChargeTableRows() {
    if (chargeTableRows == null) {
      return ImmutableList.<WebElement>copyOf(Lists.<WebElement>newArrayList());
    }
    
    return ImmutableList.<WebElement>copyOf(chargeTableRows).subList(2, chargeTableRows.size() - 1);
  }
  
  public SearchResultsPage goBack() throws MalformedURLException, IOException {
    WebDriverFactory.getWebDriver().navigate().back();
    
    return new SearchResultsPage();
  }

}
