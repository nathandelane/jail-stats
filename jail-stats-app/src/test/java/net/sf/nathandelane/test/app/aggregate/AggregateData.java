package net.sf.nathandelane.test.app.aggregate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import net.sf.nathandelane.test.app.DetailsPage;
import net.sf.nathandelane.test.app.MainPage;
import net.sf.nathandelane.test.app.SearchResultsPage;
import net.sf.nathandelane.test.app.WebDriverFactory;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AggregateData {
  
  private static final int CODE_COLUMN_INDEX = 2;
  private static final int DESCRIPTION_COLUMN_INDEX = 3;
  private static final int GRADE_COLUMN_INDEX = 4;
  private static final int DEGREE_COLUMN_INDEX = 5;
  
  @Test
  public void run() throws MalformedURLException, IOException, InterruptedException {
    final MainPage mainPage = new MainPage();
    
    SearchResultsPage searchResultsPage = mainPage.submit("%");
    
    int pageCounter = 1;
    
    do {
      final int listSize = searchResultsPage.getLookupLinks().size();
      
      for (int itemIndex = 0; itemIndex < listSize; itemIndex++) {
        final DetailsPage nextDetailsPage = searchResultsPage.clickOnItemLink(itemIndex);
        final List<WebElement> details = nextDetailsPage.getChargeTableRows();
        
        int startDetailsIndex = 2;
        
        for (int detailsIndex = startDetailsIndex; detailsIndex < details.size(); detailsIndex++) {
          final WebElement nextDetail = details.get(detailsIndex);        
          final List<WebElement> columns = nextDetail.findElements(By.xpath("td"));
          
          System.out.println(
            String.format("insert into charge_information (reference, item_number, code, description, grade, degree) values (%s, %s, '%s', '%s', '%s', '%s');"
              , (pageCounter * (itemIndex + 1))
              , (detailsIndex - 1)
              , columns.get(CODE_COLUMN_INDEX).getText()
              , columns.get(DESCRIPTION_COLUMN_INDEX).getText()
              , columns.get(GRADE_COLUMN_INDEX).getText()
              , columns.get(DEGREE_COLUMN_INDEX).getText()
            )
          );
        }
        
        searchResultsPage = nextDetailsPage.goBack();
      }
      
      searchResultsPage = searchResultsPage.clickOnNextLink();
      
      pageCounter++;
    }
    while (WebDriverFactory.getWebDriver().findElementByXPath("//a[@class='generalnav' and contains(@href, 'last')]").isDisplayed());
    
    searchResultsPage.close();
  }

}
