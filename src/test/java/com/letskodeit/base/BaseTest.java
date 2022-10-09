
package com.letskodeit.base;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.letskodeitcom.pageclasses.CategoryFilterPage;
import com.letskodeitcom.pageclasses.LoginPage;
import com.letskodeitcom.pageclasses.ResultsPage;
import com.letskodeitcom.pageclasses.SearchBarPage;
import com.letskodeitcom.pageclasses.TopNavigationMenu;
import com.letskodeitcom.utilities.Constants;

public class BaseTest {
	public WebDriver driver;
	public WebDriverWait wait;
	protected String baseURL;
	protected LoginPage login;
	protected TopNavigationMenu nav;
	protected SearchBarPage search;
	protected ResultsPage result;
	protected CategoryFilterPage category;
	private static final Logger log = LogManager.getLogger(BaseTest.class.getName());

	@BeforeClass
	@Parameters({"browser"})
	public void commonSetUp(String browser) throws Exception {
		driver = WebDriverFactory.getInstance().getDriver(browser);
		baseURL = Constants.BASE_URL;
		driver.get(baseURL);
		// Login
		nav = new TopNavigationMenu(driver);
		login = nav.login();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	}
	
	@BeforeMethod
	public void methodSetUp() {
		log.info("****** Before Method ******");
		CheckPoint.clearHasMap();
	}

	@AfterClass
	public void commonTearDown() throws Exception {
		WebDriverFactory.getInstance().quitDriver();
	}

}