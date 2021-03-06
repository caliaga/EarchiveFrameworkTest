package ariba.earchive.framework.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriveFF extends Driver {

	@Override
	public WebDriver instanceDriver() {
		driver = new FirefoxDriver();
		return driver;
	}

	@Override
	public WebDriver instanceRemoteDriver(String url) {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		
		try {
			driver = new RemoteWebDriver(new URL(url), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		return driver;
	}

	@Override
	public WebDriver driver() {
		return driver;
	}

	@Override
	public void freeDriver() {
		driver.quit();
	}

}
