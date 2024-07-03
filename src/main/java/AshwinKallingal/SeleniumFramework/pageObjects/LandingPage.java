package AshwinKallingal.SeleniumFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AshwinKallingal.SeleniumFramework.AbstractComponents.AbstarctComponent;

public class LandingPage extends AbstarctComponent{
	
	WebDriver driver;
		
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(xpath="//div[@id='toast-container']//div[@role='alert']")
	WebElement errorMessage;	
	
	public ProductCataloguePage loginApplication(String userName, String passwOrd) {		
		username.sendKeys(userName);
		password.sendKeys(passwOrd);
		login.click();
		return new ProductCataloguePage(driver);
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForVisibilityOfElement(errorMessage);
		return errorMessage.getText();
	}	
}