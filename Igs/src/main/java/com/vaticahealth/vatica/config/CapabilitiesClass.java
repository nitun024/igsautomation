package com.vaticahealth.vatica.config;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import org.openqa.selenium.remote.CapabilityType;

public class CapabilitiesClass {

	
	// To set up the driver capabilities
	public DesiredCapabilities driverCapabilities() {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/apps/igs/");
		File app = new File(appDir, "igs110.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "My Phone");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.paytm.gamespark.lite");
		capabilities.setCapability("appActivity", "com.paytm.gamestore.activity.SplashActivity");

		return capabilities;

	}
}
