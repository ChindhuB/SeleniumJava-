package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maven.cms.ClinicalManagementSystem.PageObjects.HomePg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.LoginPg;

public class TC015_HomeCreatebuttonTest extends baseClass {
	@Test
	public void verifyHomepgCreatebttn() throws IOException, InterruptedException {
		driver.get(url);
		LoginPg lp = new LoginPg(driver);
		HomePg hp = new HomePg(driver);
		logger.info("Home Page Website : ");
		// Sign In to clinical management site
		lp.setUsername(username);
		logger.info("Home Page Username is provided");
		lp.setPassword(password);
		logger.info("Home Page Password is provided");
		lp.clickSignin();
		logger.info("Home Page SignIn clicked");
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
			captureScreen(driver, "hpsignInTest");
			Assert.assertTrue(false);
			logger.info("Home Page SignIn Failed.....");// logger msg
		}
		hp.clickCreatebttn();
		if (hp.valCreatebttn()) {
			Assert.assertTrue(true);
			logger.info("Home Page Create button verification Passed");
		} else {
			logger.info("Home Page Create button verification Failed");
			captureScreen(driver, "hpcreatebttnTest");
			Assert.assertTrue(false);
		}
		lp.clickSettingsicon();
		lp.clickSignout();
		logger.info("Sign out from system");
	}
}
