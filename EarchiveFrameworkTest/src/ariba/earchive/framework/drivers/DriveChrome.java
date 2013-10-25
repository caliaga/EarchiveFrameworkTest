package ariba.earchive.framework.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriveChrome extends Driver {

	@Override
	public WebDriver instanceDriver() {
		File file = new File("C:\\driverchrome\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		return driver;
	}

	@Override
	public WebDriver instanceRemoteDriver(String url) {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		
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
