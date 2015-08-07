package net.sf.nathandelane.test.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableList;


public class SearchResultsPage extends BasePage {
  
  @FindByXPath("//a[@class='underlined']")
  private List<WebElement> lookupLinks;
  
  @FindByXPath("//a[@class='generalnav' and contains(@href, 'nex')]")
  private WebElement nextLink;

  protected SearchResultsPage() throws MalformedURLException, IOException {
    super(ConfigurationHelper.get().getStartingUrl(), false);
  }
  
  public List<WebElement> getLookupLinks() {
    return ImmutableList.<WebElement>copyOf(lookupLinks);
  }
  
  public DetailsPage clickOnItemLink(int linkIndex) throws MalformedURLException, IOException {
    Waiter.waitForSeconds(1);
    
    lookupLinks.get(linkIndex).click();
    
    return new DetailsPage();
  }
  
  public SearchResultsPage clickOnNextLink() throws MalformedURLException, IOException {
    Waiter.waitForSeconds(1);
    
    nextLink.click();
    
    return new SearchResultsPage();
  }
  
  public void close() {
    WebDriverFactory.getWebDriver().close();
  }

}
