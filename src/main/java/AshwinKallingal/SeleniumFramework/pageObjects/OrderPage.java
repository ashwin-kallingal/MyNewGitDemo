package AshwinKallingal.SeleniumFramework.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AshwinKallingal.SeleniumFramework.AbstractComponents.AbstarctComponent;

public class OrderPage extends AbstarctComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css ="tr td:nth-child(3)")
	List<WebElement> orderList;	
		
	public Boolean verifyOrderListInCart(String productName) {
		Boolean orderPresent = orderList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return orderPresent;		
	}	
}