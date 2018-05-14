package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import static java.lang.System.getProperty;

public class DriverFactory {
  private String driverType;

  protected WebDriver loadDriver(boolean enableJavascript) {
    driverType = getProperty("web.driver", "Firefox");
    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/testModule/testCore/src/test/resources/geckodriver");
    return createFirefoxDriver(enableJavascript);
  }

  private WebDriver createFirefoxDriver(boolean enableJavascript) {
    boolean headless = Boolean.parseBoolean(getProperty("headless", "false"));
    FirefoxProfile profile = new FirefoxProfile();
    profile.setAcceptUntrustedCertificates(true);
    profile.setPreference("signed.applets.codebase_principal_support", true);
    profile.setPreference("javascript.enabled", enableJavascript);
    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
    profile.setPreference("browser.download.dir", new File(System.getProperty("user.dir")).getParent());
    profile.setPreference("browser.download.folderList", 2);
    profile.setPreference("dom.storage.enabled", true);
    profile.setPreference("device.storage.enabled", true);
    FirefoxOptions options = new FirefoxOptions();
    options.setProfile(profile);
    if ((getProperty("os.name").toLowerCase().contains("mac")) && headless) {
      String LOCAL_FIREFOX_X11_PATH = "/opt/local/bin/firefox-x11";
      File binaryFile = new File(LOCAL_FIREFOX_X11_PATH);
      FirefoxBinary binary = new FirefoxBinary(binaryFile);
      String LOCAL_X11_DISPLAY = ":5";
      binary.setEnvironmentProperty("DISPLAY", LOCAL_X11_DISPLAY);
      options.setBinary(binary);
      return new FirefoxDriver(options);
    }
    return new FirefoxDriver(options);
  }
}
