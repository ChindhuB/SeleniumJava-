package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maven.cms.ClinicalManagementSystem.PageObjects.ClientPg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.HomePg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.LoginPg;
public class TC023_ClientManageMakeInactiveTest extends baseClass{
	@Test
	public void verifyClientpgManageMakeInactive() throws IOException, InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		LoginPg lp = new LoginPg(driver);
		HomePg hp = new HomePg(driver);
		ClientPg cp = new ClientPg(driver);
		logger.info("Client Page Website : ");
		// Sign In to clinical management site
		lp.setUsername(username);
		logger.info("Client Page Username is provided");
		lp.setPassword(password);
		logger.info("Client Page Password is provided");
		lp.clickSignin();
		logger.info("Client Page SignIn clicked");
		// Verification of Home Page
		if (lp.valLoginPg()) {
			Assert.assertTrue(true);
			try {
				hp.clicktrialCancelbttn();
				logger.info("Trial Version pop up");
			} catch (Exception e) {
				System.out.println("No Trial Version pop up");
				logger.info("No Trial Version pop up");
			}
			logger.info("Client Page SignIn Passed");
		} else {
			logger.info("Client Page SignIn Failed");
			captureScreen(driver, "cpsignInTest");
			Assert.assertTrue(false);
			logger.info("Client Page SignIn Failed.....");// logger msg
		}
		hp.clickClientsbttn();
		if (hp.valClientsbttn()) {
			Assert.assertTrue(true);
			logger.info("Client Page Client button verification Passed");

		} else {
			logger.info("Client Page Client button verification Failed");
			captureScreen(driver, "cpclientbttnTest");
			Assert.assertTrue(false);
		}
//calling Client page Manage-->MakeInactive functions
		if (!cp.verifyClientcontactpresent()) {
			try {
				cp.cpmanageMakeInactive();
				logger.info("Client Page Manage-->Make Inactive Option click Passed");
				if (cp.valcpmanageMakeInactive()) {
					Assert.assertTrue(true);
					logger.info("Client Page Manage-->Make Inactive Option verification Passed");
				} else {
					logger.info("Client Page Manage-->Make Inactive Option verification Failed : client page merge validation");
					captureScreen(driver, "cpManageMakeInactiveClientTest");
					Assert.assertTrue(false);
				}
			} catch (Exception e) {
				logger.info("Client Page Manage-->Make Inactive Option verification Failed");
				captureScreen(driver, "cpManageMakeInactiveTest");
				Assert.assertTrue(false);
				e.printStackTrace();
			}
		}
		lp.clickSettingsicon();
		lp.clickSignout();
		logger.info("Client Page SignOut from system");
	}
}
