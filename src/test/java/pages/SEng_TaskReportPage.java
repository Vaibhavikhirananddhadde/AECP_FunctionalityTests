package pages;

import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;

public class SEng_TaskReportPage extends BaseClass{
	public SEng_TaskReportPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//select[@class='form-control'])[1]") WebElement dd_year;
	@FindBy(xpath="(//select[@class='form-control'])[2]") WebElement dd_month;
	@FindBy(xpath="//span[text()='Generate Report']") WebElement btn_generateReport;
	
	public void generateReport() {
		Select year = new Select(dd_year);
		year.selectByVisibleText("2025");
		
		Select month = new Select(dd_month);
		month.selectByVisibleText("November");
		
		String parentWindow = driver.getWindowHandle();
		
		btn_generateReport.click();
		
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String window : allWindows) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		 System.out.println(driver.getCurrentUrl());
	}

}
