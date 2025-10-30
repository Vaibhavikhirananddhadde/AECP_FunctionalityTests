package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;

public class GM_ProjectsPage extends BaseClass{
	@FindBy(xpath="//button[text()='Add Project']")
	WebElement btn_addProject;
	
	@FindBy(xpath="//select[@id='input-status' and @name='client_id']")
	WebElement dd_clientName;
	
	@FindBy(xpath="//input[@name='p_name']")
	WebElement txt_projectName;
	
	@FindBy(name="p_budget")
	WebElement txt_projectBudget;
	
	@FindBy(xpath="//select[@name='p_m_id']")
	WebElement dd_projectEngineer;
	
	public GM_ProjectsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addingProject() {
		btn_addProject.click();
		Select clientnames = new Select(dd_clientName);
		clientnames.selectByValue("34");
		txt_projectName.sendKeys("Enter project name");
		txt_projectBudget.sendKeys("280000");
		
		Select projectEngineer = new Select(dd_projectEngineer);
		projectEngineer.selectByValue("MI450002");
		
		
	}

}
