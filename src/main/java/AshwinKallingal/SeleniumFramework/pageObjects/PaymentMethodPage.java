package AshwinKallingal.SeleniumFramework.pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AshwinKallingal.SeleniumFramework.AbstractComponents.AbstarctComponent;

public class PaymentMethodPage extends AbstarctComponent{
	
	WebDriver driver;
	
	public PaymentMethodPage(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath ="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submitButton;	
	
	@FindBy(css ="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath ="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectIndia;	
	
	public void selectIndiaAsCountry(String countryName) throws InterruptedException {
		waitForVisibilityOfElement(country);	
		Actions action = new Actions(driver);
		action.moveToElement(country).click();
		country.sendKeys(countryName);
		waitForVisibilityOfElement(selectIndia);	
		selectIndia.click();
	}	
	
	public PaymentConfirmationPage clickSubmitButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,900)");
		waitForVisibilityOfElement(submitButton);		
		try {
			submitButton.click();
		}catch(NoSuchElementException e) {
			Actions action = new Actions(driver);
		action.moveToElement(submitButton).build().perform();
		}catch(Exception e) {
			driver.findElement(By.xpath("//a[text()='Place Order '] ")).click();			
		}
		
		return new PaymentConfirmationPage(driver);
	}
}