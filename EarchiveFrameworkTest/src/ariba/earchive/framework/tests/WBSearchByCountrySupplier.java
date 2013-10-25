package ariba.earchive.framework.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import ariba.earchive.framework.common.Util;
//import ariba.earchive.framework.drivers.File;
import ariba.earchive.framework.models.LoginPage;
import ariba.earchive.framework.models.WorkBenchPage;
import ariba.earchive.framework.setup.MainTestCase;

public class WBSearchByCountrySupplier extends MainTestCase{
	@Test
	public void searhByCountrySupplier(){
		try{
			String currentWelcomeMessage;
			String expectedWelcomeMessage = Util.getProperty("cVatNumberSearch");
			
			//1. Login 
			LoginPage lp = new LoginPage(driver());
			lp.navigateTo();
			WorkBenchPage myWorkBenchPage= lp.logIn(Util.getProperty("cUsernameBR"), Util.getProperty("cOrgIDBR"), Util.getProperty("cPasswordBR"), WorkBenchPage.class);
						
			//2. Make a Search by Invoice Reference
			currentWelcomeMessage = myWorkBenchPage.searchBySupplierCountry("BRAZIL",Util.getProperty("cStartDateBr"),Util.getProperty("cEndDateBr"));
			
			 Util util = new Util();
			 util.TakeScreenShot(driver(), "SearchByCountrySupplierBR");
			//4. Check the message return
			AssertJUnit.assertEquals(expectedWelcomeMessage, currentWelcomeMessage);

			
		}
		catch(Exception e){
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
}
