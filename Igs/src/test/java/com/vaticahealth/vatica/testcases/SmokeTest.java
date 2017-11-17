package com.vaticahealth.vatica.testcases;

import com.vaticahealth.vatica.config.TestAnnotation;
import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends TestAnnotation {

	// To verify the Home and Categories option on the homepage.
	@Test(enabled = false, priority = 1)
	public void homepage_VerifyHomepage() {

		Boolean actualHome = home.home_Option.isEnabled() && home.home_Option.isDisplayed();
		assertEquals(actualHome.booleanValue(), true);

		Boolean actualcategories = home.categories_Option.isEnabled() && home.categories_Option.isDisplayed();
		assertEquals(actualcategories.booleanValue(), true);

	}

	// To start the Login/SignUp functionality and extract the OTP from the
	// response.
	@Test(enabled = false, priority = 2)
	public void menu_VerifyLogin() {

		String query = "SELECT otp FROM otp_access_token WHERE username = '918527510595' ORDER BY created DESC";
		String MobileNumber = "9643193486";
		String FullMobileNumber = "91" + MobileNumber;

		// Clicking on the Menu > Login > Entering Phone number Button.
		home.menu_Option.click();
		menu.login_Option.click();
		login.MobileNumber_Txtbx.sendKeys(MobileNumber);
		login.SendOTP_Btn.click();
		dbConnection.connectDB();
		dbConnect.getResultfromDB(query, "otp");

		// String Otp = api.OTPSender(FullMobileNumber);

		// driver.findElement(By.id("com.paytm.gamespark.lite:id/otp_one_edit_text")).sendKeys(Otp.subSequence(0,
		// 1));
		// driver.findElement(By.id("com.paytm.gamespark.lite:id/otp_two_edit_text")).sendKeys(Otp.subSequence(1,
		// 2));
		// driver.findElement(By.id("com.paytm.gamespark.lite:id/otp_three_edit_text")).sendKeys(Otp.subSequence(2,
		// 3));
		// driver.findElement(By.id("com.paytm.gamespark.lite:id/otp_four_edit_text")).sendKeys(Otp.subSequence(3,
		// 4));

	}

	@SuppressWarnings({ "unchecked", "null" })
	@Test(enabled = true, priority = 2)
	public void homePage_VerifyHomeCategories() {

		System.out.println("This is : "+common.readTestData("1001\\ HomeSubCat1"));
		List<String> listOfCategories = new ArrayList<String>();

		while (true) {
			List<WebElement> templist = new ArrayList<WebElement>();
			templist = driver.findElementsById("com.paytm.gamespark.lite:id/button_section");

			for (int i = 0; i < templist.size(); i++) {
				if (!listOfCategories.contains(templist.get(i).getText()))
					listOfCategories.add(templist.get(i).getText());
			}
			action.swipeDown();
			if (listOfCategories.size() >= 7)
				break;
		}

		for (int i = 0; i < listOfCategories.size(); i++) {
			Assert.assertEquals(listOfCategories.get(i), common.readTestData("1001\\ HomeSubCat" + (i + 1)));
		}

	}

	@Test(enabled = false, priority = 2)
	public void tc_HomePage_2() throws MalformedURLException {

		// Clicking on categories
		driver.findElement(By.xpath("//*[@text='Categories']")).click();
		List<WebElement> lst = driver.findElementsByXPath("//*[@class='android.widget.RelativeLayout']");
		System.out.println(lst.size());
		for (int i = 0; i < lst.size(); i++) {
			System.out.println(lst.get(i).getText());
			System.out.println(lst.get(i).getAttribute("text"));
		}

	}

}