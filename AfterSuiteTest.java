package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AfterSuiteTest extends CommonMethods{
	//public static WebDriver driver;
	public static ExtentTest logger;
	
	/*public static void main(String[] args) throws Exception {
		afterSuite();
	}*/
	
	@AfterSuite
	public static void afterSuite() throws Exception {
		System.out.println("browser Closed");
		driver.close();
		driver.quit();
		/*if(driver.toString().contains("null")) {
			//log.info("Browser closed");
			logger.log(LogStatus.INFO, "Browser closed");
		}
		else {
			logger.log(LogStatus.INFO, "Browser not closed");
		}*/
	}
}
