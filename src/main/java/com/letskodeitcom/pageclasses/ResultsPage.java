package com.letskodeitcom.pageclasses;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.letskodeitcom.base.BasePage;

public class ResultsPage extends BasePage {
	
	public WebDriver driver;
	private String URL = "https://courses.letskodeit.com/courses";
	private String COURSES_LIST = "class=>zen-course-list";
	private String COURSE_HEADER_SOFTWARE_DEVELOPMENT = "xpath=>//h1[normalize-space()='Category : Software Development']";
	
	public ResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean isOpen() {
		return URL.equalsIgnoreCase(getURL());
	}
	
	public int coursesCount() {
		List<WebElement> coursesList = getElementList(COURSES_LIST, "Courses List");
		return coursesList.size();
	}
	
	public boolean verifySearchResult() {
		boolean result = false;
		if(coursesCount() > 0)
			result = true;
		result = isOpen() && result;
		return result;
	}
	
	public boolean verifyFilterCourseCount(int expectedCount) {
		log.info("Actual courses count " + coursesCount());
		return coursesCount() == expectedCount;
	}
	
	public void waitForCourseHeaderVisible(String courseName) {
		waitForElementVisible(COURSE_HEADER_SOFTWARE_DEVELOPMENT,5);
	}
}