package com.maven.cms.ClinicalManagementSystem.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPg {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions pgaction;
	@FindBy(id = "user_username")
	@CacheLookup
	WebElement logUsername;
	@FindBy(id = "user_password")
	@CacheLookup
	WebElement logPassword;
	@FindBy(xpath= "//input[@name='commit']")
	@CacheLookup
	WebElement logSignin;
	@FindBy(xpath="//a[@id='ember16']//img")
	@CacheLookup
	WebElement logimage;
	By logSettingsicon=By.xpath("//button[@class='button-link button-navbar my-account js-my-account']|//a[@class='button-navbar my-account js-my-account']");
	@FindBy(xpath="//a[contains(text(),'Sign Out')]") 
	@CacheLookup
	WebElement logSignout;
	String title = "Calendar - ";
	// Initializing Login Page
	public LoginPg(WebDriver tdriver) {
		driver = tdriver;
		PageFactory.initElements(driver, this);
		pgaction = new Actions(this.driver);
		wait = new WebDriverWait(this.driver, 30);
	}

	// SignIn Actions
	public void setUsername(String lusername) {
		logUsername.clear();
		logUsername.sendKeys(lusername);
	}

	public void setPassword(String lpassword) {
		logPassword.clear();
		logPassword.sendKeys(lpassword);
	}

	public void clickSignin() {
		logSignin.click();
	}

	// Validate SignIn
	public boolean valLoginPg() {
		wait.until(ExpectedConditions.visibilityOf(logimage));
		return driver.getTitle().contains(title);
	}

	// SignOut Actions
	public void clickSettingsicon() {
	
		pgaction.moveToElement(driver.findElement(logSettingsicon)).build().perform();
		driver.findElement(logSettingsicon).click();
		
	}

	public void clickSignout() {
		wait.until(ExpectedConditions
				.visibilityOf(logSignout));
		pgaction.moveToElement(logSignout).build().perform();
		logSignout.click();
	}
}
