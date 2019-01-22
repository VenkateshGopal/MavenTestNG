package commonFunctions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mongodb.diagnostics.logging.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestNgMethods extends CommonMethods {
	
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static String testResultFolderName = null;
	public static String testResultFolderNameDownload = null;
	
/*	//@BeforeTest
	@BeforeClass
	public static void setUp() throws IOException {
		testResultFolderName = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
        testResultFolderNameDownload = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        //extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
        extent = new ExtentReports ("Test Reports\\"+testResultFolderName+
        		"\\"+"DocPrep Automation Report - "+new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date())+".html");
        extent
        .addSystemInfo("Host Name", "SoftwareTestingMaterial")
        .addSystemInfo("Environment", "Automation Testing")
        .addSystemInfo("User Name", "Rajkumar SM");
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println("Report  created --");
        //CommonFunctions.readConfig();
       // Test_Cases.testSuiteRead();
        
	}*/
	
	/*@Test
	public static void getCases() throws Exception {
		System.out.println("get cases");
		testSuiteRead();
		//writeTestngXml();
	}*/
	
	/*@Test
	public  void method1() {
		System.out.println("Method1 executed !!");
		logger= extent.startTest("method1");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Cases Passed");
	}
	  @Test
	public  void method2() {
		System.out.println("Method2 executed !!");
		logger= extent.startTest("method2");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Cases Passed");
	}

	@AfterMethod
	public void getResults(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is"+ result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is"+ result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Failed is"+ result.getName());
		}
		extent.endTest(logger);
	}*/
	
	/*//@AfterTest
	//@BeforeClass
	public static void teardown() {
		extent.flush();
		extent.close();
	}*/
	
	/*public static void main(String[] args) throws Exception {
		leanFT();
		
		
	}*/
	
	@BeforeSuite
	public static void leanFT() throws Exception {
	/*	ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
		config.setServerAddress(new URI("ws://localhost:5095"));
		SDK.init(config);
		waitTime(3);*/
		System.out.println("Create Excel Results");
		ExcelUtility.createXssfExcel();
		ExcelUtility.cellStyleColorGreen();
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\PropertyFiles\\log4j.properties");
	}

	@AfterMethod
	public void executionStatus(ITestResult result){
		System.out.println("After method execution");
		try {
			if(result.getStatus() == ITestResult.SUCCESS){
				System.out.println("passed **********");
				status = "PASS";
			}
			else if(result.getStatus() == ITestResult.FAILURE){
				System.out.println("Failed ***********");
				status = "FAIL";
			}
			/*else if(result.getStatus() == ITestResult.SKIP ){
				System.out.println("Skiped***********");
			}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
