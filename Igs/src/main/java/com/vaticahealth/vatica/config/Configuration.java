package com.vaticahealth.vatica.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.android.AndroidDriver;

public class Configuration {

	public static AndroidDriver driver;

	// @BeforeSuite
	public static AndroidDriver invokeDriver() {

		CapabilitiesClass cap = new CapabilitiesClass();
		System.out.println("this got printed.");
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap.driverCapabilities());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		return driver;

	}

}
