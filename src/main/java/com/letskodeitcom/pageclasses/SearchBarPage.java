package com.letskodeitcom.pageclasses;

import org.openqa.selenium.WebDriver;
import com.letskodeitcom.base.BasePage;
import com.letskodeitcom.utilities.Util;

public class SearchBarPage extends BasePage {
	
	public WebDriver driver;
	private String SEARCH_COURSE_FIELD = "xpath=>//input[@id='search']";
	private String SEARCH_COURSE_BUTTON = "xpath=>//button[@type='submit']";
	
	public SearchBarPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public ResultsPage course(String courseName) throws InterruptedException {
		Util.sleep(500);
		sendData(SEARCH_COURSE_FIELD, courseName, "Search Course Field");		
		elementClick(SEARCH_COURSE_BUTTON, "Search Course Button");
		return new ResultsPage(driver);
	}

}