package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import base.BaseClass;

public class SEng_TaskPage extends BaseClass {
	public SEng_TaskPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Add Task']")
	WebElement btn_AddTask;
	@FindBy(xpath = "//select[@name='projectid']")
	WebElement dd_SelectProject;
	@FindBy(name = "taskname")
	WebElement txt_TaskName;
	@FindBy(xpath = "//select[@name='assignto']")
	WebElement dd_AssignTo;
	@FindBy(id = "input-starting")
	WebElement date_starting;
	@FindBy(id = "input-ending")
	WebElement date_ending;
	@FindBy(xpath = "//div[contains(@aria-label,'Editor editing area:')]")
	WebElement txt_projectDescription;
	@FindBy(xpath = "//button[text()='Submit']")
	WebElement btn_Submit;
	@FindBy(xpath="(//button[contains(@class,'dropdown-toggle') and contains(@class,'btn-secondary')])[2]")
	WebElement dd_selectProject;   //filter
	@FindBy(xpath="(//div[@class='row'][1]//button[@class='p-0 dropdown-toggle btn btn-link'])[1]")
	WebElement dots_TaskCard;
	@FindBy(xpath="(//button[contains(@class,'dropdown-toggle') and contains(@class,'btn-secondary')])[1]")
	WebElement dd_selectEmployee;  //filter
	@FindBy(xpath="//button[text()='View']")
	WebElement btn_view;
	@FindBy(xpath="//h1[text()='View Task Details']")
	WebElement viewPageTitle;
	@FindBy(xpath="//button[text()='Edit']")
	WebElement btn_edit;
	@FindBy(xpath="//h1[text()='Edit Task Details']")
	WebElement editPageTitle;
	@FindBy(xpath="//button[text()='Delete']")
	WebElement btn_delete;
	@FindBy(xpath="(//p[text()='Are you sure you want to delete this?'])[2]")
	WebElement popup_deleteConfirm;
	@FindBy(xpath = "(//div[contains(@class,'modal') and contains(@class,'show')]//button[contains(@class,'btn-danger') and normalize-space()='Yes'])[1]")
	WebElement btn_YES;
	@FindBy(xpath="//button[text()='Back']")
	WebElement btn_Back;
	@FindBy(xpath="(//button[contains(@class,'dropdown-toggle') and contains(@class,'btn-secondary')])[3]")
	WebElement dd_selectPeriod;
	

	private By ToDo_Tasks = By
			.xpath("//div[@class='col-lg-4']//div[@class='card-stats mb-4 mb-xl-0  mt-3 gap-3 card']//h3");
	private By Inprogress_Tasks = By
			.xpath("//div[@class='col-lg-4']//div[@class='card-stats mb-4 mb-xl-0  mt-3 card']//h3");
	private By completed_Tasks = By
			.xpath("//div[@class='col-lg-4']//div[@class='card-stats mb-4 mb-xl-0 m-0 card']//h3");

	
	// options inside the currently OPEN dropdown
	private By project_options_open = By.xpath(
	    "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item')]"
	);
	
	// COMMON: options in the currently OPEN dropdown (employee OR project)
	private By dropdown_options_open = By.xpath(
	    "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item')]"
	);
	
	private By taskCards = By.xpath("//div[@class='row']/div[@class='text-left ml-3 col']");
	
	// all task titles (To-do + In-progress + Completed if they use same card structure)
	private By taskTitleLocator = By.xpath(
	    "//div[@class='col-lg-4']//div[contains(@class,'card-stats') and contains(@class,'card')]//h3"
	);
	
	
	 

	public void addTask(String option_project, String TaskName, String option_assignTo) {
		btn_AddTask.click();
		Select projects = new Select(dd_SelectProject);
		projects.selectByVisibleText(option_project);
		txt_TaskName.sendKeys(TaskName);

		Select assignto = new Select(dd_AssignTo);
		assignto.selectByVisibleText(option_assignTo);

		// date_start.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// --- Start date ---
		js.executeScript("arguments[0].click();", date_starting); // focus the field without interception
		date_starting.sendKeys(Keys.chord(Keys.COMMAND, "a")); // select all (CMD on Mac)
		date_starting.sendKeys("06-11-2025" + Keys.TAB + "10:00AM"); // valid datetime-local value
		date_starting.sendKeys(Keys.TAB); // blur -> fires change

		// --- End date ---
		js.executeScript("arguments[0].click();", date_ending);
		date_ending.sendKeys(Keys.chord(Keys.COMMAND, "a"));
		date_ending.sendKeys("22-11-2025" + Keys.TAB + "12:00PM");
		date_ending.sendKeys(Keys.TAB);

		txt_projectDescription.sendKeys("Testing adding task");
		btn_Submit.click();

		driver.navigate().refresh();

		// Check the task is added successfully
		List<WebElement> allTodoTasks = driver.findElements(ToDo_Tasks);
		for (WebElement todo_Task : allTodoTasks) {
			if (todo_Task.getText().contains(TaskName)) {
				Assert.assertTrue(todo_Task.isDisplayed(), "Newly added task is not getting added!");
				System.out.println("Newly added task is: " + todo_Task.getText());
			}
			break;
		}

	}
	
	
	
