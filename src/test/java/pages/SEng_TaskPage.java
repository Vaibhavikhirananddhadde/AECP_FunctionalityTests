package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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
	@FindBy(xpath="//button[text()='Select Project']")
	WebElement dd_selectProject;
	

	private By ToDo_Tasks = By
			.xpath("//div[@class='col-lg-4']//div[@class='card-stats mb-4 mb-xl-0  mt-3 gap-3 card']//h3");
	private By Inprogress_Tasks = By
			.xpath("//div[@class='col-lg-4']//div[@class='card-stats mb-4 mb-xl-0  mt-3 card']//h3");
	private By completed_Tasks = By
			.xpath("//div[@class='col-lg-4']//div[@class='card-stats mb-4 mb-xl-0 m-0 card']//h3");
	private By project_options = By
			.xpath("//div[@class='mb-2 dropdown show']//button[@class='dropdown-item']");
	

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
	
	//Filter tasks
	/*public void filterTasks_projects() {
		dd_selectProject.click();
		List<WebElement> allProjects = driver.findElements(project_options);
		for(WebElement project:allProjects) {
			dd_selectProject.click();
			project.click();
			
			//check task counts for the selected project in to do section
			List<WebElement> todoTasks = driver.findElements(ToDo_Tasks);
			System.out.println("To-Do task count for the selected project : "+todoTasks.size());
			
			//check task counts for the selected project in in progress section
			List<WebElement> inprogressTasks = driver.findElements(Inprogress_Tasks);
			System.out.println("In-Progress task count for the selected project : "+inprogressTasks.size());
			
			//check task counts for the selected project in completed tasks section
			List<WebElement> completedTasks = driver.findElements(completed_Tasks);
			System.out.println("Completed task count for the selected project : "+completedTasks.size());
			
			int todoCount = todoTasks.size();
			int inprogressCount = inprogressTasks.size();
			int completedCount = completedTasks.size();
			int total = todoCount+inprogressCount+completedCount;
			
			System.out.println("Total tasks for the selected project is : "+total);
			}
		
	}*/
	
	public void filterTasks_projects() throws InterruptedException {
	    // Open dropdown first time
	    dd_selectProject.click();
	    Thread.sleep(3000);

	    // Get count first (not elements)
	    List<WebElement> projectList = driver.findElements(project_options);
	    int totalProjects = projectList.size();

	    for (int i = 1; i <= totalProjects; i++) {

	       jsClickOn(dd_SelectProject);
	       Thread.sleep(3000);
	        

	        // Re-locate fresh element (avoids stale element)
	        By nthProject = By.xpath("//div[@class='mb-2 dropdown show']/div[@class='dropdown-menu show']/button[" + i + "]");

	        WebElement project = driver.findElement(nthProject);
	        String projectName = project.getText();
	        System.out.println("\nSelecting Project: " + projectName);

	        project.click(); // SAFE CLICK ✔
	        
            driver.navigate().refresh();

	        // ---- Count task sections ----
	        List<WebElement> todoTasks = driver.findElements(ToDo_Tasks);
	        System.out.println("To-Do task count: " + todoTasks.size());

	        List<WebElement> inprogressTasks = driver.findElements(Inprogress_Tasks);
	        System.out.println("In-Progress task count: " + inprogressTasks.size());

	        List<WebElement> completedTasks = driver.findElements(completed_Tasks);
	        System.out.println("Completed task count: " + completedTasks.size());

	        int total = todoTasks.size() + inprogressTasks.size() + completedTasks.size();
	        System.out.println("Total tasks: " + total);
	    }
	}

}
