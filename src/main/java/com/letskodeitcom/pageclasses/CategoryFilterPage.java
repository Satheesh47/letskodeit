
package com.letskodeitcom.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.letskodeitcom.base.BasePage;

public class CategoryFilterPage extends BasePage {
	
	public WebDriver driver;
	private String CATEGORY_DROPDOWN = "xpath=>//select[@name='categories']";
	private String CATEGORY_OPTION = "xpath=>//a[@href='/courses/category/%s']";
	
	public CategoryFilterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public ResultsPage select(String categoryName) {
		selectOption(CATEGORY_DROPDOWN, categoryName, "Category Dropdown");		
		return new ResultsPage(driver);
	}
	
    public void clickCategoryDropdown() {
        // Find category dropdown
        elementClick(CATEGORY_DROPDOWN, "Category Dropdown");
    }
	
    public WebElement findCategory(String categoryName) {
        clickCategoryDropdown();
        // Wait for the element to be clickable
        WebElement categoryOption = waitForElementToBeClickable(
                String.format(CATEGORY_OPTION, categoryName), 15);
        return categoryOption;
    }
	
    public int findCoursesCount(String categoryName) {
        WebElement categoryOption = findCategory(categoryName);
        // Get category text
        String categoryText = getText(categoryOption, "Category Option");
        // Split on ( symbol
        // Example: Software IT (2)
        // Value of arrayTemp[0] ->Software IT
        // Value of arrayTemp[1] ->2)
        String[] arrayTemp = categoryText.split("\\(");
        // Split arrayTemp[1] on ) symbol
        // Value of [0] ->2
        String courseCountString = arrayTemp[1].split("\\)")[0];
        int courseCount = Integer.parseInt(courseCountString);
        // Click the dropdown again to close the menu
        clickCategoryDropdown();
        return courseCount;
    }
	
}