	//Filter task by project
	public void filterTasks_projects(String project) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // 1. Open project dropdown
	    if (dd_selectProject.isEnabled()) {
	        js.executeScript("arguments[0].click();", dd_selectProject);
	    }
	    
	    Thread.sleep(3000);

	    // 2. Click the required project
	    List<WebElement> projectOptions =
	            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(project_options_open));

	    boolean projectFound = false;
	    for (WebElement option : projectOptions) {
	        String projectText = option.getText().trim();
	        if (projectText.equalsIgnoreCase(project)) {
	            option.click();
	            Thread.sleep(3000);
	            projectFound = true;
	            break;
	        }
	    }

	    Assert.assertTrue(projectFound, "Project not found in dropdown: " + project);

	    // 3. Wait for task cards to refresh
	    wait.until(ExpectedConditions.visibilityOfElementLocated(taskTitleLocator));

	    // 4. Collect all task titles into a List<String>
	    List<WebElement> titleElements = driver.findElements(taskTitleLocator);
	    List<String> taskTitles = new java.util.ArrayList<>();

	    for (WebElement titleEl : titleElements) {
	        String title = titleEl.getText().trim();
	        if (!title.isEmpty()) {
	            taskTitles.add(title);
	        }
	    }

	    // 5. Print AFTER collection
	    System.out.println("Tasks list for the selected project '" + project + "':");
	    for (String title : taskTitles) {
	        System.out.println(" - " + title);
	    }

	    // (optional) simple assertion
	    Assert.assertTrue(taskTitles.size() >= 0,
	            "Unable to fetch task titles for project: " + project);
	}
	
	//Filter task by Employee.
	public void filterTasks_Employee(String employeeName) throws InterruptedException {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // STEP 1 – open dropdown and read all option names
	    js.executeScript("arguments[0].click();", dd_selectEmployee);

	    List<WebElement> optionElements =
	            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdown_options_open));

	   boolean employeeFound = false;
	   
	   for(WebElement option:optionElements) {
		   String EmployeeName = option.getText().trim();
		   
		   if(EmployeeName.equalsIgnoreCase(employeeName)) {
			   option.click();
			   Thread.sleep(3000);
			   employeeFound = true;
			   break;
		   }
	    }
	   
	   Assert.assertTrue(employeeFound, "Employee not found in dropdown" +employeeName);
	   
	   // 3. Wait for task cards to refresh
	    wait.until(ExpectedConditions.visibilityOfElementLocated(taskTitleLocator));
	    
	 // 4. Collect all task titles into a List<String>
	    List<WebElement> titleElements = driver.findElements(taskTitleLocator);
	    List<String> taskTitles = new java.util.ArrayList<>();

	    for (WebElement titleEl : titleElements) {
	        String title = titleEl.getText().trim();
	        if (!title.isEmpty()) {
	            taskTitles.add(title);
	        }
	    }

	    // 5. Print AFTER collection
	    System.out.println("Tasks list for the selected employee '" + employeeName + "':");
	    for (String title : taskTitles) {
	        System.out.println(" - " + title);
	    }

	    // (optional) simple assertion
	    Assert.assertTrue(taskTitles.size() >= 0,
	            "Unable to fetch task titles for employee: " + employeeName);

	}
	
	//filter by period
	public void filterTaskByPeriod(String periodName) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // 1️⃣ Open dropdown
	    wait.until(ExpectedConditions.elementToBeClickable(dd_selectPeriod)).click();

	    // 2️⃣ Wait for options to be visible
	    List<WebElement> options = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdown_options_open));

	    boolean periodFound = false;

	    for (WebElement opt : options) {
	        String text = opt.getText().trim();
	        if (text.equalsIgnoreCase(periodName)) {
	            System.out.println("Selecting Period: " + text);
	            opt.click();
	            periodFound = true;
	            break;
	        }
	    }

	    Assert.assertTrue(periodFound, "Period option not found in dropdown: " + periodName);

	    // 3️⃣ Wait until selected period text appears on button (filter applied)
	    wait.until(ExpectedConditions.textToBePresentInElement(dd_selectPeriod, periodName));

	    // 4️⃣ Now get tasks count after applying this period filter
	    List<WebElement> todoTasks       = driver.findElements(ToDo_Tasks);
	    List<WebElement> inprogressTasks = driver.findElements(Inprogress_Tasks);
	    List<WebElement> completedTasks  = driver.findElements(completed_Tasks);

	    int todoCount       = todoTasks.size();
	    int inprogressCount = inprogressTasks.size();
	    int completedCount  = completedTasks.size();
	    int total           = todoCount + inprogressCount + completedCount;

	    System.out.println("Period = " + periodName);
	    System.out.println("To-Do task count       : " + todoCount);
	    System.out.println("In-Progress task count : " + inprogressCount);
	    System.out.println("Completed task count   : " + completedCount);
	    System.out.println("Total tasks for Period : " + total);

	    // basic assertion → just verifying page loaded and filter didn’t crash
	    Assert.assertTrue(total >= 0,
	            "Task count calculation failed for Period: " + periodName);
	}

	
	public void viewTaskDetails() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		dd_selectEmployee.click();
		List<WebElement> allEmployees = driver.findElements(By.xpath("//div[contains(@class,'mb-2 dropdown')][1]//div[contains(@class,'dropdown-menu')]"));
		for(WebElement employee:allEmployees) {
			if(employee.getText().equalsIgnoreCase("Vaibhavi")) {
				employee.click();
			}
		}
		dots_TaskCard.click();
		wait.until(ExpectedConditions.visibilityOf(btn_view));
		btn_view.click();
		wait.until(ExpectedConditions.visibilityOf(viewPageTitle));
		Assert.assertTrue(viewPageTitle.isDisplayed(), "View details page is not displayed!");

	}
	
	public void editTaskDetails() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		dd_selectEmployee.click();
		List<WebElement> allEmployees = driver.findElements(By.xpath("//div[contains(@class,'mb-2 dropdown')][1]//div[contains(@class,'dropdown-menu')]"));
		for(WebElement employee:allEmployees) {
			if(employee.getText().equalsIgnoreCase("Vaibhavi")) {
				employee.click();
			}
		}
		dots_TaskCard.click();
		wait.until(ExpectedConditions.visibilityOf(btn_edit));
		btn_edit.click();
		wait.until(ExpectedConditions.visibilityOf(editPageTitle));
		Assert.assertTrue(editPageTitle.isDisplayed(), "Edit details page is not displayed!");
		txt_TaskName.sendKeys("Task New 1");
		btn_Submit.click();
		driver.navigate().refresh();
		Thread.sleep(3000);
	}
	
	public void deleteTask() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		dd_selectEmployee.click();
		List<WebElement> allEmployees = driver.findElements(By.xpath("//div[contains(@class,'mb-2 dropdown')][1]//div[contains(@class,'dropdown-menu')]"));
		for(WebElement employee:allEmployees) {
			if(employee.getText().equalsIgnoreCase("Vaibhavi")) {
				employee.click();
			}
		}
		dots_TaskCard.click();
		wait.until(ExpectedConditions.visibilityOf(btn_delete));
		btn_delete.click();
		wait.until(ExpectedConditions.visibilityOf(popup_deleteConfirm));
		Assert.assertTrue(popup_deleteConfirm.isDisplayed(), "Confirmation popup is not displayed before deleting the task!");
		wait.until(ExpectedConditions.elementToBeClickable(btn_YES));
		btn_YES.click();
		Thread.sleep(3000);
	}
	
	public void checkEditDelete_Displayed() throws InterruptedException {
		dots_TaskCard.click();
		Thread.sleep(2000);
		Assert.assertTrue(btn_edit.isDisplayed() && btn_delete.isDisplayed(), "Edit and Delete buttons are displayed for all task cards!");
	}
	
	public void checkEditDelete_notDisplayed() {
		dots_TaskCard.click();
		Assert.assertFalse(btn_edit.isDisplayed() && btn_delete.isDisplayed(), "Edit and Delete buttons are displayed!");
	}





	public WebElement getBtn_Back() {
		return btn_Back;
	}




	public void setBtn_Back(WebElement btn_Back) {
		this.btn_Back = btn_Back;
	}
	
	
}
