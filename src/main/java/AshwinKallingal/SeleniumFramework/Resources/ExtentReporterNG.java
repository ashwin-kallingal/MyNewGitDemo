package AshwinKallingal.SeleniumFramework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {	
	
	public static ExtentReports getExtentReportsObject() {
		 ExtentReports extent;
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Autoamtion Test Report");
		reporter.config().setDocumentTitle("Automation Extent Report");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ashwin");
		//extent.createTest(path);
		return extent;
		
	}
}
