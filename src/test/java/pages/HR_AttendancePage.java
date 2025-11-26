package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class HR_AttendancePage extends BaseClass{
	public HR_AttendancePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][1]")
	WebElement dd_month;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][2]")
	WebElement dd_years;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][3]")
	WebElement dd_empName;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][4]")
	WebElement dd_status;
	
	@FindBy(xpath="(//button[text()='Select Date'])[2]")
	WebElement btn_date;
	
	@FindBy(xpath="(//button[text()='Download'])[2]")
	WebElement btn_download;
	
	@FindBy(xpath="//input[@placeholder='Search Employee' and @class='w-100 form-control']")
	WebElement txt_searchfield;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][1]")
	WebElement filter_month;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][2]")
	WebElement filter_year;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][3]")
	WebElement filter_employee;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][4]")
	WebElement filter_status;
	
	@FindBy(xpath="//button[@class='mr-2 dropdown-toggle btn btn-secondary']")
	WebElement dd_showEntries;
	
	@FindBy(xpath="//button[text()='Select Date' and @class='form-control-alternative btn btn-secondary']")
	WebElement btn_selectDate;
	
	private By td_dateInTable = By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr/td[2]");
	private By td_nameInTable = By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr/td[1]");
	private By td_statusInTable = By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr/td[5]");
	private By calendarDates = By.xpath("//div[@class='react-calendar__month-view__days']/button");
	
	public void checkAttendanceDisplayed() {
		Select months = new Select(dd_month);
		months.selectByVisibleText("September");
		
		Select years = new Select(dd_years);
		years.selectByVisibleText("2025");
		
		Select employees = new Select(dd_empName);
		employees.selectByVisibleText("Vaibhavi");
		
		Select status = new Select(dd_status);
		status.selectByVisibleText("online");
		
		List<WebElement> tableRows = driver.findElements(By.xpath("//table[contains(@class,'table-flush table')]/tbody/tr"));
		for(int i=0; i<tableRows.size();i++) {
			System.out.println(tableRows.get(i).getText());
		}
		
		waitExplicit(btn_download);
		
		String expectedURL = "https://aecp.aecearth.io/hr-admin/hr-management/attendance";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "Attendance details are not downloaded!.");
	}
	
	public void searchEmployee(String empName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txt_searchfield));
		txt_searchfield.sendKeys(empName);
		WebElement emp_name = driver.findElement(By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[1]"));
		Assert.assertEquals(emp_name.getText().trim(), empName, "Searchfield is not working properly!");
		
	}
	
	public void monthFilter(String month) {
		Select month_dd = new Select(filter_month);
		month_dd.selectByVisibleText(month);
		List<WebElement> DateList = driver.findElements(td_dateInTable);
		for(WebElement dateElement : DateList) {
			 String dateText = dateElement.getText().trim();    // Example: 2025-09-30

		        // Convert string to LocalDate (Java 8+)
		        LocalDate date = LocalDate.parse(dateText);
		        int Month = date.getMonthValue();   // 9 for September

		        // Assertion
		        Assert.assertEquals(Month, 9,  "❌ Found a date not in September: " + dateText);
		        System.out.println("✔ Date verified: " + dateText);
		    }
		    System.out.println("🎉 All attendance records belong to September!");
		}
	
	public void yearFilter(String year) {
		Select year_dd = new Select(filter_year);
		year_dd.selectByVisibleText(year);
		List<WebElement> DateList = driver.findElements(td_dateInTable);
		for(WebElement dateElement : DateList) {
			 String dateText = dateElement.getText().trim();    // Example: 2025-09-30

		        // Convert string to LocalDate (Java 8+)
		        LocalDate date = LocalDate.parse(dateText);
		        int Year = date.getYear();   // 9 for September

		        // Assertion
		        Assert.assertEquals(Year, 2025,  "❌ Found a date not in 2025: " + dateText);
		        System.out.println("✔ year verified: " + dateText);
		    }
		    System.out.println("🎉 All attendance records belong to 2025!");
	}
	public void employeeFilter(String empNameText) {
		Select employee = new Select(filter_employee);
		employee.selectByVisibleText(empNameText);
		List<WebElement> employeeList = driver.findElements(td_nameInTable);
		for(WebElement empName :employeeList) {
			String employeeName = empName.getText().trim();
			Assert.assertEquals(employeeName,empNameText, "Employee filter is not working!");
			System.out.println("✔ employee verified: " + employeeName);
		}
		 System.out.println("🎉 All attendance records belong to selected employee!");
	}
	
	public void statusFilter(String status) {
		Select statuses = new Select(filter_status);
		statuses.selectByVisibleText("online");
		List<WebElement> statusesList = driver.findElements(td_statusInTable);
		for(WebElement statusElement : statusesList) {
			Assert.assertEquals(statusElement.getText(),status, "status filter is not working!");
			System.out.println("✔ status verified: " + statusElement.getText());
		}
	}
	
	public void selectDate_Attendance(String date) throws InterruptedException {

	    btn_selectDate.click();
	    Thread.sleep(500);

	    // 1️⃣ Find the date again fresh
	    List<WebElement> dateList = driver.findElements(calendarDates);

	    for (int i = 0; i < dateList.size(); i++) {

	        // 2️⃣ REFIND element to avoid stale reference
	        WebElement dateElement = driver.findElements(calendarDates).get(i);

	        String dateText = dateElement.getText().trim();

	        if (dateText.equals(date)) {
	            dateElement.click(); // calendar refresh happens here
	            Thread.sleep(2000);
	            
	            // 3️⃣ RELOCATE attendance table rows after refresh
	            List<WebElement> employeeRows =
	                    driver.findElements(By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr"));

	            for (WebElement row : employeeRows) {

	                WebElement empNameElement = row.findElement(By.xpath("./td[1]"));
	                WebElement DateElement = row.findElement(By.xpath("./td[2]"));
	                WebElement statusElement = row.findElement(By.xpath("./td[5]"));

	                String empName = empNameElement.getText().trim();
	                String status = statusElement.getText().trim();

	                System.out.println(empName +" Logged in on "+DateElement.getText()+" → " + status);

	                Assert.assertTrue(empNameElement.isDisplayed(),
	                        "Employee row not visible after date selection!");
	            }
	            break;
	        }
	    }
	}
	
	
	

}
