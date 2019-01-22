package commonFunctions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasicFunctions extends TestData {

	public static String status = null;
	//public static Logger log;
	public static long startTime;
	public static long endTime;
	public static long totalTime;
	public static long duration;
	public static String  timeTaken=null;
	public static ExtentReports reports;
	public static ExtentTest test;
	public static final String path = System.getProperty("user.dir") + "\\Reports\\TrimsReport" + dateFunc() + ".html";
	public static Logger log = Logger.getLogger("TRIMS");
	
	public static WebDriver driver;
	
	
	public static final String dateFuncExcelSheet() {
		String pattern = "dd-MMM-yy_HHmm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(new Date());
		return date;
	}
	public static void endReportsExtentRep() throws Exception {
		reports.endTest(test);
		reports.flush();
	}
	public static final String dateFunc() {
		String pattern = "ddMMMyyHHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(new Date());
		return date;
	}
	
	public static void StarttestExtentRep(String testcasename) 	{		
		reports = new ExtentReports(path, false); //step1
		test = reports.startTest(testcasename);
	}
	public static void executionMethod(String testcasename, String sheetname, String className, String methodName) throws Exception {
		System.out.println("Execution Started");
		wbTestData = new XSSFWorkbook(new File(testData));
		testDataSheet = wbTestData.getSheet(sheetname);
		System.out.println("Sheet selected");
		Iterator<Row> testDataIterator = testDataSheet.iterator();
		testDataIterator.next();
		System.out.println("shet has next record");
		while (testDataIterator.hasNext()) {	
			try {
			Row testDataRow = (Row) testDataIterator.next();
			testDataRowNum = testDataRow.getRowNum();
			//System.out.println(testDataRowNum);
			String TC_Name = ExcelUtility.getcellvalue("TC_Name", testDataRowNum, testDataSheet);
			//System.out.println(TC_Name);
			String TC_NameToBeExecuted = ExcelUtility.getcellvalue("RunMode", testDataRowNum, testDataSheet);
			//System.out.println(TC_NameToBeExecuted);
			if(TC_Name.equalsIgnoreCase(testcasename)) {				
				if(TC_NameToBeExecuted.equalsIgnoreCase("Y")){
					System.out.println("testcases name in exection methods " +testcasename);
					TestData.getData(testDataRowNum, testDataSheet);					
					BasicFunctions.StarttestExtentRep(testcasename);					
					Thread.sleep(2000);
					startTime = System.currentTimeMillis();
					Class cls = Class.forName(className);
					Method method = cls.getDeclaredMethod(methodName);
					method.invoke(null);
					//System.out.println("methods are invoked");
					endTime = System.currentTimeMillis();
					totalTime = endTime - startTime;
					duration = TimeUnit.MILLISECONDS.toSeconds(totalTime);
					timeTaken = Long.toString(duration);
					System.out.println("duration taken :" + duration);
					test.log(LogStatus.INFO, "Time taken to complete Processing and patching is " + timeTaken + " seconds");				
					endReportsExtentRep();
					excelutil.count++;
					log.info("===============================================");
				}
			}
		} catch (Exception e) {
			//test.log(LogStatus.FAIL, test.addScreenCapture(sel.takeScreenshotSel(driver)));
			endReportsExtentRep();
			Thread.sleep(2000);
				}
			}
			/*excelutil
			wbTestData.write(excelutil.fos);
			wbTestData.close();
			System.out.println("Execution Failed !");*/
		}
	public static WebDriver openBrowser() throws Exception {
		try {
			String strBrowser= System.getProperty("user.dir");
			Proxy proxy = new Proxy();
			proxy.setProxyAutoconfigUrl("http://maproxy.wlb2.nam.nsroot.net:3878/");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver",strBrowser+"\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
		} catch(Exception e) {
			
			log.error("unable  to launch browser");
			e.printStackTrace();
			closeBrowser();
			throw new Exception(e);
		}
		return null;	
	}
	
	public static void closeBrowser() throws Exception {
		try {
		driver.close();
		driver.quit();
		log.info("Browser closed successfully");
		} catch (Exception e) {
		e.printStackTrace();
		test.log(LogStatus.FAIL, "Unable to close the browser");
		log.error("Unable to close the browser");
		throw new Exception(e);
		}
	}
}
