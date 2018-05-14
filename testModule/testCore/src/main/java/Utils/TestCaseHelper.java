package Utils;

import org.openqa.selenium.remote.UnreachableBrowserException;

import java.sql.SQLException;

public class TestCaseHelper {
  private boolean isSeleniumStarted;
  public static TestWebDriver testWebDriver;
  private DriverFactory driverFactory = new DriverFactory();
  //public DbWrapper dbWrapper;

  public void setup() throws SQLException {
    //dbWrapper = new DbWrapper();

    if (!isSeleniumStarted) {
      loadDriver();
      addTearDownShutDownHook();
      isSeleniumStarted = true;
    }
  }

  private void addTearDownShutDownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        if (testWebDriver != null) {
          tearDownSuite();
        }
      }
    });
  }

  private void tearDownSuite() {
    try {
      testWebDriver.quitDriver();
    } catch (UnreachableBrowserException e) {
      e.printStackTrace();
    }
  }

  private void loadDriver() {
    testWebDriver = new TestWebDriver(driverFactory.loadDriver(true));
  }
}
