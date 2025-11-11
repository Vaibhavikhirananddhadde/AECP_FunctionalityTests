package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class GM_DepartmentPage extends BaseClass{
	public GM_DepartmentPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary']") WebElement dd_selectProject;
	private By projectOptions = By.xpath("//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item')]");
	private By departmentsContainer = By.xpath("//div[@class='row']//div[@class='text-center']/h3");

	public void checkDepartmentDisplayed() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // 1️⃣ Open dropdown and capture PROJECT NAMES (Strings)
	    wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();
	  //  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(projectOptions));
	    
	    //    (because they are loaded dynamically)
	    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(projectOptions, 1));


	    List<WebElement> projectElements = driver.findElements(projectOptions);
	    List<String> projectNames = new java.util.ArrayList<>();

	    for (WebElement p : projectElements) {
	        String name = p.getText().trim();
	        if (!name.isEmpty()) {
	            projectNames.add(name);
	        }
	    }

	    System.out.println("Total projects in dropdown is: " + projectNames.size());
	    System.out.println("Projects: " + projectNames);

	    // Optional: close dropdown
	    dd_selectProject.click();

	    // 2️⃣ Loop using project names so we never depend on stale elements
	    for (String projectName : projectNames) {

	        // Re-open dropdown
	        wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();

	        // Select project by visible text from the open menu
	        WebElement currentProject = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@class='dropdown-menu show']/button[@class='dropdown-item' and normalize-space(text())='"
	                    + projectName + "']")
	        ));

	        System.out.println("Selecting project: " + projectName);
	        currentProject.click();

	        // 3️⃣ Wait for departments to load for this project
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(departmentsContainer));
	        List<WebElement> departments = driver.findElements(departmentsContainer);

	        if (departments.isEmpty()) {
	            System.out.println("There is no department for the selected project: " + projectName);
	        } else {
	            for (WebElement department : departments) {
	                System.out.println("Departments for the selected project " 
	                                   + projectName + " are " + department.getText());
	            }
	        }
	    }
	}

}
