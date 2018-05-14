package functional;

import Utils.TestCaseHelper;
import org.page.objects.HomePage;
import org.page.objects.PageObjectFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import org.testng.Assert;

public class BasicSearchTest extends TestCaseHelper {

  @BeforeMethod (groups = {"testNG"})
  public void setup() throws SQLException {
    super.setup();
  }

  @Test (groups = {"testNG"})
  public void verifyBasicSearch() throws SQLException {
    HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
    homePage.accessHomePage();
    Assert.assertTrue(homePage.isLogoDisplayed());
    homePage.enterSearchParameter("hello");
    homePage.hitEnter();
    Assert.assertNotEquals(homePage.getBaseUrl(), homePage.getUrl());
    Assert.assertTrue(homePage.isTextDisplayed("hello"));
    //System.out.println(dbWrapper.getDate().toString());
  }

  @AfterMethod (groups = {"testNG"})
  public void tearDown() throws SQLException {
    //dbWrapper.closeConnection();
  }
}