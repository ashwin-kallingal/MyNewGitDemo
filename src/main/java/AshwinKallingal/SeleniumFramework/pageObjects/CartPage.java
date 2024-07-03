package AshwinKallingal.SeleniumFramework.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AshwinKallingal.SeleniumFramework.AbstractComponents.AbstarctComponent;

public class CartPage extends AbstarctComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".cartSection h3")
	List<WebElement> cartLists;
	
	@FindBy(css =".totalRow button")
	WebElement checkoutButton;	
	
	public Boolean verifyProductInCart(String productName) {
		List<WebElement> cartList = cartLists;
		Boolean itemPresent = cartList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return itemPresent;		
	}
	
	public PaymentMethodPage clickCheckoutButton() {	
		checkoutButton.click();
		return new PaymentMethodPage(driver);
	}
}