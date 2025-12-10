package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.GM_DashboardPage;
import pages.GM_LeaveManagementPage;
import pages.GM_ResourceApprovalPage;
import pages.HR_AttendancePage;
import pages.HR_DashboardPage;
import pages.HR_DepartmentPage;
import pages.HR_EmployeesPage;
import pages.HR_LeaveManagementPage;
import pages.HR_ResourceAllocationPage;
import pages.HR_RolePage;
import pages.LandingPage;
import pages.PM_BudgetPage;
import pages.PM_DashboardPage;
import pages.PM_EngineersPage;
import pages.PM_LeaveManagementPage;
import pages.PM_MaterialRequestPage;
import pages.PM_ProjectPage;
import pages.PM_ResourceRequisitionPage;
import pages.PM_TaskPage;
import pages.PM_TaskReportPage;
import pages.SEng_DashboardPage;
import pages.SEng_ResourceRequestPage;

public class PM_Panel_Tests extends BaseClass {
	private static final Logger logger = LogManager.getLogger(PM_Panel_Tests.class);
	
	public LandingPage land;
	public HR_DashboardPage hr_dashboard;
	public PM_DashboardPage pm_dashboard;
	public PM_ResourceRequisitionPage pm_resourceRequisition;
	public GM_DashboardPage gm_dashboard;
	public GM_ResourceApprovalPage gm_resourceApproval;
	public HR_ResourceAllocationPage hr_resourceAllocation;
	public SEng_DashboardPage se_dashboard;
	public SEng_ResourceRequestPage se_resourceRequest;
	public HR_LeaveManagementPage hr_leaveManagement;
	public GM_LeaveManagementPage gm_leaveManagement;
	public HR_AttendancePage hr_attendance;
	public HR_EmployeesPage hr_employees;
	public HR_DepartmentPage hr_department;
	public HR_RolePage hr_role;
	public PM_ProjectPage pm_project;
	public PM_TaskPage pm_task;
	public PM_MaterialRequestPage pm_materialRequest;
	public PM_EngineersPage pm_engineer;
	public PM_BudgetPage pm_budget;
	public PM_TaskReportPage pm_taskReport;
	public PM_LeaveManagementPage pm_leaveManagement;

	// Project Manager should see Total projects, To Do projects, In-Progress
	// projects, Completed Tasks in the dashboard.
	@Test
	public void AECP_PM_TC001() throws Exception {
		logger.info("====Starting AECP_PM_TC001 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Dashboard");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.checkKPIcardsDisplayed();
		
		logger.info("====Finished AECP_PM_TC001 test ====");
	}

	// The project manager can see project details.
	@Test
	public void AECP_PM_TC002() throws Exception {
		logger.info("====Starting AECP_PM_TC002 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to projects page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnProjects();
		pm_project = new PM_ProjectPage();
		pm_project.checkProjectDetails();
		
		logger.info("====Finished AECP_PM_TC002 test====");
	}

	/*
	 * Project Manager can assign task to site engineer, general manager, store
	 * manager, HR but general manager, store manager, HR does not has Task
	 * management module - Project Manager himself update their task progress.
	 */
	@Test(enabled = true)
	public void AECP_PM_TC003() throws Exception {
		logger.info("====Starting AECP_PM_TC003 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Tasks page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnTask();
		pm_task = new PM_TaskPage();
		pm_task.addTask();

		logger.info("====Finished AECP_PM_TC003 test====");
	}

	// Project Manager, Site engineer can request for materials Store manager &
	// General manager approves/ rejects.
	@Test
	public void AECP_PM_TC004() throws Exception {
		logger.info("====Starting AECP_PM_TC004 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Material Requisition page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnMaterialRequisition();
		pm_materialRequest = new PM_MaterialRequestPage();
		pm_materialRequest.requestMaterial();
		
		logger.info("====Finished AECP_PM_TC004 test====");
	}

	// Project Manager should be able to see all the engineers for the selected project.
	@Test
	public void AECP_PM_TC005() throws Exception {
		logger.info("====Starting AECP_PM_TC005 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Site Engineer page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnSiteEngineer();
		pm_engineer = new PM_EngineersPage();
		pm_engineer.Check_SiteEngineersList();
		
		logger.info("====Finished AECP_PM_TC005 test====");

	}

	// Project Manager should be able to see the budget for the selected project
	// (General manager will add budget for the project while creating the project).
	@Test
	public void AECP_PM_TC006() throws Exception {
		logger.info("====Starting AECP_PM_TC006 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Budget page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnBudget();
		Thread.sleep(3000);
		pm_budget = new PM_BudgetPage();
		pm_budget.checkBudgetForProject();
		
		logger.info("====Finished AECP_PM_TC006 test====");

	}

	// Project Manager should be able to generate task report assigned to him.
	@Test
	public void AECP_PM_TC007() throws Exception {
		logger.info("====Starting AECP_PM_TC007 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Task Report page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnTaskReport();
		Thread.sleep(3000);
		pm_taskReport = new PM_TaskReportPage();
		pm_taskReport.generateReport();

		logger.info("====Finished AECP_PM_TC007 test====");
	}

	// Project Manager should see employee list for the selected project.
	@Test
	public void AECP_PM_TC008() throws Exception {
		logger.info("====Starting AECP_PM_TC008 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Site Engineer page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnSiteEngineer();
		pm_engineer = new PM_EngineersPage();
		pm_engineer.Check_SiteEngineersList();
		
		logger.info("====Finished AECP_PM_TC008 test====");
	}

	// Project Manager can apply for Leave.
	@Test
	public void AECP_PM_TC009() throws Exception {
		logger.info("====Starting AECP_PM_TC009 test====");
		waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("Navigating to Leave Management page");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.clickOnLeaveManagement();
		pm_leaveManagement = new PM_LeaveManagementPage();
		pm_leaveManagement.applyLeave();
		
		logger.info("====Finished AECP_PM_TC009 test====");
	}
	
	//Check page header is displayed for all the pages.
	@Test
	public void AECP_PM_TC010() throws Exception {
		logger.info("====Starting AECP_PM_TC010 test====");
        waitImplicit();
		
		logger.info("Logging in as Project Manager");
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		
		logger.info("checking page header is displayed for all the pages.");
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.checkPageHeaderDisplayed();
		logger.info("====Finished AECP_PM_TC010 test====");
	}

}
