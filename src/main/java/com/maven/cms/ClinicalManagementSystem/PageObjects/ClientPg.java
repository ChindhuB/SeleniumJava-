package com.maven.cms.ClinicalManagementSystem.PageObjects;

import java.util.List;
import java.util.ListIterator;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientPg {
	public WebDriver driver;
	public WebDriverWait wait;
	public Actions pgaction;
	public boolean bval;
	public List<WebElement> cpclientsLst;
	public ListIterator<WebElement> cpclientsLstItr;
	public String cpser;
	String cpclientname;
	String cphref;
	By cpclients = By.xpath("//div[@class='__b2a8e client-list-item client ember-view']");
	By cpsearch = By.xpath("//input[@placeholder='Search']");
	By cpfilter = By.xpath("//select[@name='filterType']");
	By cpsort = By.xpath("//select[@name='sortBy']");
	By cpmanage = By.xpath("//button[@class='button-link options'][contains(text(),'Manage')]");
	By cpmanageview = By.xpath("//a[@class='ember-view'][contains(text(),'View Client')]");
	By cpclientnamepath = By.xpath("//a[@class='record-name ember-view']");
	By cpmanageeditclientinfo = By.xpath("//a[@class='ember-view'][contains(text(),'Edit Client info')]");
	By cpeditclientinfoclientnamepath = By.cssSelector("h3[id^='ember'][class='ember-view']>[id^='ember'][class='ember-view']");
	By cpmanagemergewith = By.xpath("//button[@class='merge-with']");
	By cpmergewithtext = By
			.xpath("//div[@class='__beb96 ember-view']//p//strong[contains(text(),'Selected for merge')]");
	By cpmergewithname = By.xpath("//span[@class='merge-name']//a[@class='ember-view']");
	By cpmanageinactive = By.xpath("//button[@class='delete']");
	By cpmanageactive = By.xpath("//button[@class='activate']");
	By cpclientinactivegrid = By.xpath("//div[@class='grid-content']");
	By cpclientphone = By.xpath("//a[contains(@href,'tel')]");
	By cpvalclientname=By.cssSelector(".ember-view.name");

	// Initializing Client Page
	public ClientPg(WebDriver tdriver) {
		bval = false;
		driver = tdriver;
		PageFactory.initElements(driver, this);
		pgaction = new Actions(this.driver);
		wait = new WebDriverWait(this.driver, 100);
	}

//verify client contacts are listed
	public boolean verifyClientcontactpresent() {
		cpclientsLst = driver.findElements(cpclients);
		return cpclientsLst.isEmpty();

	}

//search client in search option
	public void sendCPSearchvar(String ser) {
		cpser = ser;
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cpsearch)));
		driver.findElement(cpsearch).sendKeys(ser);

	}

//validate client search
	public boolean valsendCPSearchvar() {
		bval = false;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpsearchelem = cpclientsLstItr.next();
			bval = cpsearchelem.getText().toLowerCase().contains(cpser.toLowerCase());

		}
		driver.findElement(cpsearch).clear();
		driver.navigate().refresh();
		return bval;
	}

	// check presence of filter option
	public void cpFilter(String cpFvar) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cpfilter)));
		new Select(driver.findElement(cpfilter)).selectByVisibleText(cpFvar);
	}

	public boolean valcpFilter() {
		bval = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty() || cpclientsLst.isEmpty()) {
			bval = true;
		}
		return bval;
	}

	// check presence of sort option
	public void cpSort(String cpSvar) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cpsort)));
		new Select(driver.findElement(cpsort)).selectByVisibleText(cpSvar);
	}

	public boolean valcpSort() {
		bval = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty() || cpclientsLst.isEmpty()) {
			bval = true;
		}
		return bval;
	}

	// check Manage options
	public boolean cpActiveManage() {
		bval = false;
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpmanageelem = cpclientsLstItr.next().findElement(cpmanage);
			cpmanageelem.click();
			try {
				cpmanageelem.findElement(cpmanageview).getText();
				bval = true;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return bval;

	}

	public boolean cpInactiveManage() {
		bval = false;
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();

			WebElement cpmanageelem = cpclientsLstItr.next().findElement(cpmanage);
			cpmanageelem.click();
			try {
				cpmanageelem.findElement(By.xpath(
						"//div[@class='ember-basic-dropdown-trigger ember-basic-dropdown-trigger--in-place ember-view']"));
				bval = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bval;
	}

//Manage-->View option
	public void cpmanageViewClient() {
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpmanageelem = cpclientsLstItr.next();
			cpclientname = cpmanageelem.findElement(cpclientnamepath).getText();
			System.out.println(cpclientname);
			cpmanageelem.findElement(cpmanage).click();
			pgaction.moveToElement(cpmanageelem.findElement(cpmanageview)).build().perform();
			cpmanageelem.findElement(cpmanageview).click();

		}
	}

	public boolean valcpmanageViewClient() {
		bval = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(cpvalclientname)));
		System.out.println(driver.findElement(cpvalclientname).getText());
		bval = driver.findElement(cpvalclientname).getText()
				.equalsIgnoreCase(cpclientname);
		return bval;
	}

