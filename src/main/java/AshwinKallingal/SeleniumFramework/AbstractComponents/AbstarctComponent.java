package AshwinKallingal.SeleniumFramework.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AshwinKallingal.SeleniumFramework.pageObjects.CartPage;
import AshwinKallingal.SeleniumFramework.pageObjects.OrderPage;

public class AbstarctComponent {
	
	WebDriver driver;
	
	public AbstarctComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}	
	
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartMenuTab;	
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement ordersMenuTab;
	
	public void waitForVisibilityOfElement(WebElement element) {		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));		
	}
	
	public void waitForVisibilityOfElementLocated(By by) {		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));	
	}
	
	public void waitForInvisibilityOfElementLocated(By by) {		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));	
	}	
	
	public CartPage goToCartPage() {
		cartMenuTab.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage() {
		ordersMenuTab.click();
		return new OrderPage(driver);
	}

}
