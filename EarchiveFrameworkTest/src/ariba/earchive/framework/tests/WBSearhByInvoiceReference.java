package ariba.earchive.framework.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
//import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import ariba.earchive.framework.common.Util;
import ariba.earchive.framework.models.LoginPage;
import ariba.earchive.framework.models.WorkBenchPage;
import ariba.earchive.framework.setup.MainTestCase;

public class WBSearhByInvoiceReference extends MainTestCase {
		@Test	
		public void searchByInvoiceReference(){ 			
			try {
				
				String currentWelcomeMessage;
				String expectedWelcomeMessage = Util.getProperty("cVatNumberSearch");
				
				//1. Login 
				LoginPage lp = new LoginPage(driver());
				lp.navigateTo();
				WorkBenchPage myWorkBenchPage= lp.logIn(Util.getProperty("cUsername"), Util.getProperty("cOrgID"), Util.getProperty("cPassword"), WorkBenchPage.class);
				
				//2. Make a Search by Invoice Reference
				currentWelcomeMessage = myWorkBenchPage.searchByInvoiceReference("130913108");
				
				//3. Check the message return
				AssertJUnit.assertEquals(expectedWelcomeMessage, currentWelcomeMessage);
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			} 
		}
}
