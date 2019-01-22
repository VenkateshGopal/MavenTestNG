package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import com.cucumber.listener.Reporter;
//import net.docprep.applicationFunction.ApplicationsVariables_Common;
import org.testng.Reporter;

import com.applicationFunctions.demo.ApplicationsVariables_Common;

public class TestData {

	public static String configSheet = "DataFiles\\TestCases.xlsx";
	public static String testData = "DataFiles\\TestData.xlsx";
	public static String testDataOutput = "TestData\\Test_Data_Output.xlsx";
	
	public static String TC_NameToBeExecuted = null;
	public static XSSFSheet testDataSheet = null;
	public static XSSFWorkbook wbConfig = null;
	public static XSSFWorkbook wbTestData = null;
	public static FileInputStream fis;
	public static int testDataRowNum;
		
	public static String methodName = null;
	
	public static String Test_Scenario = null;
	public static String Business_Flow = null;
	public static String TC_Name = null;
	public static String TD_No = null;
	public static String ToBeExecuted = null;
	public static String RegistrationFlag = null;
	public static String Maker = null;
	
	public static ExcelUtility excelutil = new ExcelUtility();
	
	public static void getData(int rownum, XSSFSheet sheet) throws Exception {
		try {
			TC_Name = excelutil.getcellvalue("TC_Name", rownum, sheet);
			//TD_No = eu.getcellvalue("TD_No", rownum, sheet);
			ToBeExecuted = excelutil.getcellvalue("ToBeExecuted", rownum, sheet);
			RegistrationFlag = excelutil.getcellvalue("RegistrationFlag", rownum, sheet);
			Maker = excelutil.getcellvalue("Maker", rownum, sheet);
		} catch (Exception e) {
			BasicFunctions.endReportsExtentRep();
		}
		
	}
	
	// Help Getting Data from TestData
	/*public static String readData(String columnName) throws IOException {
		String sendValues = null;
		FileInputStream inputStream = new FileInputStream(new File(ApplicationsVariables_Common.testDataPath));
		XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
		XSSFSheet workSheet = workBook.getSheet(ApplicationsVariables_Common.moduleName);
		for (int rowIterator = 0; rowIterator <= workSheet.getLastRowNum(); rowIterator++) {
			
			if ((workSheet.getRow(rowIterator).getCell(1).toString())
					.equalsIgnoreCase(ApplicationsVariables_Common.scenarioName)) {
				int colIndex = workSheet.getRow(0).getLastCellNum() - 1;
				for (int columnIterator = 0; columnIterator <= colIndex; columnIterator++) {
					
					if (workSheet.getRow(0).getCell(columnIterator).toString().equalsIgnoreCase(columnName)) {
						sendValues = workSheet.getRow(rowIterator).getCell(columnIterator).getStringCellValue()
								.toString();
					if(!columnName.equalsIgnoreCase("runmode")) 
					{	Reporter.setTestRunnerOutput(
								"<br>" + ApplicationsVariables_Common.scenarioName + ApplicationsVariables_Common.reportSpace10
										+ "ReadTestData" + ApplicationsVariables_Common.reportSpace10 + columnName
										+ ApplicationsVariables_Common.reportSpace17 + "\'" + sendValues + "\'");
					}
						return sendValues;

					}
				}
			}
		}
		return sendValues;

	}

	// Help Writing Data from TestData
	public static void writeData(String columnName, String value) throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(ApplicationsVariables_Common.testDataPath));
		XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
		XSSFSheet workSheet = workBook.getSheet(ApplicationsVariables_Common.moduleName);
		for (int rowIterator = 0; rowIterator <= workSheet.getLastRowNum(); rowIterator++) {
			if ((workSheet.getRow(rowIterator).getCell(1).toString())
					.equalsIgnoreCase(ApplicationsVariables_Common.scenarioName)) {
				int colIndex = workSheet.getRow(0).getLastCellNum() - 1;
				for (int columnIterator = 0; columnIterator < colIndex; columnIterator++) {
					if (workSheet.getRow(0).getCell(columnIterator).toString().equalsIgnoreCase(columnName)) {
						workSheet.getRow(rowIterator).createCell(columnIterator).setCellValue(value);
						FileOutputStream outStream = new FileOutputStream(
								new File(ApplicationsVariables_Common.testDataPath));
						workBook.write(outStream);
						outStream.close();
						inputStream.close();
						Reporter.setTestRunnerOutput(
								"<br>" + ApplicationsVariables_Common.scenarioName + ApplicationsVariables_Common.reportSpace10
										+ "WriteTestData" + ApplicationsVariables_Common.reportSpace10 + columnName
										+ ApplicationsVariables_Common.reportSpace17 + "\'" + value + "\'");

					}
				}
			}
		}

	}*/
}
