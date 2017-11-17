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
import com.vaticahealth.vatica.elements.TextBox;

import io.appium.java_client.android.AndroidDriver;

public class Login {
	AndroidDriver driver = Configuration.driver;

	@FindBy(xpath = TextBox.login_MobileNumber)
	public WebElement MobileNumber_Txtbx;

	@FindBy(id = Button.login_SendOTP)
	public WebElement SendOTP_Btn;

	public Login() {
		PageFactory.initElements(driver, this);
	}

}
