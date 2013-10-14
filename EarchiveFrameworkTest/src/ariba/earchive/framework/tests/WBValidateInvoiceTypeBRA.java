package ariba.earchive.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ariba.earchive.framework.common.Util;
import ariba.earchive.framework.models.BasePage;
import ariba.earchive.framework.models.LoginPage;
import ariba.earchive.framework.models.WorkBenchPage;
import ariba.earchive.framework.setup.MainTestCase;

public class WBValidateInvoiceTypeBRA extends MainTestCase {
	@Test
	public void WBValidateDocTypeBra(){
		try{
			
			//1. Login 
			LoginPage lp = new LoginPage(driver());
			lp.navigateTo();
			WorkBenchPage myWorkBenchPage= lp.logIn(Util.getProperty("cUsernameBR"), Util.getProperty("cOrgIDBR"), Util.getProperty("cPasswordBR"), WorkBenchPage.class);
			myWorkBenchPage.searchByDateAndDocType("", "", "");
			
	
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} 
	}
}
