package ariba.earchive.framework.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import ariba.earchive.framework.common.Util;
import ariba.earchive.framework.models.BasePage;
import ariba.earchive.framework.models.LoginPage;
import ariba.earchive.framework.setup.MainTestCase;

public class LoginBRA extends MainTestCase{
		@Test
		public void LoginUserBrasilOk(){ 			
			try {
				
				String currentRoleNameUserLogged;
				String expectedRoleNameUserLogged = Util.getProperty("cValidationTest2");

				//1. Login 
				LoginPage lp = new LoginPage(driver());
				lp.navigateTo();
				
				//2. Return an object of type WB
				BasePage myBasePage = lp.logIn(Util.getProperty("cUsernameBR"), Util.getProperty("cOrgIDBR"), Util.getProperty("cPasswordBR"), BasePage.class);
				
				//3. Get Current RoleName of User Logged
				currentRoleNameUserLogged = myBasePage.getRoleNameUserLogged();
				System.out.println("Msje Welcome Brasil: " + currentRoleNameUserLogged);
				
				//4. Check the RoleName with expected value
				AssertJUnit.assertEquals(expectedRoleNameUserLogged, currentRoleNameUserLogged);
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			} 
		}
}
