package com.maven.cms.ClinicalManagementSystem.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePg {
	public WebDriver driver;
	public WebDriverWait wait;
	public Actions pgaction;
	public boolean bval;
	By hmhomeicon = By.xpath("//a[@id='ember16']//img");
	By hmhomeicon1 = By.xpath("//a[@class='logo']//img");
	//By hmtrialcancelbutton = By.xpath("//button[@class='swal2-cancel button secondary-dismissive']");
	By hmtrialcancelbutton = By.xpath("//button[contains(text(),'Cancel']");
	By hmcalendar = By.id("ember27");
	By hmvalcalendar = By.xpath("//th[@class='fc-day-header fc-widget-header fc-sun']");
	By hmprivacybttn = By.xpath("//div[@class='privacy']");
	By hmclients = By.xpath("//a[@id='ember28']");
	String hmvalclients = "Clients & Contacts - ";
	By hmbilling = By.xpath("//div[@id='ember26']//a[3]");
	String hmvalbilling = "Billing - ";
	By hmreports = By.id("ember29");
	By hmvalreports = By.xpath("//h2[contains(text(),'Reports')]");
	By hmaccountactivity = By.id("ember30");
	By hmvalaccountactivity = By.xpath("//h2[contains(text(),'Account Activity')]");
	By hmreminders = By.partialLinkText("Reminders");
	By hmvalreminders = By.xpath("//h2[contains(text(),'Reminders')]");
	By hmcreatebttn = By.xpath("//button[@class='button-link button-navbar create']");
	By hmcreateclient = By.xpath("//a[contains(text(),'Create Client')]");
	By hmvalcreateclient = By.xpath("//h4[text()='Create Client']");
	By hmclscreateclient = By.xpath("//button[@class='button-link']//i[@class='fa fa-times']");
	By hmcreateappoint = By.xpath("//a[@class='ember-view js-create-appointment-link']");
	By hmvalcreateappoint = By.xpath("//h4[contains(text(),'New Appointment')]");
	By hmclscreateappoint = By.xpath("//a[@class='button secondary-dismissive cancel pull-right']");
	By hmmessage = By.xpath("//button[@class='ToggleButton']");
	By hmvalmessage = By.xpath("//button[@class='ListHeaderBar-newMessageButton']");
	By hmupgrade = By.id("ember21");
	String hmvalupgrade = "//button[text()='Maybe later']";
	By hmsearch = By.xpath("//input[@placeholder='Search Clients']");
	By hmsearchreslt = By.xpath("//li[@class='ui-menu-item']");
	By hmhelp = By.id("icon/help--reversed");
	By hmvalhelp = By.xpath("//div[@class='title']");
	public String hmser;

	// Initializing Home Page
	public HomePg(WebDriver tdriver) {
		bval = false;
		driver = tdriver;
		PageFactory.initElements(driver, this);
		pgaction = new Actions(this.driver);
		wait = new WebDriverWait(this.driver, 100);
	}

	// Click on Home Page Buttons
	public void clicktrialCancelbttn() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmtrialcancelbutton)));
		driver.findElement(hmtrialcancelbutton).click();
	}

	public void clickCalendarbttn() {
		driver.findElement(hmcalendar).click();
	}

	public String[] clickPrivacybttn() {
		String privacy[] = new String[2];
		driver.findElement(hmhomeicon).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmprivacybttn)));
		privacy[0] = driver.findElement(hmprivacybttn).getText();
		driver.findElement(hmprivacybttn).click();
		privacy[1] = driver.findElement(hmprivacybttn).getText();
		System.out.println(privacy[0]);
		System.out.println(privacy[1]);
		return privacy;
	}

	public void clickClientsbttn() {
		driver.findElement(hmhomeicon).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmclients)));
		driver.findElement(hmclients).click();
	}

	public void clickBillingbttn() {
		driver.findElement(hmhomeicon).click();
		driver.findElement(hmbilling).click();
	}

	public void clickReportsbttn() {
		driver.findElement(hmhomeicon1).click();
		driver.findElement(hmreports).click();
	}

	public void clickAccountactivitybttn() {
		driver.findElement(hmhomeicon).click();
		driver.findElement(hmaccountactivity).click();
	}

	public void clickRemindersbttn() {
		driver.findElement(hmhomeicon).click();
		driver.findElement(hmreminders).click();
	}

	public void clickCreatebttn() {

		try {
			driver.findElement(hmhomeicon1).click();
		} catch (Exception e) {
			driver.findElement(hmhomeicon).click();
			System.out.println("Trying another element locator for icon");
		} finally {
			System.out.println("Tried every possiblity");
		}

		driver.findElement(hmcreatebttn).click();
	}

	public void clickCreateClientbttn() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmcreateclient)));
		driver.findElement(hmcreateclient).click();
	}

	public void clickCreateAppointmentbttn() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmcreateappoint)));
		driver.findElement(hmcreateappoint).click();
	}

	public void clickMessagebttn() {
		driver.findElement(hmhomeicon).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmmessage)));
		driver.findElement(hmmessage).click();
	}

	public void clickUpgradebttn() {
		driver.findElement(hmhomeicon).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmupgrade)));
		driver.findElement(hmupgrade).click();
	}

	public void sendHPSearchvar(String ser) {
		hmser = ser;
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmsearch)));
		driver.findElement(hmsearch).sendKeys(ser);

	}

	public void clickHelpbttn() {

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmhelp)));
		driver.findElement(hmhelp).click();
	}

	// Validating Home Page Buttons
	public boolean valCalendarbttn() {
		bval = false;

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalcalendar)));
		bval = driver.findElement(hmvalcalendar).isDisplayed();

		return bval;
	}

	public boolean valClientsbttn() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bval = driver.getTitle().toLowerCase().contains(hmvalclients.toLowerCase());
		return bval;

	}

	public boolean valBillingbttn() {
		bval = driver.getTitle().toLowerCase().contains(hmvalbilling.toLowerCase());
		return bval;
	}

	public boolean valReportsbttn() {
		bval = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalreports)));
			bval = driver.findElement(hmvalreports).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valAccountactivitybttn() {
		bval = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalaccountactivity)));
			bval = driver.findElement(hmvalaccountactivity).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valRemindersbttn() {
		bval = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalreminders)));
			bval = driver.findElement(hmvalreminders).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valCreatebttn() {
		bval=false;
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmcreateclient)));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmcreateappoint)));
		if(driver.findElement(hmcreateappoint).isDisplayed()&&driver.findElement(hmcreateclient).isDisplayed())
		{
			bval=true;
		}
		
		return bval;
	}

	public boolean valCreateClientbttn() {
		bval = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalcreateclient)));
			bval = driver.findElement(hmvalcreateclient).isDisplayed();
			driver.findElement(hmclscreateclient).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valCreateAppointmentbttn() {
		bval = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalcreateappoint)));
			bval = driver.findElement(hmvalcreateappoint).isDisplayed();
			driver.findElement(hmclscreateappoint).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valMessagebttn() {
		bval = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalmessage)));
			bval = driver.findElement(hmvalmessage).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valUpgradebttn() {
		bval = false;
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(hmvalupgrade))));
			bval = driver.findElement(By.xpath(hmvalupgrade)).getText().contains("Maybe later");
			driver.findElement(By.xpath(hmvalupgrade)).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valsendHPSearchvar() {
		bval = false;
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmsearchreslt)));
			bval = driver.findElement(hmsearchreslt).getText().toLowerCase().contains(hmser.toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}

	public boolean valHelpbttn() {
		bval = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(hmvalhelp)));
			bval = driver.findElement(hmvalhelp).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bval;
	}
}
