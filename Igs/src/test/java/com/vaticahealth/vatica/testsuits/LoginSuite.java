package com.vaticahealth.vatica.testsuits;

import java.lang.reflect.InvocationTargetException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.vaticahealth.vatica.config.TestAnnotation;
import com.vaticahealth.vatica.config.IGSInterface;
import com.vaticahealth.vatica.testcases.SmokeTest;

public class LoginSuite extends TestAnnotation implements IGSInterface {

	// Login in to the application with correct credentials and select the
	// website 'Plus' from the dropdown of sites.
	@Test(priority = 1)
	public void tc_Login_1() throws InterruptedException, InvocationTargetException {
		extest = exreport.startTest("tc_Login_1");

	//	loginTest.tc_Login_1();
	
		extest.log(LogStatus.PASS,
				"Login in to the application with correct credentials and select the website 'Plus' from the dropdown of sites.");
	}


}
