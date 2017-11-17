package com.vaticahealth.vatica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.vaticahealth.vatica.config.Configuration;
import com.vaticahealth.vatica.config.TestAnnotation;
import com.vaticahealth.vatica.elements.Button;
import com.vaticahealth.vatica.elements.Option;

import io.appium.java_client.android.AndroidDriver;

public class Home {

	AndroidDriver driver = Configuration.driver;

	@FindBy(xpath = Option.home_Home)
	public WebElement home_Option;

	@FindBy(xpath = Option.home_categories)
	public WebElement categories_Option;
	
	@FindBy(xpath = Option.home_menu)
	public WebElement menu_Option;

	public Home() {
		PageFactory.initElements(driver, this);
	}

}
