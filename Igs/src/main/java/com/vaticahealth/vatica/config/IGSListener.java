package com.vaticahealth.vatica.config;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class IGSListener implements ITestListener, IAnnotationTransformer {

	MonitoringMail mail = new MonitoringMail();

	List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
	List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
	List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
	static List<ITestNGMethod> totaltests = new ArrayList<ITestNGMethod>();

	public List<ITestNGMethod> getTotaltests() {
		return totaltests;
	}

	@Override
	public void onTestStart(ITestResult result) {
		totaltests.add(result.getMethod());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		for (int i = 0; i < skippedtests.size(); i++) {
			if (skippedtests.get(i) == result.getMethod()) {
				skippedtests.remove(i);
			}
		}

		for (int i = 0; i < failedtests.size(); i++) {
			if (failedtests.get(i) == result.getMethod()) {
				failedtests.remove(i);
				break;
			}
		}
		passedtests.add(result.getMethod());

	}

	@Override
	public void onTestFailure(ITestResult result) {

		for (int i = 0; i < skippedtests.size(); i++) {
			if (skippedtests.get(i) == result.getMethod()) {
				skippedtests.remove(i);
				break;
			}
		}

		for (int i = 0; i < failedtests.size(); i++) {
			if (failedtests.get(i) == result.getMethod()) {
				failedtests.remove(i);
				break;
			}
		}

		failedtests.add(result.getMethod());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		skippedtests.add(result.getMethod());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		mail.setFailedTestCount(failedtests.size());
		mail.setPassedTestCount(passedtests.size());
		mail.setSkippedTestCount(skippedtests.size());
		mail.setTotalTestCount(totaltests.size());

	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryOnFail.class);

	}

}
