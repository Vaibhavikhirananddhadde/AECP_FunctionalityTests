package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class GM_ClientPage extends BaseClass{
	
	@FindBy(css = "input[type='file'][accept*='image'], input[type='file'][name*='profile'], input[type='file']")
	WebElement upload_ProfileImage;
	
	@FindBy(xpath="//button[text()='Add Client']")
	WebElement btn_addClient;
	
	@FindBy(xpath="//input[@placeholder='Client Name']")
	WebElement txt_ClientName;
	
	@FindBy(xpath="//input[@name='client_email']")
	WebElement txt_ClientEmail;
	
	@FindBy(xpath="//input[@name='client_number']")
	WebElement txt_ClientNumber;
	
	@FindBy(xpath="//input[@name='company_name']")
	WebElement txt_companyName;
	
	@FindBy(xpath="//input[@name='address']")
	WebElement txt_Location;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	@FindBy(xpath="//input[@placeholder='Search Client Name']")
	WebElement txt_searchfield;
	
	@FindBy(xpath="//div[@class='row']/div[@class='col-md-3']")
	List<WebElement> clientCards;
	
	@FindBy(xpath="(//i[@class='fas fa-ellipsis-v'])[1]")
	WebElement kebab_ClientCard;   //3 dots
	
	@FindBy(xpath="//div[@class='dropdown-menu show']//button[@role='menuitem'][normalize-space()='Edit']")
	WebElement btn_Edit;
	
	@FindBy(xpath="//button[normalize-space()='Back']")
	WebElement btn_editPageBack;
	
	@FindBy(xpath="(//button[@role='menuitem'][normalize-space()='Delete'])[1]")
	WebElement btn_Delete;
	
	
	
	public GM_ClientPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addClient() throws InterruptedException {
		btn_addClient.click();
		upload_ProfileImage.sendKeys("/Users/admin/downloads/ChatGPT Image Aug 4, 2025, 12_00_24 PM (1).png");
		Thread.sleep(3000);
		String clientname = randomString();
		txt_ClientName.sendKeys(clientname);
		txt_ClientEmail.sendKeys(randomString()+"@example.com");
		txt_ClientNumber.sendKeys(randomNumber(10));
		txt_companyName.sendKeys(randomString());
		txt_Location.sendKeys("Address 123");
		btn_Submit.submit();
		try {
			isAlertPresent();
		}catch(Exception e) {
			
		}
		driver.navigate().refresh();
	
		List<WebElement> ClientsName = driver.findElements(By.xpath("//div[@class='col-md-3']//h3"));
		for(int i=0;i<ClientsName.size();i++) {
			if(ClientsName.get(i).getText().contains(clientname)) {
				Assert.assertEquals(ClientsName.get(i).getText(), clientname+",", "Client has not added");
			}
		}
		
	}
	
	public void searchClient(String clientName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		txt_searchfield.sendKeys(clientName);
		wait.until(ExpectedConditions.visibilityOfAllElements(clientCards));
		for(WebElement clientcard:clientCards) {
			WebElement clientname = clientcard.findElement(By.xpath(".//h3"));
			System.out.println("Search employee is : "+clientname.getText());
			Assert.assertEquals(clientname.getText(), clientName+",", "Client is unable to search!....");
		}
	}
	
	public void editClientDetails(String clientName, String NewName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		txt_searchfield.sendKeys(clientName);
		wait.until(ExpectedConditions.visibilityOfAllElements(clientCards));
		
		
		    kebab_ClientCard.click();
			btn_Edit.click();
			
			//Edit client details
			txt_ClientName.sendKeys(NewName);
			btn_Submit.click();
			btn_editPageBack.click();
			
			//validation
			wait.until(ExpectedConditions.visibilityOf(txt_searchfield));
			txt_searchfield.sendKeys(NewName);
			wait.until(ExpectedConditions.visibilityOfAllElements(clientCards));
			for(WebElement clientcard:clientCards) {
				WebElement clientname = clientcard.findElement(By.xpath(".//h3"));
			    Assert.assertEquals(clientname.getText(), NewName+",","Client details are not edited!..");
		     }
	      
	 }
	
	public void deleteClient() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		btn_addClient.click();
		upload_ProfileImage.sendKeys("/Users/admin/downloads/ChatGPT Image Aug 4, 2025, 12_00_24 PM (1).png");
		String clientname = randomString();
		txt_ClientName.sendKeys(clientname);
		txt_ClientEmail.sendKeys(randomString()+"@example.com");
		txt_ClientNumber.sendKeys(randomNumber(10));
		txt_companyName.sendKeys(randomString());
		txt_Location.sendKeys("Address 123");
		btn_Submit.submit();
		try {
			isAlertPresent();
		}catch(Exception e) {
			
		}
		driver.get("https://aecp.aecearth.io/general-admin/general-management/client1");
		txt_searchfield.sendKeys(clientname);
		wait.until(ExpectedConditions.visibilityOfAllElements(clientCards));
		
		    kebab_ClientCard.click();
		    btn_Delete.click();
		    
		    txt_searchfield.sendKeys(clientname);
		    
		    WebElement dltConfirm = driver.findElement(By.xpath("//button[normalize-space()='Yes, delete "+clientname+"']"));
		    wait.until(ExpectedConditions.visibilityOf(dltConfirm));
		    Assert.assertTrue(dltConfirm.isDisplayed());
		    dltConfirm.click();
		    
	}
	
	public void checkPlaceholderAddClient() {
		btn_addClient.click();
		String placeholder_clientName = txt_ClientName.getAttribute("placeholder");
		System.out.println("Placeholder for 'Client Name' textfield is : "+placeholder_clientName);
		Assert.assertEquals(txt_ClientName.getAttribute("placeholder"), "Client Name", "Client Name textfield does not has proper placeholder..!");
		
		String placeholder_email = txt_ClientEmail.getAttribute("placeholder");
		System.out.println("Placeholder for 'Client Email' textfield is : "+placeholder_email);
		Assert.assertEquals(txt_ClientEmail.getAttribute("placeholder"), "Client Email", "Client Email textfield does not has proper placeholder..!");
		
		String placeholder_number = txt_ClientNumber.getAttribute("placeholder");
		System.out.println("Placeholder for 'Client Phone Number' textfield is : "+placeholder_number);
		Assert.assertEquals(txt_ClientNumber.getAttribute("placeholder"), "Client Number", "Client Number textfield does not has proper placeholder..!");
		
		
		String placeholder_companyName = txt_companyName.getAttribute("placeholder");
		System.out.println("Placeholder for 'Company Name' textfield is : "+placeholder_companyName);
		Assert.assertEquals(txt_companyName.getAttribute("placeholder"), "Company Name", "Company Name textfield does not has proper placeholder..!");
		
		
		String placeholder_location = txt_Location.getAttribute("placeholder");
		System.out.println("Placeholder for 'Location' textfield is : "+placeholder_location);
		Assert.assertEquals(txt_Location.getAttribute("placeholder"), "Location", "Location textfield does not has proper placeholder..!");
	}

}
