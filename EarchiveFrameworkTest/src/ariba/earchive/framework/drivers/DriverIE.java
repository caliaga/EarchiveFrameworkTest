package ariba.earchive.framework.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverIE extends Driver {

	

	
	@Override
	public WebDriver instanceDriver() {
		File file = new File("C:\\driver\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		driver = new InternetExplorerDriver();
		return driver;
	}

	@Override
	public WebDriver instanceRemoteDriver(String url) {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		
		try {
			driver = new RemoteWebDriver(new URL(url), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		return driver;
	}

	@Override
	public WebDriver driver() {
		// TODO Auto-generated method stub
		return driver;
	}

	@Override
	public void freeDriver() {
		driver.quit();
	}	
}