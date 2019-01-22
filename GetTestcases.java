package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

//import com.applicationFunctions.demo.ApplicationsVariables_Common;

//import net.docprep.applicationFunction.ApplicationsVariables_Common;

public class GetTestcases {
	//public static XSSFWorkbook workBook;
	//public static XSSFSheet workSheet;
	public static String configSheet = "DataFiles\\TestCases.xlsx";
	public static String testData = "DataFiles\\TestData.xlsx";
	
	public static String regionToBeExecuted = null;
	public static String data = null;
	public static String value = null;
	public static int count = 1;
	public static int testDataRowNum;
	public static XSSFSheet regionSheet = null;
	public static XSSFSheet regionExecutionSheet = null;
	public static XSSFSheet testDataSheet = null;
	public static XSSFSheet Scenariosheet = null;
	public static XSSFWorkbook wbConfig = null;
	public static XSSFWorkbook wbTestData = null;
	public static FileInputStream fis;
	
	public static String regionValue = null;

	public static String methodToBeExecuted = null;
	
	public static ExcelUtility excelutil = new ExcelUtility();
	static Properties configProp = new Properties();
	public static String TestSuitePath;
	public static int urlFlag = 0;
	//public static String fileName;
	public static ArrayList<String> methodsList = new ArrayList<>();
	public static ArrayList<String> classList = new ArrayList<>();
	//public static String methodToBeExecuted = null;
	public static XmlSuite suite = new XmlSuite();
	public static XmlTest xmlTest;
	public static XmlClass testClassAs = new XmlClass ();
	public static XmlClass testClassAsia = new XmlClass ();
	public static XmlClass testClass = new XmlClass ();
	public static List<XmlInclude> methodsToRunAs = new ArrayList<XmlInclude> ();
	public static List<XmlInclude> methodsToRunAsia = new ArrayList<XmlInclude> ();
	public static List<XmlInclude> methodsToRun = new ArrayList<XmlInclude> ();
	
	/*public static void main(String[] args) throws Exception, Exception {
		//testCasesToRun();
		getCases();
	}*/
	
	public static  XmlSuite testCasesToRun() throws IOException, Exception {
		/*FileInputStream configFilePath = new FileInputStream(
				System.getProperty("user.dir") + "/Configuration/Config.properties");
		configProp.load(configFilePath);
		// ApplicationsVariables.applicationURL = configProp.getProperty("URL");
		ApplicationsVariables_Common.testCasesPath = configProp.getProperty("TestCases");
		System.out.println(ApplicationsVariables_Common.testCasesPath);
		ApplicationsVariables_Common.testDataPath = configProp.getProperty("TestData");
		System.out.println(ApplicationsVariables_Common.testDataPath);
		ApplicationsVariables_Common.refDocsPath = configProp.getProperty("ReferenceDocs");
		ApplicationsVariables_Common.exportFilePath = configProp.getProperty("ExportFilePath");
		
		FileInputStream tc_fis = new FileInputStream(new File(ApplicationsVariables_Common.testCasesPath));
		FileInputStream td_fis = new FileInputStream(new File(ApplicationsVariables_Common.testDataPath));*/
		wbConfig = new XSSFWorkbook(new File(configSheet));
		wbTestData = new XSSFWorkbook(new File(testData));
		Scenariosheet = wbConfig.getSheet("Config");
		
		Iterator<Row> rowIterator = Scenariosheet.iterator();
		rowIterator.next();	
		
		generateTestNGSuite();
		while (rowIterator.hasNext()) {
			Row row = (Row) rowIterator.next();
			int rowNum= row.getRowNum();
			String ScenarioToBeExecute = excelutil.getcellvalue("RunMode", rowNum, Scenariosheet);
			String ScenarioName = excelutil.getcellvalue("ScenarioName", rowNum, Scenariosheet);
			String ModuleName = excelutil.getcellvalue("ModuleName", rowNum, Scenariosheet);
			//System.out.println("Module Name - " + ModuleName + ", Scenario to run - " + ScenarioName +", To be executed? " + ScenarioToBeExecute);
			if (ScenarioToBeExecute.equalsIgnoreCase("Y")) {
				testDataSheet = wbTestData.getSheet(ModuleName);
				System.out.println("testDataSheet " + ModuleName );
				Iterator<Row> TCIterator =  testDataSheet.iterator();
				TCIterator.next();
				while (TCIterator.hasNext()) {
					Row TCRow = (Row) TCIterator.next();
					testDataRowNum = TCRow.getRowNum();
					String TS_Name= excelutil.getcellvalue("scenario", testDataRowNum, testDataSheet);
					//System.out.println("TS from TestDataSheet "+TS_Name );
					String TC_NameToBeExecuted = excelutil.getcellvalue("RunMode", testDataRowNum, testDataSheet);
					//System.out.println("Run mode in testDatasheet " + TC_NameToBeExecuted);
					if (TS_Name.equalsIgnoreCase(ScenarioName)) {
						if(TC_NameToBeExecuted.equalsIgnoreCase("Y")) {						
							String TC_Name= excelutil.getcellvalue("TC_Name", testDataRowNum, testDataSheet);
							//System.out.println(TC_Name);
							methodToBeExecuted = TC_Name;
							//System.out.println(methodToBeExecuted);
							//System.out.println("Test case name - " + TC_Name + ", To be executed? " + TC_NameToBeExecuted);
							methodsList.add(methodToBeExecuted);
							}
						}
					}
				}
			testDataSheet = wbTestData.getSheet(ModuleName);
		}
		wbConfig.close();		
		testClassAs.setName("commonFunctions.AfterSuiteTest");
		methodsToRunAs.add(new XmlInclude("afterSuite"));
		testClassAs.setIncludedMethods(methodsToRunAs);
		for (String tc : methodsList) {
			System.out.println(tc);			
				methodsToRun.add(new XmlInclude(tc));
				//System.out.println("TC Selected for non ASIA");
				testClass.setName("regions.Asia");
				testClass.setIncludedMethods(methodsToRun);
				boolean flag = methodsToRun.isEmpty();
				if(flag ==false) {
					xmlTest.setXmlClasses(Arrays.asList(new XmlClass[] {testClass,testClassAs} ));				
			}
		}	
		return suite;
	}
	
	@SuppressWarnings("deprecation")
	public static void generateTestNGSuite() {
		suite.setName("TRIMS REGRESSION");
		suite.setThreadCount(10);
		
		xmlTest = new XmlTest(suite);
		xmlTest.setName("Regression");
		xmlTest.setPreserveOrder("true");
		
	}
	
	public static void writeTestngXml() throws IOException {
		File file = new File("testng01.xml");
		System.out.println("file " + file);
		
		FileWriter writer = new FileWriter(file);
		writer.write(suite.toXml());
		writer.close();
	}
	
	@Test
	public static void getCases() throws Exception {
		System.out.println("get cases");
		testCasesToRun();
		writeTestngXml();
	}

}
