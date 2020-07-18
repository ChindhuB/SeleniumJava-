package com.maven.cms.ClinicalManagementSystem.Config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportingNew extends TestListenerAdapter {
	public ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		// specify name and location of the report
		String repName = System.getProperty("user.dir") + "/test-output/" + "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentSparkReporter(repName);
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Chindhu");

		htmlReporter.config().setDocumentTitle("Sample Project"); // Title of report
		htmlReporter.config().setReportName("Functional Test Report"); // name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	/*
	 * public void onTestStart(ITestResult tr) {
	 * 
	 * logger = extent.createTest(tr.getName(),tr.getMethod().getDescription());
	 * logger.assignCategory(tr.getTestContext().getSuite().getName()); }
	 */
	public void onTestSuccess(ITestResult tr) {
		// create new entry in the report
		logger = extent.createTest(tr.getName(),tr.getMethod().getDescription());
		// send the passed information to the report with GREEN color highlighted
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		logger.assignCategory(tr.getTestContext().getSuite().getName());

	}

	public void onTestFailure(ITestResult tr) {
		// create new entry in the report
		logger = extent.createTest(tr.getName());
		// send the failed information to the report with RED color highlighted
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		logger.assignCategory(tr.getTestContext().getSuite().getName());
		try {
		logger.fail(tr.getThrowable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String screenshotpath = System.getProperty("user.dir") + "/src/test/Screenshots/" + tr.getName() + ".png";
		File screenshot = new File(screenshotpath);
		if (screenshot.exists()) {
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult tr) {
		// create new entry in the report
		logger = extent.createTest(tr.getName());
		// send the skipped information to the report with ORANGE color highlighted
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		try {
			logger.fail(tr.getThrowable());
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
