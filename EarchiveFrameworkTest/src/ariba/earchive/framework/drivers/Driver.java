package ariba.earchive.framework.drivers;

import org.openqa.selenium.WebDriver;

public abstract class Driver {
	
	protected WebDriver driver;
	
	/*Methods*/
	public abstract WebDriver instanceDriver();
	public abstract WebDriver instanceRemoteDriver(String url);
	public abstract WebDriver driver();
	public abstract void freeDriver();

}
