package commonFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.applicationFunctions.demo.ApplicationsVariables_Common;

//import net.docprep.applicationFunction.ApplicationsVariables_Common;

public class CommonFunctions {
	static Properties configProp = new Properties();
	public static WebDriver IEdriver = null;
	
	public static void readConfig() throws IOException {
		FileInputStream configFilePath = new FileInputStream(
				System.getProperty("user.dir") + "/Configuration/Config.properties");
		configProp.load(configFilePath);
		// ApplicationsVariables.applicationURL = configProp.getProperty("URL");
		ApplicationsVariables_Common.testCasesPath = configProp.getProperty("TestCases");
		System.out.println(ApplicationsVariables_Common.testCasesPath);
		ApplicationsVariables_Common.testDataPath = configProp.getProperty("TestData");
		System.out.println(ApplicationsVariables_Common.testDataPath);
		ApplicationsVariables_Common.refDocsPath = configProp.getProperty("ReferenceDocs");
		ApplicationsVariables_Common.exportFilePath = configProp.getProperty("ExportFilePath");

	}

}
