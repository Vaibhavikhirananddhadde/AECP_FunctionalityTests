package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class PM_TaskReportPage extends BaseClass{
	public PM_TaskReportPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//select[@class='form-control w-auto'])[1]") WebElement dd_year;
	
	@FindBy(xpath="(//select[@class='form-control w-auto'])[2]") WebElement dd_month;
	
	@FindBy(xpath="//button[text()='Generate Report']") WebElement btn_generateReport;
	
	public void generateReport() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Select year = new Select(dd_year);
		year.selectByVisibleText("2025");
		Thread.sleep(3000);
		Select month = new Select(dd_month);
		month.selectByVisibleText("July");
		
		// Store current window handle
		String parentWindow = driver.getWindowHandle();
		String presentWindowURL = driver.getCurrentUrl();
		
		wait.until(ExpectedConditions.visibilityOf(btn_generateReport));
		btn_generateReport.click();
		Thread.sleep(3000);
		
		Set<String> allwindows = driver.getWindowHandles();
		for(String window:allwindows) {
			if(!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
		String newWindowURL = driver.getCurrentUrl();
		Assert.assertNotEquals(newWindowURL,presentWindowURL , "Page is not navigated to new window!");
		
		
		
	}

}
