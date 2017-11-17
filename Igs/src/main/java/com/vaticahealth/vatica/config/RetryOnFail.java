package com.vaticahealth.vatica.config;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryOnFail implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 1;
	IGSListener vl = new IGSListener();

	@Override
	public boolean retry(ITestResult result) {

		if (counter < retryLimit) {
			IGSListener.totaltests.remove(IGSListener.totaltests.size() - 1);
			counter++;
			return true;
		}

		return false;
	}

}
