package com.vaticahealth.vatica.config;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class Action {

	AndroidDriver driver = Configuration.driver;

	// To scroll down to a particular text on the app.
	public void scrollto(String text) {
		driver.scrollTo(text);
	}

	// To swipe down the app
	public void swipeDown() {

		driver.swipe(390, 1030, 390, 364, 1000);
	}

	// To create a list of similar elements after scrolling on the app.
	public void getCategory(String id, List list, int count) {

		List<WebElement> curList = driver.findElementsById(id);

		for (int i = 0; i < curList.size(); i++) {
			if (!list.contains(curList.get(i).getText())) {
				list.add(curList.get(i).getText());
			}
		}

		swipeDown();

		if (list.size() >= count) {
			return;
		} else {
			getCategory(id, list, count);
		}
	}

}
