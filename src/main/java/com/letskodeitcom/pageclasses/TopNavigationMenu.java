
package com.letskodeitcom.pageclasses;

import java.util.List;
import com.letskodeitcom.utilities.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.letskodeitcom.base.BasePage;

public class TopNavigationMenu extends BasePage {
	
	public WebDriver driver;
	//private final String URL = "https://courses.letskodeit.com/courses";
	private String ALL_COURSES_LINK = "xpath=>//a[contains(text(),'ALL COURSES')]";
	private String MY_COURSES_LINK = "xpath=>//a[contains(text(),'MY COURSES')]";
	private String ACCOUNT_ICON = "xpath=>//img[@class='zl-navbar-rhs-img ']";
	private String LOGOUT_LINK = "xpath=>//a[@href='/logout']";
	private String LOGIN_LINK="xpath=>//a[contains(@href,'login')]";
	
	public TopNavigationMenu(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public LoginPage login() {
		elementClick(LOGIN_LINK, "Login Link");
		return new LoginPage(driver);
	}
	
	public void allCourses() {
		elementClick(ALL_COURSES_LINK, "All Courses Link");
	}
	
	public void myCourses() {
		elementClick(MY_COURSES_LINK, "My Courses Link");
	}
	
	/*
	 * public boolean isOpen() { return
	 * URL.equalsIgnoreCase(driver.getCurrentUrl()); }
	 */
	
	public boolean verifyHeader() {
		String text = getText(ALL_COURSES_LINK, "All courses link");
		return Util.verifyTextContains(text,"All Courses");
	}
	
	public boolean isUserLoggedIn() {
		try {
			List<WebElement> accountImage = getElementList(ACCOUNT_ICON, "Account Icon");
			if(accountImage.size() > 0)
				return true;
			else
				return false;
		}
		catch(Exception e) {
			return false;
		}
		/*
		 * if(accountImage.isDisplayed()) { return true; } else { return false; }
		 */
	}
	
	public boolean isUserNotLoggedIn() {
		try {
			List<WebElement> accountImage = getElementList(ACCOUNT_ICON, "Account Icon");
			if(accountImage.size() > 0)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			return false;
		}
		/*
		 * if(accountImage.isDisplayed()) { return true; } else { return false; }
		 */
	}
	
	public void logout() {
		elementClick(ACCOUNT_ICON, "User Account Icon");
		WebElement logoutLink = waitForElementToBeClickable(LOGOUT_LINK, 10);
		elementClick(logoutLink, "Logout link");
	}
}