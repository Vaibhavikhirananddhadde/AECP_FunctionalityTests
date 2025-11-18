package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.LandingPage;
import pages.SEng_DashboardPage;
import pages.SEng_ProjectPage;
import pages.SEng_TaskPage;

public class SiteEngineer_Panel_Tests extends BaseClass{
	public LandingPage land;
	public SEng_DashboardPage SE_dashboard;
	public SEng_ProjectPage SE_project;
	public SEng_TaskPage SE_task;
	
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
		SE_task.addTask("Test Project1", "Task new", "Ashwini");
	}
	
	//Site Engineer can filter tasks by Period, Project, Employee.
    @Test
    public void AECP_SE_TC003() throws Exception {
    	waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();
		SE_dashboard = new SEng_DashboardPage();
		SE_dashboard.clickOnTask();
		SE_task = new SEng_TaskPage();
		SE_task.filterTasks_projects();
    }


}
