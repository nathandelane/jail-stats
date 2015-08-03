package net.sf.nathandelane.test.app.aggregate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import net.sf.nathandelane.test.app.DetailsPage;
import net.sf.nathandelane.test.app.MainPage;
import net.sf.nathandelane.test.app.SearchResultsPage;

import org.junit.Test;
import org.openqa.selenium.WebElement;


public class AggregateData {
  
  @Test
  public void run() throws MalformedURLException, IOException {
    final MainPage mainPage = new MainPage();
    
    SearchResultsPage searchResultsPage = mainPage.submit("%");
    
    final int listSize = searchResultsPage.getLookupLinks().size();
    
    for (int index = 0; index < listSize; index++) {
      final DetailsPage nextDetailsPage = searchResultsPage.clickOnLink(index);
      final List<WebElement> details = nextDetailsPage.getChargeTableRows();
      
      searchResultsPage = nextDetailsPage.goBack();
    }
  }

}
