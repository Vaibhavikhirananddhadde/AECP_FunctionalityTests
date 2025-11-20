package tests;

import org.testng.annotations.Test;
import base.BaseClass;
import pages.LandingPage;
import pages.PM_DashboardPage;
import pages.PM_TaskPage;
import pages.SEng_DashboardPage;
import pages.SEng_LabourPage;
import pages.SEng_ProjectPage;
import pages.SEng_TaskPage;
import pages.SEng_TaskReportPage;

public class SiteEngineer_Panel_Tests extends BaseClass {
	public LandingPage land;
	public SEng_DashboardPage SE_dashboard;
	public SEng_ProjectPage SE_project;
	public SEng_TaskPage SE_task;
	public SEng_TaskReportPage SE_taskreport;
	public PM_DashboardPage PM_dashboard;
	public PM_TaskPage PM_task;
	public SEng_LabourPage SE_labour;

	// Site Engineer should be able to see the project details which he involved in.
	@Test
	public void AECP_SE_TC001() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnProjects();
		SE_project = new SEng_ProjectPage();
		SE_project.checkProjectsDisplayed();
	}

	// Site Engineer can add Task to himself and others.
	@Test
	public void AECP_SE_TC002() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.addTask("Test Project1", "Task new", "Vaibhavi");
	}

	// Site Engineer can filter tasks by Project.
	@Test
	public void AECP_SE_TC003() throws Exception {
		// waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.filterTasks_projects();
	}

	// Site Engineer can filter tasks by Employee.
	@Test
	public void AECP_SE_TC004() throws Exception {
		// waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.filterTasks_Employee();
	}

	// Site Engineer can filter tasks by period.
	@Test
	public void AECP_SE_TC005() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.filterTask_period();
	}

	// Site Engineer can view tasks.
	@Test
	public void AECP_SE_TC006() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.viewTaskDetails();
	}

	// site engineer can edit tasks
	@Test
	public void AECP_SE_TC007() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.editTaskDetails();
	}

	// Site engineer can delete tasks
	@Test(enabled = false)
	public void AECP_SE_TC008() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.deleteTask();
	}

	// Site Engineer should be able to generate task report.
	@Test
	public void AECP_SE_TC009() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTaskReport();
		SE_taskreport = new SEng_TaskReportPage();
		SE_taskreport.generateReport();
	}

	/*
	 * Edit and Delete task options are displayed only for the tasks which are
	 * assigned for ourself and if user assigns task for others
	 */
	@Test
	public void AECP_SE_TC010() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.addTask("Test Project", "Task for Others", "Ashwini Satti");
		driver.get("https://aecp.aecearth.io/site-admin/site-management/task2");
		SE_task.checkEditDelete_Displayed();
	}

	// Edit and Delete task buttons should not be there if others assign task to us.
	@Test
	public void AECP_SE_TC011() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		PM_dashboard = new PM_DashboardPage();
		PM_dashboard.clickOnTask();
		PM_task = new PM_TaskPage();
		PM_task.addTask();
		PM_dashboard.logout();

		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		driver.get("https://aecp.aecearth.io/site-admin/site-management/task2");
		SE_task = new SEng_TaskPage();
		SE_task.checkEditDelete_notDisplayed();
	}

	// Site Engineer can add labour and track their attendance.
	@Test
	public void AECP_SE_TC012() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnLabour();
		SE_labour = new SEng_LabourPage();
		SE_labour.addNewLabour();
	}

}
