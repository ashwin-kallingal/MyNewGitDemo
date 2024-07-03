package AshwinKallingal.SeleniumFramework.TestCases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import AshwinKallingal.SeleniumFramework.TestComponents.BaseTest;
import AshwinKallingal.SeleniumFramework.TestComponents.Retry;
import AshwinKallingal.SeleniumFramework.pageObjects.CartPage;
import AshwinKallingal.SeleniumFramework.pageObjects.OrderPage;
import AshwinKallingal.SeleniumFramework.pageObjects.PaymentConfirmationPage;
import AshwinKallingal.SeleniumFramework.pageObjects.PaymentMethodPage;
import AshwinKallingal.SeleniumFramework.pageObjects.ProductCataloguePage;


//import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest{
	
	//public String productName = "ZARA COAT 3";
	
	@Test(/*retryAnalyzer = Retry.class*/)
	public void Demo1() throws InterruptedException {
		
		ProductCataloguePage pc = lp.loginApplication("ashwin.kallingal@gmail.com", "Manasvi@08");
		Thread.sleep(2000);
		Assert.assertTrue(true);
	
	}
	
	/*@Test
	public void Demo2() {
		
		ProductCataloguePage pc = lp.loginApplication("ashwin.kallingal@gmail.com", "Manasvi@08");
		Assert.assertTrue(true);
	
	}*/
	
	
	
	

	@Test(dataProvider = "getData")
	public void submitOrder(Map<String, String> input) throws Exception {

		// WebDriverManager.chromedriver().setup();
		// WebDriver driver = new ChromeDriver();
		SoftAssert assertion = new SoftAssert();
		
		ProductCataloguePage pc = lp.loginApplication(input.get("email"), input.get("password"));
		pc.addProductToCart(input.get("product"));
		CartPage cp = pc.goToCartPage();
		boolean flag = cp.verifyProductInCart(input.get("product"));
		assertion.assertTrue(flag);
		PaymentMethodPage pp = cp.clickCheckoutButton();		
		pp.selectIndiaAsCountry("india");
		PaymentConfirmationPage pcp = pp.clickSubmitButton();
		String file = getScreenshotFilePath("Test", driver);
		assertion.assertTrue(pcp.getPaymentConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
		System.out.println(file+"");
		assertion.assertAll();		
	}	
	
	/*@Test(dependsOnMethods={"submitOrder"})
	public void verifyOrder() throws Exception {
		SoftAssert assertion = new SoftAssert();
	    lp.loginApplication("ashwin.kumar.k@gmail.com", "Manasvi@08");
		OrderPage op  = lp.goToOrderPage();
		boolean flag = op.verifyOrderListInCart("ZARA COAT 4");
		assertion.assertTrue(flag);	
		assertion.assertAll();
	}*/
	
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "ZARA COAT 3" }, { "IPHONE 13 PRO" }};
	}*/
	
	
	/*@DataProvider
	public Object[][] getData() {
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "ashwin.kallingal@gmail.com");
		map1.put("password", "Manasvi@08");
		map1.put("product", "ZARA COAT 3");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "ashwin.kumar.k@gmail.com");
		map2.put("password", "Manasvi@08");
		map2.put("product", "IPHONE 13 PRO");
		
		return new Object[][] { { map1 }, { map2 }};
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJSONData(System.getProperty("user.dir")+"//src//test//java//AshwinKallingal//SeleniumFramework//Data//data.json");
		return new Object[][] { { data.get(0) }/*, { data.get(1) }*/};
	}
	
	
	
	
}

