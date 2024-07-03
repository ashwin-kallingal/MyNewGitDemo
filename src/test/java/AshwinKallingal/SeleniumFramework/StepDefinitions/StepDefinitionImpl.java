package AshwinKallingal.SeleniumFramework.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import AshwinKallingal.SeleniumFramework.TestComponents.BaseTest;
import AshwinKallingal.SeleniumFramework.pageObjects.CartPage;
import AshwinKallingal.SeleniumFramework.pageObjects.LandingPage;
import AshwinKallingal.SeleniumFramework.pageObjects.PaymentConfirmationPage;
import AshwinKallingal.SeleniumFramework.pageObjects.PaymentMethodPage;
import AshwinKallingal.SeleniumFramework.pageObjects.ProductCataloguePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage lp;
	public ProductCataloguePage pc;
	public CartPage cp ;
	public PaymentMethodPage pp;
	public PaymentConfirmationPage pcp;
	
	SoftAssert assertion = new SoftAssert();
	
	@Given ("User lands on Ecommerce page")
	public void user_lands_on_Ecommerce_page() throws InterruptedException, IOException {
		lp = launchApplication();		
	}
	
	@Given ("^Logged in to Ecommerce site with username (.+) and password (.+)$")
	public void logged_in_to_Ecommerce_site_with_username_and_password(String username, String password) {
		pc = lp.loginApplication(username, password );
	}
	
	@When ("^Add product (.+) to cart$")
	public void add_product_to_cart(String productName) {
		pc.addProductToCart(productName);
	}
		
		
	@And ("^Checkout the (.+) and submit the order$")
	public void checkout_the_and_submit_the_order(String productName) throws InterruptedException {
		cp = pc.goToCartPage();
		boolean prodPresent = cp.verifyProductInCart(productName);	
		assertion.assertTrue(prodPresent);
		pp = cp.clickCheckoutButton();		
		pp.selectIndiaAsCountry("india");
		pcp = pp.clickSubmitButton();		
	}
	
	@Then ("The confirmation message {string} is displayed on Confirmation page")
	public void the_confirmation_message_is_displayed_on_Confirmation_page(String message) {
		System.out.println("The Confirmation message is:"+message);
		System.out.println("The Expected Confirmation message is"+pcp.getPaymentConfirmationMessage());
		assertion.assertTrue(pcp.getPaymentConfirmationMessage().equalsIgnoreCase(message));		
		assertion.assertAll();	
		driver.close();
	}	
	
	@Then("Verify the Error Validation Message {string} on Login page")
    public void verify_the_error_validation_message_on_login_page(String expectedErrorMessage) {
		String errorMessage = lp.getErrorMessage();
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, expectedErrorMessage);
		driver.close();
	}
}
