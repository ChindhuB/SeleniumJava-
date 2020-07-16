package com.maven.cms.ClinicalManagementSystem.TestCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.maven.cms.ClinicalManagementSystem.Config.ReadConfig;

public class baseClass {
	ReadConfig config = new ReadConfig();
	public String url = config.getUrl();
	public String username = config.getUsername();
	public String password = config.getPassword();
	public String path = config.getChromepath();
	public static WebDriver driver;
	public static Logger logger; // Added Logger

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) {
		logger = Logger.getLogger("Clinical Managment System");
		PropertyConfigurator.configure("Log4j.properties");
		if (browser.equalsIgnoreCase("firefox")) {
			// Firefox Browser
			System.setProperty("webdriver.gecko.driver", path);
			driver = new FirefoxDriver();
		}

		else if (browser.equalsIgnoreCase("chrome")) {
			// opens the browser
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		}

	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}
	public void captureScreen(WebDriver driver, String screenschotname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File scr=ts.getScreenshotAs(OutputType.FILE);
		File trgt=new File(System.getProperty("user.dir") + "/src/test/Screenshots/" + screenschotname + ".png");
		FileUtils.copyFile(scr, trgt);
		System.out.println("Screenshot taken");
	}
}
