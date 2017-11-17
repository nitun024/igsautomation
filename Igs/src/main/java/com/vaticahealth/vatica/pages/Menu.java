package com.vaticahealth.vatica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.vaticahealth.vatica.config.Configuration;
import com.vaticahealth.vatica.elements.Button;
import com.vaticahealth.vatica.elements.Option;

import io.appium.java_client.android.AndroidDriver;

public class Menu {

	AndroidDriver driver = Configuration.driver;

	@FindBy(xpath = Option.menu_login)
	public WebElement login_Option;

	public Menu() {
		PageFactory.initElements(driver, this);
	}

}
