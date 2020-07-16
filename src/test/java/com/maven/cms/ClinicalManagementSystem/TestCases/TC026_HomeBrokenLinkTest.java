 package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maven.cms.ClinicalManagementSystem.PageObjects.HomePg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.LoginPg;
import com.maven.cms.ClinicalManagementSystem.Utils.BrokenLink;

public class TC026_HomeBrokenLinkTest extends baseClass {
	@Test
	public void verifyHomepgBrokenLink() throws IOException, InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		LoginPg lp = new LoginPg(driver);
		HomePg hp = new HomePg(driver);
		BrokenLink bl = new BrokenLink(driver);
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
			logger.info("Home Page SignIn Passed");
		} else {
			logger.info("Home Page SignIn Failed");
			captureScreen(driver, "cpsignInTest");
			Assert.assertTrue(false);
			logger.info("Home Page SignIn Failed.....");// logger msg
		}
	//Verify Broken Links
		if (bl.linkStatus()) {
			Assert.assertTrue(true);
			logger.info("Home Page Phone Broken Link verification Passed,No Broken Links");
		} else {
			logger.info("Home Page Phone Broken Link verification Failed ,Links are shown below");
			logger.info(bl.brokenlinks.toString());
			captureScreen(driver, "hpBrokenLinkHomeTest");
			Assert.assertTrue(false);
		}
		lp.clickSettingsicon();
		lp.clickSignout();
		logger.info("Home Page Sign Out from system");
	}
}
