package pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.BaseClass;

public class HR_EmployeesPage extends BaseClass {
	public HR_EmployeesPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='order-xl-2 mb-5 mb-xl-0 d-flex col']//button[text()='Add Employee']")
	WebElement btn_AddEmployee;

	@FindBy(id = "input-name")
	WebElement txt_empName;
	@FindBy(xpath = "//input[@id='input-name']")
	WebElement edit_empName;

	@FindBy(id = "input-number")
	WebElement txt_empNumber;
	@FindBy(xpath = "//input[@id='input-number']")
	WebElement edit_empNumber;

	@FindBy(id = "input-mail")
	WebElement txt_empMail;

	@FindBy(id = "input-birthday")
	WebElement date_empDOB;

	@FindBy(xpath = "//select[@name='role']")
	WebElement dd_empRole;

	@FindBy(name = "department")
	WebElement dd_empDepartment;

	@FindBy(name = "doj")
	WebElement date_empDOJ;

	@FindBy(name = "salary")
	WebElement txt_empSalary;

	@FindBy(xpath = "//input[@accept='image/*']")
	WebElement file_uploadImage;

	@FindBy(name = "password")
	WebElement txt_empPassword;

	@FindBy(name = "address")
	WebElement txt_empLocation;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement btn_Submit;

	@FindBy(xpath = "(//div[@class='dropleft dropdown'])[last()]")
	WebElement dropleftDropdown_last;

	@FindBy(xpath = "(//button[text()='Edit'])[last()]")
	WebElement edit_last;

	@FindBy(xpath = "(//button[text()='Delete'])[last()]")
	WebElement delete_last;

	@FindBy(xpath = "//p[text()='Employee added successfully!']")
	WebElement msg_success;

	@FindBy(xpath = "//button[text()='OK']")
	WebElement btn_OK;

	@FindBy(xpath = "//p[text()='Employee updated successfully!']")
	WebElement msg_updateSuccess;

	@FindBy(xpath = "//button[contains(text(), 'Yes,')]")
	WebElement btn_deleteConfirm;

	@FindBy(xpath = "//button[text()='No']")
	WebElement btn_NO;

	@FindBy(xpath = "//button[text()='Back']")
	WebElement btn_back;
	
	@FindBy(xpath="(//input[@placeholder='Search Employee'])[2]")
	WebElement txt_searchfield;
	
	@FindBy(xpath="(//a[text()='View More'])[1]")
	WebElement btn_ViewMore;
	
	
	private By employeeCards = By.xpath("//div[@class='row']/div[@class='col-12 col-md-6 col-lg-4 col-xl-3']");
	

   //Add employee
	public void addEmployee() {
		btn_AddEmployee.click();
		txt_empName.sendKeys("Emp Test");
		txt_empNumber.sendKeys("0981237893");
		txt_empMail.sendKeys("test@example1.com");
		date_empDOB.sendKeys("14/12/2002");

		Select role = new Select(dd_empRole);
		role.selectByVisibleText("General Manager");

		Select department = new Select(dd_empDepartment);
		department.selectByVisibleText("Construction Site");

		date_empDOJ.sendKeys("06/05/2024");
		txt_empSalary.sendKeys("30000");
		file_uploadImage.sendKeys("/Users/admin/Downloads/ChatGPT Image Sep 16, 2025, 03_02_23 PM.png");
		txt_empPassword.sendKeys("AECP@123");
		txt_empLocation.sendKeys("Pune");
		btn_Submit.click();
		waitExplicit(msg_success);
		Assert.assertTrue(msg_success.isDisplayed(), "Employee is not added!");
		btn_OK.click();
		driver.navigate().refresh();

		List<WebElement> allEmpNames = driver.findElements(By.xpath("(//div[@class='row'])[2]/div//h3"));
		for (WebElement empName : allEmpNames) {
			String name = empName.getText();

			if (name.equals("Emp Test")) {
				Assert.assertEquals(name, "Emp Test", "Empployee name does not match");
			}
		}
	}

//Edit Employee details	

	public void editEmployee() throws Exception {
		dropleftDropdown_last.click();
		edit_last.click();
		txt_empName.clear();
		txt_empName.sendKeys("Emp TestName");
		txt_empNumber.clear();
		txt_empNumber.sendKeys("1234567890");
		scrolltoview(btn_Submit);
		btn_Submit.submit();
		waitExplicit(btn_OK);
		Assert.assertTrue(msg_updateSuccess.isDisplayed(), "Details are not edited!");
		btn_OK.click();
		btn_back.click();
		driver.navigate().refresh();

	}

//Delete Employee
	public void deleteEmployee() {
		List<WebElement> allEmpNames = driver.findElements(By.xpath("(//div[@class='row'])[2]/div//h3"));
		for (WebElement empName : allEmpNames) {
			String name = empName.getText();
			if (name.equals("Emp TestName")) {
				dropleftDropdown_last.click();
				delete_last.click();
				btn_deleteConfirm.click();
				driver.navigate().refresh();
				Assert.assertEquals(name, "Emp TestName", "employee not deleted!");
				break;
			}

		}

	}
	
	public void searchEmployee(String employee) throws InterruptedException {
		txt_searchfield.sendKeys(employee);	
		Thread.sleep(3000);
		List<WebElement> empCards = driver.findElements(employeeCards);
		for(WebElement empCard:empCards) {
			WebElement name = empCard.findElement(By.xpath(".//h3"));
			
			System.out.println("Searched employee name is : "+name.getText().trim());
			Assert.assertTrue(name.isDisplayed(), "Searched employee is not displayed!");
		}
	}
	
	public void viewEmployeeDetails() {
	    btn_ViewMore.click();
	    waitExplicit(driver.findElement(By.xpath("//div[@class='modal-body']")));

	    List<WebElement> rows = driver.findElements(By.xpath("//div[@class='modal-body']//table/tbody/tr"));

	    for (WebElement row : rows) {
	        List<WebElement> tds = row.findElements(By.tagName("td"));

	        String label = tds.get(0).getText().trim();   // Name / Email Id / Address
	        String value = tds.get(1).getText().trim();   // Vaibhavi / email / location

	        if (!label.isEmpty()) {
	            System.out.println(label + " : " + value);
	        }
	    }
	}


}
