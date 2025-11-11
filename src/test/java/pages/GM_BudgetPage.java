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

public class GM_BudgetPage extends BaseClass{
	
	public GM_BudgetPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary']") WebElement dd_SelectProject;
	
	private By projectOptions = By.xpath("//div[@class='dropdown-menu show']/button[@class='dropdown-item']");
    private By budgetContainer = By.xpath("//div[@class='d-flex justify-content-between align-items-center']");
    private By budgetSpans = By.xpath("//div[@class='d-flex justify-content-between align-items-center']/span");
    
    public void checkBudgetForProject() throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 
    	 
    	 //open the dropdown once and count number of options
    	// wait.until(ExpectedConditions.elementToBeClickable(dd_SelectProject)).click();
    	 dd_SelectProject.click();
    	 Thread.sleep(3000);
    	 List<WebElement> initialProjects =wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(projectOptions));
    	 int projectCount = initialProjects.size();
    	 System.out.println("Total number of projects in dropdown is :"+ projectCount);
    	 
    	 dd_SelectProject.click();
    	
    	 // 2) Loop by index so we don't rely on text locators
    	 for(int i=0; i<projectCount; i++) {
    		 //Click on dropdown each time
    		 wait.until(ExpectedConditions.elementToBeClickable(dd_SelectProject)).click();
    		 
    		 //Refetch the project list to avoid stale element
    		 List<WebElement> projects = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(projectOptions));
    		 
    		 WebElement currentProject = projects.get(i);
    		 String ProjectName = currentProject.getText().trim();
    		 System.out.println("Selecting project "+ProjectName);
    		 currentProject.click();
    		 
    		 //wait for budget section for this project
    		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(budgetContainer));
    		 
    		 List<WebElement> budgets = driver.findElements(budgetSpans);
    		 if(budgets.isEmpty()) {
    			 System.out.println("No budget details for the project "+ProjectName);
    		 }else {
    			 for(WebElement budget:budgets) {
    				 System.out.println("Budget for "+ProjectName+ "is :"+ budget.getText());
    			 }
    		 }
    		 
    	 }
    }

}
