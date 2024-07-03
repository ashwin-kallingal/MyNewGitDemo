package AshwinKallingal.SeleniumFramework.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import AshwinKallingal.SeleniumFramework.TestComponents.BaseTest;
import AshwinKallingal.SeleniumFramework.pageObjects.CartPage;
import AshwinKallingal.SeleniumFramework.pageObjects.ProductCataloguePage;


public class ErrorValidation extends BaseTest {

	public static WebDriver driver;

	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation() throws Exception {

		lp.loginApplication("ashwin.kallingal@gmail.com", "Manasvi@09");
		String errorMessage = lp.getErrorMessage();
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
		}
	
	
	@Test(groups = {"Error"})
	public void productErrorValidation() throws Exception {	

		String productName = "ZARA COAT 3";
		ProductCataloguePage pc = lp.loginApplication("ashwin.kallingal@gmail.com", "Manasvi@08");
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCartPage();
		boolean flag = cp.verifyProductInCart("ZARA COAT 33");
		Assert.assertFalse(flag);		
	}
}
