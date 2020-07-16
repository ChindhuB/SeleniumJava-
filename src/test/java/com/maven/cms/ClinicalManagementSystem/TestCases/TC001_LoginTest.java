package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maven.cms.ClinicalManagementSystem.PageObjects.LoginPg;

public class TC001_LoginTest extends baseClass {
@Test
	public void verifySignIn() throws IOException, InterruptedException {
		driver.get(url);
		LoginPg lp = new LoginPg(driver);
		logger.info("SignIn Page Website : ");
		lp.setUsername(username);
		logger.info("SignIn Page Username is provided");
		lp.setPassword(password);
		logger.info("SignIn Page Password is provided");
		lp.clickSignin();
		logger.info("SignIn Page SignIn clicked");
//verification
		if (lp.valLoginPg()) {
			logger.info("SignIn Page SignIn Passed");
			lp.clickSettingsicon();
			lp.clickSignout();
			Assert.assertTrue(true);
			logger.info("SignIn Page SignOut Passed");
		} else {
			logger.info("SignIn Page SignIn Failed");
			captureScreen(driver, "spsignInTest");
			Assert.assertTrue(false);
			logger.info("SignIn Page SignIn Failed.....");// logger msg
		}

	}
}
