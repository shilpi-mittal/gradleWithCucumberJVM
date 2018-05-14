package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestWebDriver {
  private static final long DEFAULT_WAIT_TIME = 10;
  private static WebDriver webDriver;
  private static String BASE_URL;

  public TestWebDriver(WebDriver driver) {
    TestWebDriver.webDriver = driver;
    maximizeWindows();
  }

  public void setBaseUrl(String BASE_URL) {
    webDriver.manage().deleteAllCookies();
    TestWebDriver.BASE_URL = BASE_URL;
  }

  public void maximizeWindows() {
    webDriver.manage().window().maximize();
  }

  public boolean isDisplayed(WebElement element) {
    return element.isDisplayed();
  }

  public void waitForElementToAppear(final WebElement element) {
    (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return (element.isDisplayed());
      }
    });
  }

  public String getCurrentUrl() {
    return webDriver.getCurrentUrl();
  }

  public WebElement findElement(By by) {
    return webDriver.findElement(by);
  }

  public void quitDriver() {
    webDriver.quit();
  }

  public static WebDriver getDriver() {
    return webDriver;
  }

  public void navigateTo(String url) {
    webDriver.navigate().to(url);
  }

  public void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
