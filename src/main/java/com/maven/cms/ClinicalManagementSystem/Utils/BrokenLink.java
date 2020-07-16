package com.maven.cms.ClinicalManagementSystem.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenLink {
	By linktag = By.tagName("a");
	boolean bval;
	public WebDriver driver;
	public WebDriverWait wait;
	public ArrayList<String> brokenlinks;

	// Initializing BrokenLink Class
	public BrokenLink(WebDriver tdriver) {
		driver = tdriver;
		wait = new WebDriverWait(this.driver, 100);
	}

	public boolean linkStatus() {
		int respCode = 0;
		bval = false;
		List<WebElement> linklist = driver.findElements(linktag);
		Iterator<WebElement> linklistIt = linklist.iterator();
		while (linklistIt.hasNext()) {
			respCode = 0;
			brokenlinks = new ArrayList<String>();
			WebElement linkchk = linklistIt.next();
			String url = linkchk.getAttribute("href");
			HttpURLConnection huc = null;
			if (url != null) {
				try {
					huc = (HttpURLConnection) (new URL(url).openConnection());
					huc.setRequestMethod("HEAD");
					huc.connect();
					respCode = huc.getResponseCode();
					if (respCode >= 400) {
						System.out.println(url + " is a broken link");
						brokenlinks.add(url);
					} else {
						System.out.println(url + " is a valid link");
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (brokenlinks.isEmpty()) {
			bval = true;
		}
		return bval;
	}
}
