package AshwinKallingal.SeleniumFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AshwinKallingal.SeleniumFramework.AbstractComponents.AbstarctComponent;

public class PaymentConfirmationPage extends AbstarctComponent{
	
	WebDriver driver;
	
	public PaymentConfirmationPage(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	
	@FindBy(css =".hero-primary")
	WebElement confirmationMessage;
	
	
	public String getPaymentConfirmationMessage() {
		waitForVisibilityOfElement(confirmationMessage);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return confirmationMessage.getText();		
	}
}
