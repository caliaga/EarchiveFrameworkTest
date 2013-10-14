package ariba.earchive.framework.common;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.PropertiesFile;

public class Util {
	
	public static String getProperty(String tagName){
		PropertiesFile properties = null;
		String pathName = getResourcesPath();
		try {
			properties = new PropertiesFile(pathName+getPropertyFileName(pathName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperties().getProperty(tagName);
	}
	
	public static String getResourcesPath(){
		return System.getProperty("user.dir")+"\\res\\";
	}
	
	private static String getPropertyFileName(String pathName){
		File file = new File(pathName);
		return file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".properties");
			}
		})[0].getName().toString();
	}
	
	
	/**
	 * Customized findElement method
	 * @param driver
	 * @param xpath The web element xpath
	 * @return WebElement - The web element to be used.
	 */
	public WebElement findElement(WebDriver driver, String xpath){
		return driver.findElement(By.xpath(xpath));
	}
	
	/**
	 * Customized findElements method
	 * @param driver
	 * @param xpath The web element xpath
	 * @return List&lt;WebElement&gt; - An array list of web element
	 */
	public List<WebElement> findElements(WebDriver driver, String xpath){
		return driver.findElements(By.xpath(xpath));
	}
	
	/**
	 * Customized click method
	 * @param driver
	 * @param xpath
	 * @throws InterruptedException
	 */
	public void click(WebDriver driver, String xpath){
		By byElem = By.xpath(xpath);
		WebElement element = driver.findElement(byElem);
		Actions builder = new Actions(driver);
		builder.moveToElement(element);
		element.click();
	}
	
	/**
	 * This method clicks a web element and wait for another one to be displayed
	 * in the screen before selenium continues to run the test case.
	 * @param driver
	 * @param xpath
	 * @throws InterruptedException
	 */
	public void clickAndWait(WebDriver driver, String xpath, String expectedElementXpath){
		By byElem = By.xpath(xpath);
		WebElement element = driver.findElement(byElem);
		Actions builder = new Actions(driver);
		builder.moveToElement(element);
		element.click();
		waitForElementPresent(driver, expectedElementXpath);
	}
	
	
	
	/**
	 * Makes a pause for the n seconds, then selenium replays the test sequence
	 * @param driver
	 * @param seconds The seconds of the pause
	 */
	public void sleep(WebDriver driver, long miliseconds){
		driver.manage().timeouts().implicitlyWait(miliseconds, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * This method waits for an element to be displayed in the screen before 
	 * selenium continues with the next test step
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementPresent(WebDriver driver, String xpath){
		By by = By.xpath(xpath);
		Wait<WebDriver> wait = new WebDriverWait(driver, 70);
	    wait.until(visibilityOfElementLocated(by));
    }
	
	/**
	 * This method waits for an element to be hidden in the screen before 
	 * selenium continues with the next test step
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementNotPresent(WebDriver driver, String xpath){
		By by = By.xpath(xpath);
		Wait<WebDriver> wait = new WebDriverWait(driver, 70);
	    wait.until(notVisibilityOfElementLocated(by));
    }
	
	/**
	 * A customized Expected Condition. Required for utility methods related 
	 * to wait features
	 * @param by
	 * @return
	 */
	private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by){
	    return new ExpectedCondition<WebElement>() {
	      public WebElement apply(WebDriver driver) {
	        WebElement element = driver.findElement(by);
	        System.out.println(element.isDisplayed());
	        return element.isDisplayed() ? element : null;
	      }
	    };
	}
	
	/**
	 * A customized Expected Condition. Required for utility methods related 
	 * to wait for something not displayed. 
	 * @param by
	 * @return
	 */
	private ExpectedCondition<WebElement> notVisibilityOfElementLocated(final By by){
	    return new ExpectedCondition<WebElement>() {
	      public WebElement apply(WebDriver driver) {
	        WebElement element = driver.findElement(by);
	        System.out.println(element.isDisplayed());
	        if (element.isDisplayed() == false){
	        	return element;
	        } else {
	        	return null;
	        }
	      }
	    };
	}
	
	/**
	 * Customized sendKeys method
	 * @param driver
	 * @param inputXpath
	 * @param textToType
	 */
	public void sendKeys(WebDriver driver, String inputXpath, String textToType){
		By byElement = By.xpath(inputXpath);
		WebElement input = driver.findElement(byElement);
		input.sendKeys(textToType);
	}
	
	/**
	 * Extended sendKeys method that cleans the text field value
	 * before start typing the given string 
	 * @param driver
	 * @param inputXpath - The target text field 
	 * @param textToType - The given string to be typed in the text field 
	 */
	public void sendKeysAndClean(WebDriver driver, String inputXpath, String textToType){
		By byElement = By.xpath(inputXpath);
		WebElement input = driver.findElement(byElement);
		input.clear();
		input.sendKeys(textToType);
	}

	
	/**
	 * Executes a script on an element
	 * @note Really should only be used when the web driver is sucking at exposing
	 * functionality natively
	 * @param script The script to execute
	 * @param element The target of the script, referenced as arguments[0]
	 */
	private void trigger(WebDriver driver, String script, WebElement element) {
	    ((JavascriptExecutor)driver).executeScript(script, element);
	}

	/** Executes a script
	 * @note Really should only be used when the web driver is sucking at exposing
	 * functionality natively
	 * @param script The script to execute
	 */
	private Object trigger(WebDriver driver, String script) {
	    return ((JavascriptExecutor)driver).executeScript(script);
	}

	/**
	 * Opens a new tab for the given URL
	 * @param url The URL to 
	 * @throws JavaScriptException If unable to open tab
	 */
	private void openTab(WebDriver driver, String url) {
	    String script = "var d=document,a=d.createElement('a');a.target='_blank';a.href='%s';a.innerHTML='.';d.body.appendChild(a);return a";
	    Object element = trigger(driver, String.format(script, url));
	    if (element instanceof WebElement) {
	        WebElement anchor = (WebElement) element; anchor.click();
	        trigger(driver, "var a=arguments[0];a.parentNode.removeChild(a);", anchor);
	    } else {
	        throw new JavaScriptException(element, "Unable to open tab", 1);
	    }       
	}
	
	/**
	 * Switches to the non-current window
	 */
	public void switchWindow(WebDriver driver) throws NoSuchWindowException, NoSuchWindowException {
	    Set<String> handles = driver.getWindowHandles();
	    String current = driver.getWindowHandle();
	    handles.remove(current);
	    String newTab = handles.iterator().next();
	    driver.switchTo().window(newTab);
	}
	
	/**
	 * Opens a new window of the current browser
	 * @param driver
	 */
	public void openWindow(WebDriver driver, String URL){
		openTab(driver, URL);
		switchWindow(driver);
	}
	
	/**
	 * Creates a combo box object in order to have full access to its properties
	 * @param driver - WebDriver
	 * @param comboXpath - String: XPath of ComboBox looked
	 * @return Select - A combo box
	 */
	public Select combo(WebDriver driver, String comboXpath){
		Select comboBox = new Select(driver.findElement(By.xpath(comboXpath)));
		return comboBox;
	}
	
	/** Take a screenshot - evidence for test Case
	 * @author caliaga
	 * @note The screenshot will save on .jpg format.
	 * @param driver - WebDriver
	 * @param namefile - String: NameFile of Screenshot, must be different on each call
	 */
	public void TakeScreenShot(WebDriver driver, String nameFile)
	{
		String Path = "C:\\test-output\\img\\" + nameFile + ".png";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(Path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/** Move and Click of element menu
	 * @author caliaga
	 * @param driver - WebDriver
	 * @param linkName - String: XPath of Name of Link
	 */
	public static void moveClickElement(WebDriver driver, String xPathLinkName) throws InterruptedException
	{
		Actions action = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath(xPathLinkName));
		action.moveToElement(mainMenu).click(mainMenu);
		action.perform();
		Thread.sleep(3000);
	}
	
	/** Move and Click of element menu Three Levels RadMenu
	 * @author caliaga
	 * @note This method working with Telerik RadMenu and SNM Architecture based on Three levels menu.
	 * @param driver - WebDriver
	 * @param linkName1 - String: First Level of Menu
	 * @param linkName2 - String: Second Level of Menu
	 * @param linkName3 - String: Third Level of Menu
	 */
	public static void moveMenuClickElement(WebDriver driver, String LinkName1, String LinkName2, String LinkName3) throws Exception 
	{
		Actions action = new Actions(driver);
		///Menu 1
		WebElement mainMenu = driver.findElement(By.linkText(LinkName1));
		action.moveToElement(mainMenu).build().perform();
		mainMenu.click();
		Thread.sleep(3000);
		///Menu 2
		mainMenu = driver.findElement(By.className("rmExpandRight"));
		action.moveToElement(mainMenu).build().perform();
		mainMenu.click();
		Thread.sleep(3000);
		///Menu 3
		mainMenu = driver.findElement(By.linkText(LinkName3));
		action.moveToElement(mainMenu).build().perform();
		mainMenu.click();
		Thread.sleep(3000);
	}
	
	/** Get Text Cell of RadGrid - Telerik, based of row and column position
	 * @author caliaga
	 * @note This method working with Telerik RadGrid
	 * @param driver - WebDriver
	 * @param nameOfTable - String: Based of GridData
	 * @param row - Integer: Row of DataTable
	 * @param column - Integer:  Column of DataTable
	 * @return String
	 */
	public static String getValueGrid(WebDriver driver, String nameOfTable, int row, int column) throws Exception 
	{
		String ValueFinded="";
		WebElement table_element = driver.findElement(By.id(nameOfTable));
        List<WebElement> tr_collection=table_element.findElements(By.xpath("id('"+nameOfTable+"')/tbody/tr"));

        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
        int row_num,col_num;
        row_num=1;
        for(WebElement trElement : tr_collection)
        {
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            System.out.println("NUMBER OF COLUMNS="+td_collection.size());
            col_num=1;
            for(WebElement tdElement : td_collection)
            {
                System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
                if(row_num==row && col_num==column){
                	ValueFinded= tdElement.getText();
                }
                col_num++;
            }
            row_num++;
        }
        
		return ValueFinded;
	}
	
	/** Get Total Count of RadGrid
	 * @author caliaga
	 * @note This method working with Telerik RadGrid
	 * @param driver - WebDriver
	 * @param nameOfTable - String: Based of GridData
	 * @return Integer
	 */
	public static int GetTotalCountOfGrid(WebDriver driver, String nameOfTable){		
		WebElement table_element = driver.findElement(By.id(nameOfTable));
        List<WebElement> tr_collection=table_element.findElements(By.xpath("id('"+nameOfTable+"')/tbody/tr"));
		return tr_collection.size();
	}

	/** Set one value for RadCombobox - Telerik
	 * @author caliaga
	 * @note This method working with Telerik RadComboBox
	 * @param driver - WebDriver
	 * @param nameComboBox - String: ID of combo box 
	 * @param valueSelected - String: Value to select
	 */
	public void SetValueCombo(WebDriver driver, String nameComboBox, String valueSelected) throws InterruptedException{
		
		Actions action = new Actions(driver);
		WebElement arrowCombo = driver.findElement(By.id("ctl00_DefaultContent_cmbBuyerCountry_Arrow"));
		action.moveToElement(arrowCombo).build().perform();
		arrowCombo.click();
		Thread.sleep(3000);
		
		List<WebElement> allElements = driver.findElements(By.xpath("//div[@id='" + nameComboBox + "']/div/ul/li")); 

		for (WebElement element: allElements) {
		      //System.out.println(element.getText());
		      
		      if(valueSelected.equals(element.getText()))
		      {
		    	  element.click();
		    	  Thread.sleep(3000);
		          break;
		      }
		}		
	}
	
	/** Send large data to Input Text/TextArea
	 * @author caliaga
	 * @note Send large data to Input Text/TextArea
	 * @param driver - WebDriver
	 * @param inputXpath - String: XPATH of Element to put the content.
	 * @param content - String: Large Content
	 */
	public void SendDataLarge(WebDriver driver, String inputXpath, String content) throws InterruptedException
	{ 
		WebDriverBackedSelenium selenium = new WebDriverBackedSelenium(driver, "");
		selenium.type(inputXpath, content);
		Thread.sleep(4000);
	}
}
