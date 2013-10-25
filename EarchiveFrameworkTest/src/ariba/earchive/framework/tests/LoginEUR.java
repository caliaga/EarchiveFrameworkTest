package ariba.earchive.framework.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

import ariba.earchive.framework.common.Util;
import ariba.earchive.framework.models.LoginPage;
import ariba.earchive.framework.models.BasePage;
import ariba.earchive.framework.setup.MainTestCase;

public class LoginEUR extends MainTestCase{
		@Test	
		public void LoginUserEuropeOK(){ 			
			try {
				
				String currentRoleNameUserLogged;
				String expectedRoleNameUserLogged = Util.getProperty("cValidationTest1");
				
				//1. Login 
				LoginPage lp = new LoginPage(driver());
				lp.navigateTo();
				
				//2. Return an object of type WB
				BasePage myBasePage = lp.logIn(Util.getProperty("cUsername"), Util.getProperty("cOrgID"), Util.getProperty("cPassword"), BasePage.class);
				
				//3. Get Current RoleName of User Logged
				currentRoleNameUserLogged = myBasePage.getRoleNameUserLogged("EUR");
				System.out.println("Msje Welcome EUR: " + currentRoleNameUserLogged);
				
				//4. Check the RoleName with expected value
				AssertJUnit.assertEquals(expectedRoleNameUserLogged, currentRoleNameUserLogged);
				
				//5. Take evidence
				 Util util = new Util();
				 util.TakeScreenShot(driver(), "LOGIN_EUR_SUCCESS");
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			} 
		}
}
