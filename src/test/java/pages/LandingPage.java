package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class LandingPage extends BaseClass{
	

	@FindBy(xpath="//button[text()='Login']")
	WebElement lnk_Login;
	
	@FindBy(name="email")
	WebElement txt_email;
	
	@FindBy(name="password")
	WebElement txt_password;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement btn_Login;
	
	public LandingPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickLogin() {
		lnk_Login.click();
	}
	
	public void loginHR() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		txt_email.clear();
		txt_email.sendKeys(readProperty("HR_username", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		txt_password.clear();
		txt_password.sendKeys(readProperty("HR_password", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		btn_Login.click();
		Thread.sleep(6000);
		//check logged in successfully
		String actual_URL = driver.getCurrentUrl();
		String expected_URL = "https://aecp.aecearth.io/hr-admin/hr-management/dashboard3";
		Assert.assertEquals(actual_URL, expected_URL);
		
	}
	
	public void loginPM() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		txt_email.clear();
		txt_email.sendKeys(readProperty("PM_username", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		txt_password.clear();
		txt_password.sendKeys(readProperty("PM_password", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		btn_Login.click();
		Thread.sleep(3000);
		//check logged in successfully
		String actual_URL = driver.getCurrentUrl();
		String expected_URL = "https://aecp.aecearth.io/project-admin/project-management/dashboard";
		Assert.assertEquals(actual_URL, expected_URL);
		
	}
	
	public void loginGM() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		txt_email.clear();
		txt_email.sendKeys(readProperty("GM_username", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		txt_password.clear();
		txt_password.sendKeys(readProperty("GM_password", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		btn_Login.click();
		Thread.sleep(3000);
		//check logged in successfully
		String actual_URL = driver.getCurrentUrl();
		String expected_URL = "https://aecp.aecearth.io/general-admin/general-management/index";
		Assert.assertEquals(actual_URL, expected_URL);
		
	}
	
	public void loginSEng() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		txt_email.clear();
		txt_email.sendKeys(readProperty("SEng_username", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		txt_password.clear();
		txt_password.sendKeys(readProperty("SEng_password", "/src/test/resources/configfiles/config.properties"));
		waitImplicit();
		btn_Login.click();
		Thread.sleep(3000);
		//check logged in successfully
		String actual_URL = driver.getCurrentUrl();
		String expected_URL = "https://aecp.aecearth.io/site-admin/site-management/dashboard2";
		Assert.assertEquals(actual_URL, expected_URL);
		
	}
	
	
	
}
