package tests;

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
import pages.PM_DashboardPage;
import pages.PM_ResourceRequisitionPage;
import pages.SEng_DashboardPage;
import pages.SEng_ResourceRequestPage;

public class HR_Panel_Tests extends BaseClass {
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

	// HR can see ‘Total employees’, ‘OnTime’, ‘Late’, ‘Leave Requests’ count.
	@Test
	public void AECP_HR_TC001() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		Thread.sleep(3000);
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.cardsDisplayed();
	}

	// Resource Requisition - Project manager will request for resources those
	// requests will be displayed in the HR and General manager panel.
	@Test
	public void AECP_HR_TC002() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginPM();
		pm_dashboard = new PM_DashboardPage();
		pm_dashboard.click_ResourceRequisition();
		pm_resourceRequisition = new PM_ResourceRequisitionPage();
		pm_resourceRequisition.requestResource();
		pm_dashboard.logout();

		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnResourceApproval();

		gm_resourceApproval = new GM_ResourceApprovalPage();
		gm_resourceApproval.checkResourcesRequested();

		gm_dashboard.logout();

		land.loginHR();
		driver.navigate().refresh();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnResourceAllocation();
		hr_resourceAllocation = new HR_ResourceAllocationPage();
		hr_resourceAllocation.checkResourceRequested();

	}

	// Resource Requisition - Site Engineer will request for resources those
	// requests will be displayed in the HR and General manager panel.
	@Test
	public void AECP_HR_TC003() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginSEng();

		se_dashboard = new SEng_DashboardPage();
		se_dashboard.clickOnResourceRequest();
		se_resourceRequest = new SEng_ResourceRequestPage();
		se_resourceRequest.requestResorce();
		se_dashboard.logout();

		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnResourceApproval();

		gm_resourceApproval = new GM_ResourceApprovalPage();
		gm_resourceApproval.checkResourcesRequested();

		gm_dashboard.logout();

		land.loginHR();
		driver.navigate().refresh();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnResourceAllocation();
		hr_resourceAllocation = new HR_ResourceAllocationPage();
		hr_resourceAllocation.checkResourceRequested();

	}

	// HR can Approve / Reject resource requests.
	@Test
	public void AECP_HR_TC004() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnResourceAllocation();
		hr_resourceAllocation = new HR_ResourceAllocationPage();
		hr_resourceAllocation.approveResourceRequest();
	}

	// HR - Resource requisition - click on view button - Details like Resource
	// type, quantity, Requested By, Description, general manager action, HR action,
	// General action should be displayed.
	@Test
	public void AECP_HR_TC005() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnResourceAllocation();
		hr_resourceAllocation = new HR_ResourceAllocationPage();
		hr_resourceAllocation.viewRequestDetails();

	}

	// HR can apply for Leave.
	@Test
	public void AECP_HR_TC006() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnLeaveManagement();
		hr_leaveManagement = new HR_LeaveManagementPage();
		hr_leaveManagement.applyLeave();
	}

	// If HR apply Leave then it should be displayed in general manager panel.
	@Test
	public void AECP_HR_TC007() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnLeaveManagement();
		hr_leaveManagement = new HR_LeaveManagementPage();
		hr_leaveManagement.applyLeave();
		hr_dashboard.logout();
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard = new GM_DashboardPage();
		gm_dashboard.clickOnLeaveManagement();
		Thread.sleep(3000);
		gm_leaveManagement = new GM_LeaveManagementPage();
		gm_leaveManagement.leaveRequestsDisplayed();
	}

	// HR should be able to see attendance details & download.
	@Test
	public void AECP_HR_TC008() throws Exception {
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnAttendance();
		hr_attendance = new HR_AttendancePage();
		hr_attendance.checkAttendanceDisplayed();

	}

	// HR can add employee, Edit & Delete employee / Invite employee.
	@Test
	public void AECP_HR_TC009() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnEmployee();
		System.out.println("Clicked on Employees from nav bar.");
		hr_employees = new HR_EmployeesPage();
		hr_employees.addEmployee();
		System.out.println("Employee added successfully.");
		hr_employees.editEmployee();
		System.out.println("Employee details updated successfully");
		hr_employees.deleteEmployee();
		System.out.println("Employee deleted successfully.");
	}

	// HR can Add, Delete Department.
	@Test
	public void AECP_HR_TC010() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnDepartment();
		hr_department = new HR_DepartmentPage();
		hr_department.addingDepartment();
		System.out.println("Department added successfully");
		waitImplicit();
		scrollDown();
		hr_department.deleteDepartment();
		System.out.println("Department deleted successfully");
	}

	// HR can edit Roles.
	@Test
	public void AECP_HR_TC011() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnRole();
		hr_role = new HR_RolePage();
		hr_role.editRole();
		System.out.println("Role got edited successfully");
		hr_role.cancelEditRole();
		System.out.println("Cancel Edit role is functioning well.");
	}
	
	//HR can search resources in Resource List
	@Test(groups= {"functional"})
	public void AECP_HR_TC013() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginHR();
		hr_dashboard = new HR_DashboardPage();
		hr_dashboard.clickOnResourceAllocation();
		hr_resourceAllocation= new HR_ResourceAllocationPage();
		hr_resourceAllocation.searchResources();
	}

}
