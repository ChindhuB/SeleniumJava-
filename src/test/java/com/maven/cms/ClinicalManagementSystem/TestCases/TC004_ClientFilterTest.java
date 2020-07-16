package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maven.cms.ClinicalManagementSystem.PageObjects.ClientPg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.HomePg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.LoginPg;

public class TC004_ClientFilterTest extends baseClass {
	/*
	 * Only to check filter options present in website Manual Testing needed to
	 * check Filter and Sort working properly
	 */
	public String[] cpfilterArray = { "Contacts", "Clients & Contacts", "Recently viewed Clients", "Inactive Clients",
			"Clients" };

	@Test
	public void verifyClientpgFilter() throws IOException, InterruptedException {
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
//calling Client Filter functions
		if (!cp.verifyClientcontactpresent()) {
			// Filter verification
			for (int i = 0; i < cpfilterArray.length; i++) {
				cp.cpFilter(cpfilterArray[i]);
				if (cp.valcpFilter()) {
					Assert.assertTrue(true);
					logger.info("Client Page Filter verification Passed for :" + cpfilterArray[i]);
				} else {
					logger.info("Client Page Filter verification Failed for :" + cpfilterArray[i]);
					captureScreen(driver, "cpclientFilterTest");
					Assert.assertTrue(false);
				}

			}
		}
		lp.clickSettingsicon();
		lp.clickSignout();
		logger.info("Client Page SignOut from system");

	}

}
