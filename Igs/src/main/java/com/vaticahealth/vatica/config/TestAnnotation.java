package com.vaticahealth.vatica.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.AuthenticationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.vaticahealth.vatica.pages.Home;
import com.vaticahealth.vatica.pages.Login;
import com.vaticahealth.vatica.pages.Menu;

import io.appium.java_client.android.AndroidDriver;

import com.igs.api.DatabaseConnect;
import com.igs.api.IgsApi;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

public class TestAnnotation {

	// public static WebDriver driver = Configuration.browser();
	public static ExtentReports exreport;
	public static ExtentTest extest;
	protected Home home = new Home();
	protected Login login = new Login();
	protected Menu menu = new Menu();
	public static AndroidDriver driver = Configuration.invokeDriver();
	public static DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	public static DateFormat sdf_Subject = new SimpleDateFormat("MM/dd/yyyy");
	public static Date date = new Date();
	public static String CurrentDateTime = sdf.format(date);
	public static String CurrentDate = sdf_Subject.format(date);
	public static MonitoringMail mail = new MonitoringMail();
	public static BugLogInJira bug = new BugLogInJira();
	public static String Environment;
	public static IgsApi api = new IgsApi();
	public static DatabaseConnection dbConnection = new DatabaseConnection();
	public static DatabaseConnect dbConnect = new DatabaseConnect();
	public static Action action = new Action();
	public static Common common = new Common();

	@BeforeSuite
	public void invokeUrl() {

	}

	@BeforeMethod
	public void setUpBeforeEveryTest() {
		// driver.manage().deleteAllCookies();
		// driver.get(Configuration.invokeUrl());

	}

	@BeforeSuite
	public void startReport() {
		// common.removeOldReport();
		exreport = new ExtentReports(System.getProperty("user.dir") + "/test-output/Vatica_Extent_Report.html", true);
		// exreport.addSystemInfo("Host Name", "Nitun
		// Pachauri").addSystemInfo("Environment", Configuration.URL)
		// .addSystemInfo("User Name", "Nitun Pachauri");
		// common.cleanDownloadsFolder();
		/*
		 * if (Configuration.URL.equals(
		 * "http://vaticatest.cloudapp.net/VaticaHealth.Web.UI/")) { Environment = "QA";
		 * } else { Environment = "Dev"; }
		 */
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// String screenShotPath = common.takeScreenshot(driver, "screenShot");
			extest.log(LogStatus.FAIL, result.getThrowable());
			// extest.log(LogStatus.FAIL, "Snapshot below: " +
			// extest.addScreenCapture(screenShotPath));

		}
		if (result.getStatus() == ITestResult.SKIP) {
			extest.log(LogStatus.SKIP, "The test has been skipped.");
		}
		exreport.endTest(extest);

	}

	@AfterSuite
	public void endReport() {
		exreport.flush();
		exreport.close();
		// driver.quit();
	}

	@Parameters({ "ExecutionType" })
	@AfterSuite(description = "Send email report", enabled = false, dependsOnMethods = { "endReport" })
	public void sendTestReport(String ExecutionType) throws AddressException, MessagingException,
			AuthenticationException, ClientHandlerException, UniformInterfaceException, ParseException {

		String SubjectPass = "Vatica-" + Environment + " " + ExecutionType + " Test Report - " + CurrentDate
				+ " - Build: STABLE";
		String SubjectFail = "Vatica-" + Environment + " " + ExecutionType + " Test Report - " + CurrentDate
				+ " - Build: UNSTABLE";

		// Message body of the mail for Automation Test
		String MessageBodyFail = "<br/>" + "<br/>" + "<U>" + "<B>" + "Automated " + ExecutionType
				+ " Tests Execution Summary" + "</B>" + "</U>" + "<br/>" + "<br/>"
				+ "<table  border= \"1px solid black\" border-collapse=collapse   padding=5px>" + "<tr>" + "<th>"
				+ "Ran at" + "</th>" + "<th>" + (CurrentDateTime) + "</th>" + "</tr>" + "<tr>" + "<th>"
				+ "Total test cases run" + "</th>" + "<th>" + (mail.getTotalTestCount()) + "</th>" + "</tr>" + "<tr>"
				+ "<th>" + "Passed test cases" + "</th>" + "<th>" + (mail.getPassedTestCount()) + "</th>" + "</tr>"
				+ "<tr>" + "<th>" + "Failed test cases" + "</th>" + "<th>" + (mail.getFailedTestCount()) + "</th>"
				+ "</tr>" + "<tr>" + "<th>" + "Skipped test cases" + "</th>" + "<th>" + (mail.getSkippedTestCount())
				+ "</th>" + "</tr>" + "<tr>" + "<th>" + "Overall Build health" + "</th>" + "<th>" + "<B>UNSTABLE</B>"
				+ "</th>" + "</tr>" + "</table>" + "<br/>" + "<br/>" + "DETAILED REPORT IS ATTACHED WITH THIS EMAIL."
				+ /*
					 * " Bug logged for the build failure: " +
					 * bug.logBugInJiraOnFailure(mail.getFailedTestCount()) +
					 */ "<br/>" + "<br/>"
				+ "If you have any concerns or suggestions, please reach out to:<U>vaticaqa@quovantis.com</U>"
				+ "<br />" + "<br />" + "<br />" + "Thanks," + "<br />" + "Vatica QA Team";

		String MessageBodyPass = "<br/>" + "<br/>" + "<U>" + "<B>" + "Automated " + ExecutionType
				+ " Tests Execution Summary" + "</B>" + "</U>" + "<br />" + "<br/>"
				+ "<table  border= \"1px solid black\" border-collapse=collapse   padding=5px>" + "<tr>" + "<th>"
				+ "Ran at" + "</th>" + "<th>" + (CurrentDateTime) + "</th>" + "</tr>" + "<tr>" + "<th>"
				+ "Total test cases run" + "</th>" + "<th>" + (mail.getTotalTestCount()) + "</th>" + "</tr>" + "<tr>"
				+ "<th>" + "Passed test cases" + "</th>" + "<th>" + (mail.getPassedTestCount()) + "</th>" + "</tr>"
				+ "<tr>" + "<th>" + "Failed test cases" + "</th>" + "<th>" + (mail.getFailedTestCount()) + "</th>"
				+ "</tr>" + "<tr>" + "<th>" + "Skipped test cases" + "</th>" + "<th>" + (mail.getSkippedTestCount())
				+ "</th>" + "</tr>" + "<tr>" + "<th>" + "Overall Build health" + "</th>" + "<th>" + "<B>STABLE</B>"
				+ "</th>" + "</tr>" + "</table>" + "<br />" + "<br />" + "DETAILED REPORT IS ATTACHED WITH THIS EMAIL."
				+ "<br/>" + "<br/>"
				+ "If you have any concerns or suggestions, please reach out to:<U>vaticaqa@quovantis.com</U>"
				+ "<br />" + "<br />" + "<br />" + "Thanks," + "<br />" + "Vatica QA Team";

		if (mail.getPassedTestCount() == mail.getTotalTestCount()) {

			mail.sendMail(MonitoringMail.server, MonitoringMail.from, MonitoringMail.to, SubjectPass, MessageBodyPass,
					MonitoringMail.attachmentPath, MonitoringMail.attachmentName);

		} else {
			mail.sendMail(MonitoringMail.server, MonitoringMail.from, MonitoringMail.to, SubjectFail, MessageBodyFail,
					MonitoringMail.attachmentPath, MonitoringMail.attachmentName);
		}
	}

}
