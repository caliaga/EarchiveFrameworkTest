package ariba.earchive.framework.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ariba.earchive.framework.common.Util;

public class WorkBenchPage {
	
	
	//Local Variables
	WebDriver workBenchDriver = null;
	String xLnkRole = Util.getProperty("XPATH_ROLE");
	String xBtnLogout = Util.getProperty("XPATH_BUTTON_LOGOFF");
	String XBtnSearch= Util.getProperty("XPATH_BUTTON_SEARCH");
	String IdTableWB =Util.getProperty("cIDTableDataWorkBench");
	
	//Constructor
	public WorkBenchPage(WebDriver driver) throws Exception
	{
		this.workBenchDriver = driver;
		this.movetoWorkBench("Archiving", "Search", "Search Documents");
	}
	
	//Local Methods
	private void setStartDate(String startDateIn)
	{
		WebElement startDate; 
		startDate = workBenchDriver.findElement(By.id("ctl00_DefaultContent_calStartDate_dateInput_text"));
		startDate.clear();
		startDate.sendKeys(startDateIn);
	}
	
	private void setEndDate(String endDateIn)
	{
		WebElement endDate; 
		endDate = workBenchDriver.findElement(By.id("ctl00_DefaultContent_calEndDate_dateInput_text"));
		endDate.clear();
		endDate.sendKeys(endDateIn);
	}
	
	private void selectSuplierName(String name) throws InterruptedException
	{
		Util myUtil = new Util();
		myUtil.SetValueCombo(workBenchDriver, "ctl00_DefaultContent_cmbSupplierName", name);
	}
	
	
	private void selectBuyerCountry(String country) throws InterruptedException
	{
		Util myUtil = new Util();
		myUtil.SetValueCombo(workBenchDriver, "ctl00_DefaultContent_cmbBuyerCountry", country);
	}
	
	private void selectSupplierCountry(String country) throws InterruptedException
	{
		Util myUtil = new Util();
		myUtil.SetValueCombo(workBenchDriver, "ctl00_DefaultContent_cmbSupplierCountry", country);
	}
	
	private void selectDocumentType(String docType) throws InterruptedException
	{
		Util myUtil = new Util();
		myUtil.SetValueCombo(workBenchDriver, "ctl00_DefaultContent_cmbInvoiceType_DropDown", docType);
	}
	
	private void setInvoiceReference(String InvoiceReference)
	{
		WebElement UserNametextField; 
		UserNametextField = workBenchDriver.findElement(By.id("ctl00_DefaultContent_txtInvoiceReference_text"));
		UserNametextField.clear();
		UserNametextField.sendKeys(InvoiceReference);
	}
	
	private void clickSearch() throws Exception
	{
		Util myUtil = new Util();
		myUtil.clickAndWait(workBenchDriver, XBtnSearch, XBtnSearch);
		Thread.sleep(3000);
	}

	private void movetoWorkBench(String LinkName1, String LinkName2, String LinkName3) throws Exception {
		Util.moveMenuClickElement(workBenchDriver, LinkName1, LinkName2, LinkName3);
	}
	
	//Public Methods
	
	/** Search Invoice(s) By Invoice Reference
	 * @author caliaga
	 * @param InvoiceReference - String: Invoice Reference.
	 */
	public String searchByInvoiceReference(String InvoiceReference) throws Exception
	{
		String valueFinded;
		
		this.setInvoiceReference(InvoiceReference);
		this.clickSearch();
		valueFinded = Util.getValueGrid(workBenchDriver, IdTableWB, 1, 3);
		return valueFinded;
	}
	
	/** Search Invoice(s) By Supplier Country
	 * @author caliaga
	 * @param country - String: Country of Supplier.
	 */
	public String searchBySupplierCountry(String country, String startDateIn,String endDateIn  ) throws Exception
	{
		String valueFinded;
		this.setStartDate(startDateIn);
		this.setEndDate(endDateIn);
		this.selectSupplierCountry(country);
		this.clickSearch();
		valueFinded = Util.getValueGrid(workBenchDriver, IdTableWB, 1, 3);
		return valueFinded;
		
	}
	
	/** Search Invoice(s) By Buyer Country
	 * @author caliaga
	 * @param country - String: Country of Buyer.
	 */
	public String searchByBuyerCountry(String country) throws Exception
	{
		String valueFinded;
		
		this.selectBuyerCountry(country);
		this.clickSearch();
		valueFinded = Util.getValueGrid(workBenchDriver, IdTableWB, 1, 3);
		return valueFinded;		
	}
	
	/** Search Invoice(s) By Invoice Type
	 * @author caliaga
	 * @param invoiceType - String: Invoice Type
	 */
	public String searchByInvoiceType(String invoiceType) throws Exception
	{
		String valueFinded;
		
		this.selectDocumentType(invoiceType);
		this.clickSearch();
		valueFinded = Util.getValueGrid(workBenchDriver, IdTableWB, 1, 3);
		return valueFinded;		
	}
	
	/** Search Invoice(s) By SupplierName
	 * @author jbarbaran
	 * @param name - String: name of supplier
	 */
	public String searchBySupplierName(String name, String startDateIn, String endDateIn ) throws Exception
	{
		String valueFinded;
		
		this.selectSuplierName(name);
		this.setStartDate(startDateIn);
		this.setEndDate(endDateIn);
		this.clickSearch();
		valueFinded = Util.getValueGrid(workBenchDriver, IdTableWB, 8, 3);
		return valueFinded;		
	}
	
	/** Search Invoice(s) By Start Date and Invoice Type
	 * @author caliaga
	 * @param startDate - String: Start Date of Search
	 * @param endDate - String: End Date of Search
	 * @param invoiceType - String: Invoice Type
	 */
	public String searchByDateAndDocType(String startDate, String endDate, String invoiceType) throws Exception
	{
		String valueFinded;
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.selectDocumentType(invoiceType);
		this.clickSearch();
		valueFinded = Util.getValueGrid(workBenchDriver, IdTableWB, 1, 3);
		return valueFinded;		
	}
}