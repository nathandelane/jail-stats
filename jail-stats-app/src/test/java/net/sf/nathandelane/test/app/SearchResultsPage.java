package net.sf.nathandelane.test.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableList;


public class SearchResultsPage extends BasePage {
  
  @FindByXPath("//a[@class='underlined']")
  private List<WebElement> lookupLinks;

  protected SearchResultsPage() throws MalformedURLException, IOException {
    super(ConfigurationHelper.get().getStartingUrl(), false);
  }
  
  public List<WebElement> getLookupLinks() {
    return ImmutableList.<WebElement>copyOf(lookupLinks);
  }
  
  public DetailsPage clickOnLink(int linkIndex) throws MalformedURLException, IOException {
    lookupLinks.get(linkIndex).click();
    
    return new DetailsPage();
  }

}
