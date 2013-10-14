package ariba.earchive.framework.setup;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import ariba.earchive.framework.drivers.DriverFactory;

public class MainTestCase {
	
	DriverFactory driverFactory = null;
	
	@Parameters({"browser"})
	
	@BeforeClass
	protected void setUp(String browser)
	{
		driverFactory = new DriverFactory();
		driverFactory.getDriver(browser);
	}

	@AfterClass
	protected void tearDown(){
		driverFactory.tearDown();
	}
	
	protected WebDriver driver(){
		return driverFactory.driver();
	}
}
