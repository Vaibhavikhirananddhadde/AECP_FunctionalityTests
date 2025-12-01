package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.GM_BudgetPage;
import pages.GM_ClientPage;
import pages.GM_DashboardPage;
import pages.GM_DepartmentPage;
import pages.GM_LeaveManagementPage;
import pages.GM_ProjectsPage;
import pages.GM_ResourceApprovalPage;
import pages.HR_DashboardPage;
import pages.HR_ResourceAllocationPage;
import pages.LandingPage;
import pages.PM_DashboardPage;
import pages.PM_ResourceRequisitionPage;

public class GM_Panel_Tests extends BaseClass {
	public LandingPage land;
	public HR_DashboardPage hr_dashboard;
	public PM_DashboardPage pm_dashboard;
	public PM_ResourceRequisitionPage pm_resourceRequisition;
	public GM_DashboardPage gm_dashboard;
	public GM_ResourceApprovalPage gm_resourceApproval;
	public HR_ResourceAllocationPage hr_resourceAllocation;
	public GM_LeaveManagementPage gm_leaveManagement;
	public GM_ClientPage gm_clientpage;
	public GM_ProjectsPage gm_projects;
	public GM_BudgetPage gm_budget;
	public GM_DepartmentPage gm_department;

	// GM can Approve/Reject resource requests.
	@Test(enabled = true)
	public void AECP_GM_TC001() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnResourceApproval();
		gm_resourceApproval = new GM_ResourceApprovalPage();
		gm_resourceApproval.approveResourceRequest();

	}

	// GM can reject resource requests.
	@Test
	public void AECP_GM_TC002() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnResourceApproval();
		gm_resourceApproval = new GM_ResourceApprovalPage();
		gm_resourceApproval.rejectResourceRequest();

	}

	// GM can approve leave requests raised by HR.
	@Test
	public void AECP_GM_TC003() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnLeaveManagement();
		gm_leaveManagement = new GM_LeaveManagementPage();
		gm_leaveManagement.approveHRLeave();

	}

	// GM can reject leave requests raised by HR.
	@Test
	public void AECP_GM_TC004() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnLeaveManagement();
		gm_leaveManagement = new GM_LeaveManagementPage();
		gm_leaveManagement.rejectHRLeave();
	}

	// GM can add Clients.
	@Test
	public void AECP_GM_TC005() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnClient();
		gm_clientpage = new GM_ClientPage();
		gm_clientpage.addClient();
	}

	// GM can add projects.
	@Test(enabled = true) // Failing
	public void AECP_GM_TC006() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnProject();
		gm_projects = new GM_ProjectsPage();
		gm_projects.addingProject();
	}

	/*
	 * General Manager should be able to see the budget for the selected project
	 * (General manager will add budget for the project while creating the project).
	 */
	@Test
	public void AECP_GM_TC007() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnBudget();
		gm_budget = new GM_BudgetPage();
		gm_budget.checkBudgetForProject();
	}

	// The general manager should see all the added departments.(HR adds the
	// department).
	@Test
	public void AECP_GM_TC008() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnDepartment();
		Thread.sleep(3000);
		gm_department = new GM_DepartmentPage();
		gm_department.checkDepartmentDisplayed();

	}

}
