package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class PM_EngineersPage extends BaseClass{
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary']") WebElement dd_selectProject;
	private By projectOptions = By.xpath("//div[@class='dropdown-menu show']/button");
	private By empNameContainer = By.xpath("//div[@class='row']//div[@class='fw-bold mb-1']");
	
	public PM_EngineersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void Check_SiteEngineersList() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();

	    Thread.sleep(3000);
	    List<WebElement> initialProjects = wait.until(
	            ExpectedConditions.presenceOfAllElementsLocatedBy(projectOptions));
	    int projectCount = initialProjects.size();
	    System.out.println("Total number of projects in dropdown is :" + projectCount);

	    // Close dropdown once
	    dd_selectProject.click();

	    for (int i = 0; i < projectCount; i++) {

	        // Re-open dropdown fresh
	        wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();

	        List<WebElement> projects = wait.until(
	                ExpectedConditions.presenceOfAllElementsLocatedBy(projectOptions));

	        WebElement currentProject = projects.get(i);
	        String projectName = currentProject.getText().trim();

	        System.out.println("\nSelecting project " + projectName);
	        currentProject.click();
	        Thread.sleep(3000);

	        // 🔹 Try to get employee names – but if none, handle gracefully
	        List<WebElement> empNames;
	        try {
	            empNames = wait.until(
	                    ExpectedConditions.visibilityOfAllElementsLocatedBy(empNameContainer));
	        } catch (TimeoutException e) {
	            // No employee cards visible for this project
	            System.out.println("No site engineers found for selected project " + projectName);
	            continue; // move to next project
	        }

	        // If you want unique names only, use Set. If you want all (including duplicates), use List.
	        java.util.Set<String> engineerNames = new java.util.LinkedHashSet<>();

	        for (WebElement employee : empNames) {
	            String employeeName = employee.getText().trim();
	            if (!employeeName.isEmpty()) {
	                engineerNames.add(employeeName);  // Set automatically removes duplicates
	            }
	        }

	        if (engineerNames.isEmpty()) {
	            System.out.println("No site engineers found for selected project " + projectName);
	        } else {
	            System.out.println("Site engineers for selected project " + projectName + " are: "
	                    + String.join(", ", engineerNames));
	        }
	    }
	}

}
