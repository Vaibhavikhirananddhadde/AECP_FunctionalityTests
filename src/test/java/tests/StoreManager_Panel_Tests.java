package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.LandingPage;
import pages.StoreManager_DashboardPage;
import pages.StoreManager_InvoicePage;
import pages.StoreManager_StoreManagementPage;
import pages.StoreManager_VendorListPage;
import pages.StoreManager_VendorManagementPage;
import pages.StoreManager_WastageMaintenancePage;

public class StoreManager_Panel_Tests extends BaseClass {
	public LandingPage land;
	public StoreManager_DashboardPage storeDashboard;
	public StoreManager_StoreManagementPage storeManagement;
	public StoreManager_VendorListPage vendorList;
	public StoreManager_VendorManagementPage vendorManagement;
	public StoreManager_WastageMaintenancePage wastageMaintenance;
	public StoreManager_InvoicePage invoice;

	// Store Manager can add in-house stock (new or existing products).
	@Test
	public void AECP_SM_TC001() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnStoreManagement();
		storeManagement = new StoreManager_StoreManagementPage();
		storeManagement.addSingleInhouseStock();
	}

	// System will update stock quantity, total stock, and cost automatically.
	@Test
	public void AECP_SM_TC002() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnStoreManagement();
		storeManagement = new StoreManager_StoreManagementPage();
		storeManagement.addSingleInhouseStock();
		storeManagement.materialDelivery();
		storeManagement.materialReturn();
	}

	// Download/Export button allows Store Manager to download stock data (CSV/PDF).
	@Test
	public void AECP_SM_TC003() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnStoreManagement();
		storeManagement = new StoreManager_StoreManagementPage();
		storeManagement.generateReportAndDownload();
	}

	// Store Manager can open Vendor List and see vendor cards with name, email,
	// phone, avatar, and a View More link.
	@Test
	public void AECP_SM_TC004() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnVendorList();
		vendorList = new StoreManager_VendorListPage();
		vendorList.checkVendorCards();

	}

	// Store Manager can click “Add Vendor” to create a new vendor.
	@Test
	public void AECP_SM_TC005() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnVendorList();
		vendorList = new StoreManager_VendorListPage();
		vendorList.addVendor();

	}

	// Store Manager can edit vendor details from the 3-dot (kebab) menu on the
	// card.
	@Test
	public void AECP_SM_TC006() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnVendorList();
		vendorList = new StoreManager_VendorListPage();
		vendorList.editVendorDetails();

	}

	// Store Manager can click “Add Stock” to receive items from a Vendor into
	// Store.
	@Test
	public void AECP_SM_TC007() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnVendorManagement();
		vendorManagement = new StoreManager_VendorManagementPage();
		vendorManagement.addSingleStock(100);
	}

	// Data matches the movements from Vendor to Store, Vendor Material Delivery,
	// Vendor Material Return, Store to Vendor.
	@Test
	public void AECP_SM_TC008() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnVendorManagement();
		vendorManagement = new StoreManager_VendorManagementPage();
		vendorManagement.addSingleStock(100);
		vendorManagement.returnToVendor(5);
		vendorManagement.vendorMaterialDelivery(8);
		vendorManagement.VendorMaterialReturn(3);
	}

	// Store Manager can click “Add New Waste” to record a waste item (product, qty,
	// reason, date, location/project, disposal method, notes, attachment).
	@Test
	public void AECP_SM_TC009() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnWastageMaintenance();
		wastageMaintenance = new StoreManager_WastageMaintenancePage();
		wastageMaintenance.addDamagedWaste();
	}

	// Store Manager can record a replacement item for a specific waste (link to the
	// waste record).
	@Test
	public void AECP_SM_TC010() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnWastageMaintenance();
		wastageMaintenance = new StoreManager_WastageMaintenancePage();
		wastageMaintenance.addReplacedWaste();
	}

	// Store Manager can click “Add Invoice” to create a new invoice.
	@Test
	public void AECP_SM_TC011() throws Exception {
		waitImplicit();
		land = new LandingPage();
		land.clickLogin();
		land.loginStore();
		storeDashboard = new StoreManager_DashboardPage();
		storeDashboard.clickOnInvoice();
		invoice = new StoreManager_InvoicePage();
		invoice.addInvoice();
	}

}
