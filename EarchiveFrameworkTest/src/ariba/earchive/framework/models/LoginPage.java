package ariba.earchive.framework.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ariba.earchive.framework.common.Util;

public class LoginPage {
	
	//Variables
	WebDriver loginDriver = null;
	
	//Load XPath of Controls
	String loginButtonXPath = Util.getProperty("XPATH_BUTTON_LOGIN");
	String logoutButtonXPath = Util.getProperty("XPATH_BUTTON_LOGOFF");
	String xLnkRole = Util.getProperty("XPATH_ROLE");
	String xImgGeneral = Util.getProperty("XPATH_IMG_GENERAL");
	
	
	//Constructor
	public LoginPage(WebDriver driver){
		this.loginDriver = driver;
	}
	
	public void navigateTo(){
		String URL=Util.getProperty("ADMIN_BASE_URL");
		this.loginDriver.get(URL);
	}
	
	public void typeUserName(String userName){
		WebElement userNameTextField; 
		userNameTextField = loginDriver.findElement(By.id("txtUserID"));
		userNameTextField.clear();
		userNameTextField.sendKeys(userName);
	}
	
	public void typeOrgId(String orgID){
		WebElement orgIDtextField; 
		orgIDtextField = loginDriver.findElement(By.id("txtOrgId"));
		orgIDtextField.clear();
		orgIDtextField.sendKeys(orgID);
	}
	
	public void typePassword(String password){
		WebElement passwordTextField; 
		passwordTextField = loginDriver.findElement(By.id("txtPassword"));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
	}
	
	public void clickLogin(){
		Util myUtil = new Util();
		myUtil.clickAndWait(loginDriver, loginButtonXPath, xImgGeneral);
	}
	
	/** Method to Send Large Data to input text/textArea
	 * @author caliaga
	 * @param XPathElement - String: XPath of element input/textArea
	 * @param content - String: Large Data to send
	 * @return void
	 */
	public void SendData(String XPathElement, String content) throws InterruptedException
	{
		Util myUtil = new Util();
		myUtil.SendDataLarge(loginDriver, XPathElement, content);	
	}
	
	/** Generic Method for LogIn, this return object that class was sent(parameter resultClass)
	 * @author caliaga
	 * @param userName String 
	 * @param orgId String
	 * @param password String
	 * @param resultClass Class - expected return.
	 * @return Object
	 */
	public <T> T  logIn(String userName, String orgId, String password, Class<T> resultClass)throws Exception   {	
		this.typeUserName(userName);
		this.typeOrgId(orgId);
		this.typePassword(password);
		this.clickLogin();
		
		return resultClass.getDeclaredConstructor(WebDriver.class).newInstance(loginDriver);
	}
}