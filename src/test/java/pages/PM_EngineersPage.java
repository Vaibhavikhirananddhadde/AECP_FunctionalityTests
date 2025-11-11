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

public class PM_EngineersPage extends BaseClass{
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary']") WebElement dd_selectProject;
	private By projectOptions = By.xpath("//div[@class='dropdown-menu show']/button");
	private By empNameContainer = By.xpath("//div[@class='row']//div[@class='fw-bold mb-1']");
	
	public PM_EngineersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void Check_SiteEngineersList() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject));
		dd_selectProject.click();
		
		 Thread.sleep(3000);
    	 List<WebElement> initialProjects =wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(projectOptions));
    	 int projectCount = initialProjects.size();
    	 System.out.println("Total number of projects in dropdown is :"+ projectCount);
    	 dd_selectProject.click();
    	 
    	 for(int i=0; i<projectCount; i++) {
    		//Click on dropdown each time
    		 wait.until(ExpectedConditions.elementToBeClickable(dd_selectProject)).click();
    		 
    		 List<WebElement> Projects =wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(projectOptions));
    		 
    		 WebElement currentProject = Projects.get(i);
    		 String ProjectName = currentProject.getText().trim();
    		 System.out.println("Selecting project "+ProjectName);
    		 currentProject.click();
    		 
    		 List<WebElement> empNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(empNameContainer));
    		 for(WebElement employee:empNames) {
    			 String employeeName = employee.getText().trim();
    			 System.out.println("Employees for the selected project "+ProjectName+ " is : "+employeeName);
    		 }
    	 }
	}

}
