package pages;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class SEng_LabourPage extends BaseClass {
	public SEng_LabourPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Add New Labour']")
	WebElement btn_AddNewLabour;
	@FindBy(xpath = "//select[@id='input-project']")
	WebElement dd_projectName;
	@FindBy(xpath = "//input[@id='input-name']")
	WebElement txt_labourName;
	@FindBy(xpath = "//input[@id='input-number']")
	WebElement txt_labourNumber;
	@FindBy(xpath = "//button[text()='Submit']")
	WebElement btn_Submit;
	@FindBy(xpath = "(//table[@class='align-items-center table-flush table']/tbody/tr/td[3])[last()]")
	WebElement td_newLabourName;
	@FindBy(xpath = "//p[contains(text(),'Labour added successfully!')]")
	WebElement successLabourAddtion;
	@FindBy(xpath = "//button[text()='OK']")
	WebElement btn_OK;

	public void addNewLabour() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Generate random values
		String randomName = "Labour_" + UUID.randomUUID().toString().substring(0, 6);
		Random rand = new Random();
		String randomPhone = "9" + (100000000 + rand.nextInt(900000000));

		btn_AddNewLabour.click();
		Select projects = new Select(dd_projectName);
		projects.selectByVisibleText("Test Project");
		txt_labourName.sendKeys(randomName);
		txt_labourNumber.sendKeys(randomPhone);
		btn_Submit.click();
		wait.until(ExpectedConditions.visibilityOf(successLabourAddtion));
		Assert.assertTrue(successLabourAddtion.isDisplayed(), "Success message is not displayed!.");
		btn_OK.click();
		driver.get("https://aecp.aecearth.io/site-admin/site-management/Labour");

		Assert.assertEquals(td_newLabourName.getText().trim(), randomName,
				"New labour has not got added successfully.");
		System.out.println("Newly added labour name is: " + td_newLabourName.getText().trim());
	}

}
