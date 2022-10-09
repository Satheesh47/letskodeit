package com.letskodeit.testclasses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.letskodeit.base.BaseTest;
import com.letskodeit.base.CheckPoint;
import com.letskodeitcom.pageclasses.CategoryFilterPage;
import com.letskodeitcom.pageclasses.SearchBarPage;
import com.letskodeitcom.utilities.Constants;
import com.letskodeitcom.utilities.ExcelUtility;
import com.letskodeitcom.utilities.Util;

public class AllCoursesTests extends BaseTest {
	
	@DataProvider(name="verifySearchCourseData")
	public Object[][] getVerifySearchCourseData() {
		Object[][] testData = ExcelUtility.getTestData("verify_search_course");
		return testData;
	}
	
	@BeforeClass
	public void setUp() {
		nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "AllCoursesTests");
	}
	
	@Test(dataProvider = "verifySearchCourseData")
	public void verifySearchCourse(String courseName) throws InterruptedException {
		nav.allCourses();
		search = new SearchBarPage(driver);
		result = search.course(courseName);
		Thread.sleep(1000);
		boolean searchResult = result.verifySearchResult();
		CheckPoint.markFinal("test-verify-search-course-01", searchResult, "Search course verification");
	}
	
	@Test
	public void filterByCategory() throws InterruptedException {
		nav.allCourses();
		Util.sleep(500, " All Courses link ..");
		category = new CategoryFilterPage(driver);
		result = category.select("Software Development");
		Util.sleep(2000, " Selected courses results page ..");
		boolean filterResult = result.verifyFilterCourseCount(Constants.CATEGORY_COURSE_COUNT);
		CheckPoint.markFinal("test-filter-by-category-01", filterResult, "Filter by category verification");
	}
}