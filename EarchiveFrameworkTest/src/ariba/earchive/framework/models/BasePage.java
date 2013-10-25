package ariba.earchive.framework.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ariba.earchive.framework.common.Util;

public class BasePage {
	WebDriver basePageDriver = null;
	
	String xBtnLogout = Util.getProperty("XPATH_BUTTON_LOGOFF");
	
	public BasePage(WebDriver driver){
		this.basePageDriver = driver;
	}
	
	/** Get Name of Role of Use Logged
	 * @author caliaga
	 * @return RoleName String
	 */
	public String getRoleNameUserLogged(String region) throws InterruptedException
	{
		String xLnkRole="";
		if(region=="BR")
		{
			xLnkRole= Util.getProperty("XPATH_ROLE");
		}
		else if(region=="EUR")
		{
			xLnkRole= Util.getProperty("XPATH_ROLE_EUR");
		}
		
		System.out.println(region);
		
		Util.moveClickElement(basePageDriver, xLnkRole);
		Util myUtil = new Util();
		myUtil.clickAndWaitRole(basePageDriver, xBtnLogout);
		WebElement ObjUsertext = basePageDriver.findElement(By.id("ctl00_lblRoleName"));
		String UserText = ObjUsertext.getText();
		return UserText;
	}
	
	
}