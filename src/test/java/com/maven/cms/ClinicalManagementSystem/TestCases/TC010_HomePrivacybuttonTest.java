package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maven.cms.ClinicalManagementSystem.PageObjects.HomePg;
import com.maven.cms.ClinicalManagementSystem.PageObjects.LoginPg;

public class TC010_HomePrivacybuttonTest extends baseClass {
	@Test
	public void verifyHomepgPrivacybttn() throws IOException , InterruptedException{
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
			}catch(Exception e) {
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
		// Validate Home page Privacy buttons
		hp.clickCalendarbttn();
		if (hp.valCalendarbttn()) {
			Assert.assertTrue(true);
			logger.info("Home Page Calendar button verification Passed");
		} else {
			logger.info("Home Page Calendar button verification Failed");
			captureScreen(driver, "hpcalendarbttnTest");
			Assert.assertTrue(false);
		}
		
		String assrtPrivacy[] = hp.clickPrivacybttn();
		if (assrtPrivacy[0].toLowerCase().contains("privacy off")) {
			Assert.assertTrue(assrtPrivacy[1].toLowerCase().contains("privacy on"));
			logger.info("Home Page Privacy button verification Passed");
		} else if (assrtPrivacy[0].toLowerCase().contains("privacy on")) {
			Assert.assertTrue(assrtPrivacy[1].toLowerCase().contains("privacy off"));
			logger.info("Home Page Privacy button verification Passed");
		} else {
			logger.info("Home Page Privacy button verification Failed");
			captureScreen(driver, "hpprivacybttnTest");
			Assert.assertTrue(false);
		}
		lp.clickSettingsicon();
		lp.clickSignout();
		logger.info("Sign out from system");
	}
}
