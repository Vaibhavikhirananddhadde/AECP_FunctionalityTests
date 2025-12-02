package pages;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="(//div[@class='react-select__control css-13cymwt-control'])[1]")
	WebElement dd_selectDepartments;
	
	@FindBy(xpath="(//div[@class='react-select__indicators css-1wy0on6'])[2]")	WebElement dd_selectMembers;
	
	@FindBy(id="input-starting")
	WebElement date_start;
	
	@FindBy(id="input-ending")
	WebElement date_end;
	
	@FindBy(id="input-location")
	WebElement txt_location;
	
	@FindBy(xpath="//div[@aria-label='Editor editing area: main. Press ⌥0 for help.' and @contenteditable='true']")
	WebElement txt_description;
	
	@FindBy(xpath="//input[@id='input-BOQ-file']")
	WebElement file_BOQ;
	
	@FindBy(xpath="//input[@id='input-BOM-file']")
	WebElement file_BOM;
	
	@FindBy(xpath="//button[text()='Upload Documents']")
	WebElement upload_documents;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	public GM_ProjectsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addingProject() throws Exception {
		btn_addProject.click();
		Select clientnames = new Select(dd_clientName);
		clientnames.selectByValue("34");
		txt_projectName.sendKeys("Enter project name");
		txt_projectBudget.sendKeys("280000");
		Thread.sleep(3000);
		Select projectEngineer = new Select(dd_projectEngineer);
		projectEngineer.selectByValue("MI450002");
		
		 // 1) Select Department (React dropdown)
	    // -----------------------------
	    waitExplicit(dd_selectDepartments); 
	    dd_selectDepartments.click();
		
		selectFromReactDropdown(
	            By.xpath("//div[@id='react-select-2-listbox']/div"),
	            "Engineers"
	    );
		
		// 2) Select Member (React dropdown)
	    // -----------------------------
	  
	   dd_selectMembers.click();
	    selectFromReactDropdown(
	            By.xpath("//div[@id='react-select-3-listbox']/div"),
	            "Divya MN"
	    );
		
		date_start.sendKeys("03/11/2025");
		date_end.sendKeys("25/11/2025");
		txt_location.sendKeys("Bangalore");
		scrolltoview(file_BOQ);
		//txt_description.sendKeys("Testing");
		file_BOQ.sendKeys("/Users/admin/Downloads/attendance_list.xlsx");
		file_BOM.sendKeys("/Users/admin/Downloads/attendance_list.xlsx");
		upload_documents.sendKeys("/Users/admin/Downloads/attendance_list.xlsx");
		btn_Submit.click();

	}
	
	public void checkPlaceholder() {
		btn_addProject.click();
		String placeholder_projectName = txt_projectName.getAttribute("placeholder");
		System.out.println(placeholder_projectName);
		
		String placeholder_budget = txt_projectBudget.getAttribute("placeholder");
		System.out.println(placeholder_budget);
		
		String placeholder_location = txt_location.getAttribute("placeholder");
		System.out.println(placeholder_location);
	}

}