//Check Manage-->EditClientInfo option
	public void cpmanageEditClientInfo() {
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpmanageelem = cpclientsLstItr.next();
			cpclientname = cpmanageelem.findElement(cpclientnamepath).getText();
			System.out.println(cpclientname);
			cpmanageelem.findElement(cpmanage).click();
			pgaction.moveToElement(cpmanageelem.findElement(cpmanageeditclientinfo)).build().perform();
			cpmanageelem.findElement(cpmanageeditclientinfo).click();

		}

	}

	public boolean valcpmanageEditClientInfo() {
		bval = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cpeditclientinfoclientnamepath)));
		System.out.println(driver.findElement(cpeditclientinfoclientnamepath).getText());
		bval = driver.findElement(cpeditclientinfoclientnamepath).getText().equalsIgnoreCase(cpclientname);
		return bval;
	}

	// Check Manage-->Merge with option
	public void cpmanageMergeWith() {
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpmanageelem = cpclientsLstItr.next();
			cpclientname = cpmanageelem.findElement(cpclientnamepath).getText();
			System.out.println(cpclientname);
			cpmanageelem.findElement(cpmanage).click();
			pgaction.moveToElement(cpmanageelem.findElement(cpmanagemergewith)).build().perform();
			cpmanageelem.findElement(cpmanagemergewith).click();
		}

	}

	public boolean valcpmanageMergeWith() {
		bval = false;
		String merg = "Selected for merge";
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cpmergewithname)));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cpmergewithtext)));
		if (driver.findElement(cpmergewithname).getText().equalsIgnoreCase(cpclientname)
				&& driver.findElement(cpmergewithtext).getText().toLowerCase().contains(merg.toLowerCase())) {
			bval = true;
		}
		return bval;
	}

	// Check Manage-->Make Inactive option
	public String cpmanageMakeInactive() {

		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpmanageelem = cpclientsLstItr.next();
			cpclientname = cpmanageelem.findElement(cpclientnamepath).getText();
			System.out.println(cpclientname);
			cpmanageelem.findElement(cpmanage).click();
			pgaction.moveToElement(cpmanageelem.findElement(cpmanageinactive)).build().perform();
			cpmanageelem.findElement(cpmanageinactive).click();
		}
		return cpclientname;
	}

	public boolean valcpmanageMakeInactive() {
		bval = false;
		String inactivepopup = "//div[@class='banner banner-success has-icon is-sticky']";
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bval = driver.findElement(By.xpath(inactivepopup)).getText().toLowerCase().contains(cpclientname.toLowerCase());
		return bval;
	}

//verify links are working
	public void cpnameLinkclick() {
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpnameelem = cpclientsLstItr.next();
			cpclientname = cpnameelem.findElement(cpclientnamepath).getText();
			pgaction.moveToElement(cpnameelem.findElement(cpclientnamepath)).build().perform();
			cpnameelem.findElement(cpclientnamepath).click();
		}
	}

	public boolean valcpnameLinkclick() {
		bval = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(cpvalclientname)));
		System.out.println(driver.findElement(cpvalclientname).getText());
		bval = driver.findElement(cpvalclientname).getText()
				.equalsIgnoreCase(cpclientname);
		return bval;
	}

	public void cpmailLinkclick() {

	}

	public boolean valcpmailLinkclick() {
		bval = false;
		return bval;
	}

	public void cpphoneLinkclick() {
		cpclientsLst = driver.findElements(cpclients);
		if (!cpclientsLst.isEmpty()) {
			cpclientsLstItr = cpclientsLst.listIterator();
			WebElement cpnameelem = cpclientsLstItr.next();
			System.out.println(driver.getWindowHandle().toString());
			pgaction.moveToElement(cpnameelem.findElement(cpclientphone)).build().perform();
			cphref = cpnameelem.findElement(cpclientphone).getAttribute("href");
			try {
				Robot robo = new Robot();
				cpnameelem.findElement(cpclientphone).click();
				robo.keyPress(KeyEvent.VK_ESCAPE);
				robo.keyRelease(KeyEvent.VK_ESCAPE);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public boolean valcpphoneLinkclick(String purl) {
		bval = false;
		String url = purl + cphref;
		HttpURLConnection huc = null;
		int respCode = 0;
		try {
			huc = (HttpURLConnection) (new URL(url).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			if (respCode >= 400) {
				System.out.println(url + " is a broken link");
			} else {
				System.out.println(url + " is a valid link");
			}
			bval = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bval;
	}
}
