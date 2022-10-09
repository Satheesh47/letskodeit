package com.letskodeitcom.pageclasses;

import org.openqa.selenium.WebDriver;
import com.letskodeitcom.base.BasePage;
import com.letskodeitcom.utilities.Constants;
import com.letskodeitcom.utilities.Util;

public class LoginPage extends BasePage {

	public WebDriver driver;
	private String EMAIL_FIELD="id=>email";
	private String PASSWORD_FIELD="id=>password";
	private String LOG_IN_BUTTON = "xpath=>//input[@value='Login']";
	private String LOGIN_ERROR_MESSAGE = "xpath=>//span[@class='dynamic-text help-block']";
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public TopNavigationMenu signInWith(String email, String password) {
		sendData(EMAIL_FIELD, email, "Email Field");
		sendData(PASSWORD_FIELD, password, "Password Field");
		elementClick(LOG_IN_BUTTON, "Login Button");
		return new TopNavigationMenu(driver);
	}
	
	public boolean loginErrorValidation() {
		boolean result = Util.verifyTextContains(getText(LOGIN_ERROR_MESSAGE, "Login Error Message"),Constants.LOGIN_ERROR_MESSAGE);
		return result;
	}
	
	
}