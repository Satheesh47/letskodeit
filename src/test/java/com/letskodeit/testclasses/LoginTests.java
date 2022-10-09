package com.letskodeit.testclasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.letskodeit.base.BaseTest;
import com.letskodeit.base.CheckPoint;
import com.letskodeitcom.utilities.Constants;
import com.letskodeitcom.utilities.Util;

public class LoginTests extends BaseTest {
	
	
	private static final Logger log = LogManager.getLogger(LoginTests.class.getName());
	
	@AfterMethod
	public void afterMethod() {
		log.info("****** After Method ******");
		if (nav.isUserLoggedIn()) {
			nav.logout();
			nav.login();
		}
	}

	@Test
	public void testLogin() {
		nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		boolean headerResult = nav.verifyHeader();
		CheckPoint.mark("test-login-01", headerResult, "Header verification");
		boolean result = nav.isUserLoggedIn();
		CheckPoint.markFinal("test-login-01", result, "Login verification");
	}

	@Test
	public void testInvalidLogin() {
		nav = login.signInWith("test@email.com", "123abcd");
		Util.sleep(500);
		boolean result = nav.isUserNotLoggedIn();
		CheckPoint.markFinal("test-invalidlogin-01", result, "Invalid Login verification");
	}
}