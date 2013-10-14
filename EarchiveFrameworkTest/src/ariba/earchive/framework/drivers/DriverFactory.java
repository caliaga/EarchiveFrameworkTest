package ariba.earchive.framework.drivers;

import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	public Driver genericDriver;
	
	public void getDriver(String browserName) 
	{
		switch(browserName){
		case "IE":
			genericDriver = new DriverIE();
			break;
		case "FF":
			genericDriver = new DriveFF();
			break;
		case "CHROME":
			genericDriver = new DriveChrome();
			break;
		default:
			genericDriver = null;
		}
	
		genericDriver.instanceDriver();
	}
	
	@AfterMethod
	public void tearDown(){
		genericDriver.freeDriver();
	}
	
	public WebDriver driver(){
		return genericDriver.driver();
	}
}
