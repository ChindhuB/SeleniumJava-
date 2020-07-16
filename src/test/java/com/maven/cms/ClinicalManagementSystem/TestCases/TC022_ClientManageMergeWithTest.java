package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maven.cms.ClinicalManagementSystem.PageObjects.ClientPg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.HomePg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.LoginPg;
public class TC022_ClientManageMergeWithTest extends baseClass {
	@Test
	public void verifyClientpgManageMergeWith() throws IOException, InterruptedException {
		driver.get(url);
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
//calling Client page Manage-->Merge With functions
		if (!cp.verifyClientcontactpresent()) {
			try {
				cp.cpmanageMergeWith();
				logger.info("Client Page Manage-->MergeWith Option click Passed");
				if (cp.valcpmanageMergeWith()) {
					Assert.assertTrue(true);
					logger.info("Client Page Manage-->MergeWith Option verification Passed");
				} else {
					logger.info("Client Page Manage-->MergeWith Option verification Failed : client page merge validation");
					captureScreen(driver, "cpManageMergeWithClientPageTest");
					Assert.assertTrue(false);
				}
				driver.navigate().back();
			} catch (Exception e) {
				logger.info("Client Page Manage-->MergeWith Option verification Failed");
				captureScreen(driver, "cpManageMergeWithTest");
				Assert.assertTrue(false);
				e.printStackTrace();
			}
		}
		lp.clickSettingsicon();
		lp.clickSignout();
		logger.info("Client Page SignOut from system");
	}
}
