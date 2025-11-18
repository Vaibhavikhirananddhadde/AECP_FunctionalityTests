package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class SEng_ProjectPage extends BaseClass{
    public SEng_ProjectPage() {
    	PageFactory.initElements(driver, this);
    }
    
    private By projects = By.xpath("//div[@class='row']/div[@class='mb-4 col-lg-6 col-xl-3']");
    private By projectNames = By.xpath("//div[@class='row']/div[@class='mb-4 col-lg-6 col-xl-3']//h1");
    @FindBy(xpath="//span[@class='mb-0 text-sm font-weight-bold']") WebElement profileName;
    
    public void checkProjectsDisplayed() {
    	List<WebElement> allProjects = driver.findElements(projects);
    	List<WebElement> allProjectNames = driver.findElements(projectNames);
    	for(WebElement project:allProjects) {
    		Assert.assertTrue(project.isDisplayed());
    	}
    	for(WebElement projectName : allProjectNames) {
    		System.out.println("Site Engineer "+profileName.getText()+" involved in: \n"+ projectName.getText());
    	}
    	
    }
}
