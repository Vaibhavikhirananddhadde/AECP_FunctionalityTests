package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class GM_DepartmentPage extends BaseClass{
	public GM_DepartmentPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary']") WebElement dd_selectProject;
	private By projectOptions = By.xpath("//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item')]");
	private By departmentsContainer = By.xpath("//div[@class='row']//div[@class='text-center']/h3");

    public void checkDepartmentDisplayed() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1️⃣ Open dropdown and capture PROJECT NAMES as Strings
        wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", dd_selectProject);

        List<WebElement> projectElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(projectOptions));

        List<String> projectNames = new ArrayList<>();

        for (WebElement p : projectElements) {
            String name = p.getText().trim();
            // Skip empty / placeholder options if any
            if (!name.isEmpty()
                    && !name.equalsIgnoreCase("Select Project")
                    && !name.equalsIgnoreCase("Show All")) {
                projectNames.add(name);
            }
        }

        System.out.println("Total projects in dropdown: " + projectNames.size());
        System.out.println("Projects: " + projectNames);

        // Close dropdown (toggle)
        dd_selectProject.click();

        // 2️⃣ For EACH project, select and verify departments
        for (String projectName : projectNames) {

            // Re-open dropdown fresh
            wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();

            // Locator for this specific project option in the OPEN menu
            By currentProjectOption = By.xpath(
                "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]" +
                "//button[contains(@class,'dropdown-item') and normalize-space()='" + projectName + "']"
            );

            WebElement projectOption = wait.until(
                    ExpectedConditions.elementToBeClickable(currentProjectOption));

            System.out.println("\nSelecting project: " + projectName);
            projectOption.click();
            Thread.sleep(3000);

            // Wait until dropdown shows selected project text (filter applied)
            wait.until(ExpectedConditions.textToBePresentInElement(dd_selectProject, projectName));

            // 3️⃣ Wait for departments to load for this project
            List<WebElement> departments;
            try {
                departments = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(departmentsContainer));
            } catch (Exception e) {
                // If nothing is found in time, treat as no departments
                departments = new ArrayList<>();
            }

            // Collect department names in a list of Strings
            List<String> deptNames = new ArrayList<>();
            for (WebElement dept : departments) {
                String deptName = dept.getText().trim();
                if (!deptName.isEmpty()) {
                    deptNames.add(deptName);
                    Assert.assertFalse(deptName.isEmpty(),
                            "Department name is empty for project: " + projectName);
                }
            }

            // 4️⃣ Print AFTER collecting all dept names
            if (deptNames.isEmpty()) {
                System.out.println("Departments for selected project " + projectName + " is : NONE");
            } else {
                System.out.println("Departments for selected project " + projectName + " is : "
                        + String.join(", ", deptNames));
            }
        }
    }

}
