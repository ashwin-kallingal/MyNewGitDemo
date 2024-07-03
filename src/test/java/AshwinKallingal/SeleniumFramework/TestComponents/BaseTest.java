package AshwinKallingal.SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aventstack.extentreports.ExtentReports;

import AshwinKallingal.SeleniumFramework.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	public ExtentReports extent;

	public WebDriver initializeDriver() throws InterruptedException, IOException {	
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//AshwinKallingal//SeleniumFramework//Resources//Global.properties");
		prop.load(fis);
		String browserName = prop.getProperty("Browser");
		
		
		browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		System.out.println("Browser Name is:"+browserName);
				
				
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			if(browserName.contains("headless")) {
			  options.addArguments("--headless=new");
			}
			
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440, 990));
		}else if(browserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(2000);	
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws InterruptedException, IOException  {		
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}	
	
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJSONData(String filePath) throws IOException {
		
		String jSONContent = FileUtils.readFileToString(new File(filePath)
				,StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jSONContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
		
	}
	
	public String getScreenshotFilePath(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
	    FileUtils.copyFile(srcFile, destFile);
	    return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";		
	}	
}
	

