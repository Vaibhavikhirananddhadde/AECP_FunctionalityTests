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

public class PM_BudgetPage extends BaseClass{
	public PM_BudgetPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary']") WebElement dd_selectProject;
	
	private By projectOptions = By.xpath("//div[@class='dropdown-menu show']/button[@class='dropdown-item']");
    private By budgetContainer = By.xpath("//div[@class='d-flex justify-content-between align-items-center']");
    private By budgetSpans = By.xpath("//div[@class='d-flex justify-content-between align-items-center']/span");

    public void checkBudgetForProject() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) Open dropdown once & get how many projects are there
        wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();

        List<WebElement> initialProjects =wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(projectOptions));

        int projectCount = initialProjects.size();
        System.out.println("Total projects in dropdown: " + projectCount);
        
        dd_selectProject.click();

        // 2) Loop by index so we don't rely on text locators
        for (int i = 0; i < projectCount; i++) {

            // Re-open dropdown every time
            wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();

            // Re-fetch list to avoid stale elements
            List<WebElement> projects =
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(projectOptions));

            WebElement currentProject = projects.get(i);
            String projectName = currentProject.getText().trim();
            System.out.println("Selecting project: " + projectName);

            currentProject.click();

            // 3) Wait for budget section for this project
            wait.until(ExpectedConditions.visibilityOfElementLocated(budgetContainer));

            List<WebElement> budgets = driver.findElements(budgetSpans);

            if (budgets.isEmpty()) {
                System.out.println("No budget details found for project: " + projectName);
            } else {
                for (WebElement budget : budgets) {
                    System.out.println("Budget for " + projectName + " is: " + budget.getText());
                }
            }
        }
    }


}
