package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.GM_DashboardPage;
import pages.GM_ResourceApprovalPage;
import pages.HR_DashboardPage;
import pages.HR_ResourceAllocationPage;
import pages.LandingPage;
import pages.PM_DashboardPage;
import pages.PM_ResourceRequisitionPage;

public class GM_Panel_Tests extends BaseClass{
	public LandingPage land;
	public HR_DashboardPage hr_dashboard;
	public PM_DashboardPage pm_dashboard;
	public PM_ResourceRequisitionPage pm_resourceRequisition;
	public GM_DashboardPage gm_dashboard;
	public GM_ResourceApprovalPage gm_resourceApproval;
	public HR_ResourceAllocationPage hr_resourceAllocation;
	
	
	
	 //GM can Approve/Reject resource requests.
    @Test(enabled=true)
    public void AECP_GM_TC001() throws Exception {
    	land = new LandingPage();
		land.clickLogin();
		land.loginGM();
		gm_dashboard =new GM_DashboardPage();
		gm_dashboard.clickOnResourceApproval();
		gm_resourceApproval = new GM_ResourceApprovalPage();
		gm_resourceApproval.approveResourceRequest();

    }

}
