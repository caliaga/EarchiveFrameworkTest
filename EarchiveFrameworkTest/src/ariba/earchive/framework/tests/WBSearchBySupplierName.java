package ariba.earchive.framework.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import ariba.earchive.framework.common.Util;
import ariba.earchive.framework.models.LoginPage;
import ariba.earchive.framework.models.WorkBenchPage;
import ariba.earchive.framework.setup.MainTestCase;

public class WBSearchBySupplierName extends MainTestCase{
	@Test	
	public void SearchBySupplierNameOK(){ 			
		try {
			
			String currentInvoiceSupplierName;
			String expectedInvoiceSupplierName = Util.getProperty("cValidationTest3");
			
			//1. Login 
			LoginPage lp = new LoginPage(driver());
			lp.navigateTo();
			
			//2. Return an object of type WB
			WorkBenchPage myWorkBenchPage = lp.logIn(Util.getProperty("cUserAdmin"), Util.getProperty("cOrgID"), Util.getProperty("cPassAdmin"), WorkBenchPage.class);
			
			//3. Get Invoice from Supplier Name selected
			currentInvoiceSupplierName = myWorkBenchPage.searchBySupplierName(Util.getProperty("cSupplierName"), Util.getProperty("cStartDate"), Util.getProperty("cEndDate"));
			System.out.println("Invoice : " + currentInvoiceSupplierName);
			
			//4. Check the Invoice from Supplier Name selected
			AssertJUnit.assertEquals(expectedInvoiceSupplierName, currentInvoiceSupplierName);
			
			//5. Take evidence
			 Util util = new Util();
			 util.TakeScreenShot(driver(), "InvoiceSupplierName");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} 
	}
}
