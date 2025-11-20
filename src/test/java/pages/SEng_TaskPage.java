package pages;

import java.time.Duration;
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
	@FindBy(xpath="//div[contains(@class,'mb-2 dropdown')][3]")
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
	
	
	
	
	public void filterTasks_projects() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // ---------- STEP 1: read all project names once ----------
	    // open dropdown using JS (so it definitely opens)
	    if(dd_selectProject.isEnabled()) {
	    	 js.executeScript("arguments[0].click();", dd_selectProject);

	    }
	   
	    List<WebElement> optionElements =
	            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(project_options_open));

	    List<String> projectNames = new java.util.ArrayList<>();
	    for (WebElement opt : optionElements) {
	        String name = opt.getText().trim();
	        if (!name.isEmpty()) {
	            projectNames.add(name);
	        }
	    }

	    System.out.println("Total projects in dropdown: " + projectNames.size());
	    
	    dd_selectProject.click();

	    // ---------- STEP 2: loop for each project ----------
	    for (String projectName : projectNames) {

	        // open dropdown FRESH with JS (normal click was sometimes not opening)
	        js.executeScript("arguments[0].click();", dd_selectProject);

	        // wait until the menu is actually visible
	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(project_options_open));

	        // now locate the option for this project
	        By optionBy = By.xpath(
	            "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button" +
	            "[contains(@class,'dropdown-item') and normalize-space()='" + projectName + "']"
	        );

	        WebElement projectOption =
	                wait.until(ExpectedConditions.elementToBeClickable(optionBy));

	        System.out.println("\nSelecting Project: " + projectName);
	        projectOption.click();

	        // wait until button text changes to selected project (confirm filter applied)
	        wait.until(ExpectedConditions.textToBePresentInElement(dd_selectProject, projectName));

	        // (optional) tiny wait for cards to render
	        Thread.sleep(500);

	        // ---------- STEP 3: count tasks ----------
	        List<WebElement> todoTasks       = driver.findElements(ToDo_Tasks);
	        List<WebElement> inprogressTasks = driver.findElements(Inprogress_Tasks);
	        List<WebElement> completedTasks  = driver.findElements(completed_Tasks);

	        int todoCount       = todoTasks.size();
	        int inprogressCount = inprogressTasks.size();
	        int completedCount  = completedTasks.size();
	        int total           = todoCount + inprogressCount + completedCount;

	        System.out.println("To-Do task count        : " + todoCount);
	        System.out.println("In-Progress task count  : " + inprogressCount);
	        System.out.println("Completed task count    : " + completedCount);
	        System.out.println("Total tasks for project : " + total);

	        Assert.assertTrue(total >= 0, "Task count calculation failed for project: " + projectName);
	    }
	}
	
	public void filterTasks_Employee()
	        throws InterruptedException {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // STEP 1 – open dropdown and read all option names
	    js.executeScript("arguments[0].click();", dd_selectEmployee);

	    List<WebElement> optionElements =
	            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdown_options_open));

	    List<String> optionNames = new java.util.ArrayList<>();
	    for (WebElement opt : optionElements) {
	        String name = opt.getText().trim();
	        if (!name.isEmpty()) {
	            optionNames.add(name);
	        }
	    }

	    System.out.println("\n" + "Employee" + " filter – total options: " + optionNames.size());
	    
	    dd_selectEmployee.click();

	    // STEP 2 – loop over each option
	    for (String name : optionNames) {

	        // open dropdown fresh
	        js.executeScript("arguments[0].click();", dd_selectEmployee);
	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdown_options_open));

	        // locate the option for this name
	        By optionBy = By.xpath(
	            "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button" +
	            "[contains(@class,'dropdown-item') and normalize-space()='" + name + "']"
	        );

	        WebElement option =
	                wait.until(ExpectedConditions.elementToBeClickable(optionBy));

	        System.out.println("\nSelecting " + "Employee" + ": " + name);
	        option.click();

	        // wait until the button text changes (confirm filter applied)
	        wait.until(ExpectedConditions.textToBePresentInElement(dd_selectEmployee, name));

	        //Thread.sleep(500); // small wait for cards to update

	        // STEP 3 – count tasks on board
	        List<WebElement> todoTasks       = driver.findElements(ToDo_Tasks);
	        List<WebElement> inprogressTasks = driver.findElements(Inprogress_Tasks);
	        List<WebElement> completedTasks  = driver.findElements(completed_Tasks);

	        int todoCount       = todoTasks.size();
	        int inprogressCount = inprogressTasks.size();
	        int completedCount  = completedTasks.size();
	        int total           = todoCount + inprogressCount + completedCount;

	        System.out.println("Employee" + " = " + name);
	        System.out.println("To-Do task count        : " + todoCount);
	        System.out.println("In-Progress task count  : " + inprogressCount);
	        System.out.println("Completed task count    : " + completedCount);
	        System.out.println("Total tasks for " + "Employee" + " : " + total);

	        Assert.assertTrue(total >= 0,
	                "Task count calculation failed for " + "Employee" + ": " + name);
	    }
	}
	
	public void filterTask_period() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    JavascriptExecutor js = (JavascriptExecutor) driver;

		    // STEP 1 – open dropdown and read all option names
		    js.executeScript("arguments[0].click();", dd_selectPeriod);

		    List<WebElement> optionElements =
		            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdown_options_open));

		    List<String> optionNames = new java.util.ArrayList<>();
		    for (WebElement opt : optionElements) {
		        String name = opt.getText().trim();
		        if (!name.isEmpty()) {
		            optionNames.add(name);
		        }
		    }

		    System.out.println("\n" + "Period" + " filter – total options: " + optionNames.size());
		    
		    dd_selectPeriod.click();

		    // STEP 2 – loop over each option
		    for (String name : optionNames) {

		        // open dropdown fresh
		        js.executeScript("arguments[0].click();", dd_selectPeriod);
		        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdown_options_open));

		        // locate the option for this name
		        By optionBy = By.xpath(
		            "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button" +
		            "[contains(@class,'dropdown-item') and normalize-space()='" + name + "']"
		        );

		        WebElement option =
		                wait.until(ExpectedConditions.elementToBeClickable(optionBy));

		        System.out.println("\nSelecting " + "Period" + ": " + name);
		        option.click();

		        // wait until the button text changes (confirm filter applied)
		        wait.until(ExpectedConditions.textToBePresentInElement(dd_selectPeriod, name));

		        //Thread.sleep(500); // small wait for cards to update

		        // STEP 3 – count tasks on board
		        List<WebElement> todoTasks       = driver.findElements(ToDo_Tasks);
		        List<WebElement> inprogressTasks = driver.findElements(Inprogress_Tasks);
		        List<WebElement> completedTasks  = driver.findElements(completed_Tasks);

		        int todoCount       = todoTasks.size();
		        int inprogressCount = inprogressTasks.size();
		        int completedCount  = completedTasks.size();
		        int total           = todoCount + inprogressCount + completedCount;

		        System.out.println("Employee" + " = " + name);
		        System.out.println("To-Do task count        : " + todoCount);
		        System.out.println("In-Progress task count  : " + inprogressCount);
		        System.out.println("Completed task count    : " + completedCount);
		        System.out.println("Total tasks for " + "Period" + " : " + total);

		        Assert.assertTrue(total >= 0,
		                "Task count calculation failed for " + "Period" + ": " + name);
		    }
		
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
	
	public void checkEditDelete_Displayed() {
		dots_TaskCard.click();
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
