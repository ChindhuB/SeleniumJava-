package com.maven.cms.ClinicalManagementSystem.Config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
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
		
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Chindhu");
		extent.setReportUsesManualConfiguration(true);


		htmlReporter.config().setDocumentTitle("Sample Project"); // Title of report
		htmlReporter.config().setReportName("Functional Test Report"); // name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		

	}

	public void onTestStart(ITestResult tr) {
		// create new entry in the report
		String qualifiedName = tr.getTestClass().getName();
		int last = qualifiedName.lastIndexOf(".");
		String className = qualifiedName.substring(last+1);
		logger = extent.createTest(className, tr.getMethod().getDescription());
		logger.assignCategory(tr.getTestContext().getSuite().getName());
		logger.getModel().setStartTime(getTime(tr.getStartMillis()));
	}

	public void onTestSuccess(ITestResult tr) {
		// send the passed information to the report with GREEN color highlighted
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		logger.getModel().setEndTime(getTime(tr.getEndMillis()));
	}

	public void onTestFailure(ITestResult tr) {
		// send the failed information to the report with RED color highlighted
		Object testObj=tr.getInstance();
		Class cls=tr.getTestClass().getRealClass().getSuperclass();
		String screenshotname="test";
		System.out.println("1"+screenshotname);
		try {
			screenshotname = cls.getDeclaredField("bscreenshotname").get(testObj).toString();
			System.out.println("2"+screenshotname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		logger.getModel().setEndTime(getTime(tr.getEndMillis()));
		String screenshotpath ="./test-output/Screenshots/" + screenshotname + ".png";
		File screenshot = new File(screenshotpath);
		if (screenshot.exists()) {
			try {
				logger.fail("Screenshot is below:" +screenshotpath);
				logger.log(Status.FAIL, tr.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath("."+screenshotpath).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			logger.log(Status.FAIL, tr.getThrowable());
		}
	}

	public void onTestSkipped(ITestResult tr) {
		// send the skipped information to the report with ORANGE color highlighted
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		logger.getModel().setEndTime(getTime(tr.getEndMillis()));
		try {
			logger.log(Status.SKIP, tr.getThrowable());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
