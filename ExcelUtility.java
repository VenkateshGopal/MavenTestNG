package commonFunctions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static XSSFSheet sheet = null;
	public static XSSFWorkbook wbexeStatus = null;
	public static FileOutputStream fos;
	public static FileOutputStream execStatusfos;
	public static Row rowStatusSheet;
	public static Cell cell;
	public static CellStyle style;
	public static CellStyle styleGreen;
	public static CellStyle styleRed;
	public static CellStyle styleWhite;
	static XSSFFont defaultFont;
	static XSSFFont font;
	
	public static final String executionSheet = "ExcelReports\\ExecutionStatus_" + BasicFunctions.dateFuncExcelSheet() + ".xlsx";
	public static final String urlSheet = "TestData\\Links.xlsx";

	//static TestSuiteDriver tsd = new TestSuiteDriver();


	/*public static String configSheet = "TestData\\NewConfig.xlsx";
	public static String testData = "TestData\\Test_Data.xlsx";
	public static String testDataOutput = "TestData\\Test_Data_Output.xlsx";*/

	public static String TC_Name = null;
	public static String TC_NameToBeExecuted = null;
	
	public static String data = null;
	public static String urlValue = null;
	public static int count = 1;
	public static int testDataRowNum;
	public static XSSFSheet regionSheet = null;
	public static XSSFSheet regionExecutionSheet = null;
	public static XSSFSheet testDataSheet = null;
	public static XSSFWorkbook wbConfig = null;
	public static XSSFWorkbook wbTestData = null;
	public static FileInputStream fis;
	
	//public static ConfigProp con = new ConfigProp();
	
	@SuppressWarnings("unused")
	public static String getcellvalue(String columnname, int rownum, XSSFSheet sheet) throws Exception {
		try {
		DataFormatter formatter = new DataFormatter();
		Row row = sheet.getRow(0);
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell CurrentCell =  cellIterator.next();
		if(CurrentCell.getStringCellValue().equalsIgnoreCase(columnname)) {
			int columnnum = CurrentCell.getColumnIndex();
			
			if(CurrentCell == null) {
				CurrentCell = sheet.getRow(rownum).getCell(columnnum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				return formatter.formatCellValue(CurrentCell);
			}
			else {
				return (sheet.getRow(rownum).getCell(columnnum).getStringCellValue());
				}
			}
		}
	} catch(Exception e) {
		e.printStackTrace();
		BasicFunctions.endReportsExtentRep();
	}
		return columnname+" - Column Not Found";
	}
	
	public static void createXssfExcel() throws IOException, InterruptedException {
		wbexeStatus = new XSSFWorkbook();
		sheet = wbexeStatus.createSheet("Execution status");
		cellStyleColorGrey();
		cellStyleColorGreen();
		cellStyleColorRed();
		cellStyleColorWhite();
		rowStatusSheet = sheet.createRow(0);
		cell = rowStatusSheet.createCell(0);
		cell.setCellValue("Environment");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(1);
		cell.setCellValue("Version");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(2);
		cell.setCellValue("Product Type");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(3);
		cell.setCellValue("Sub Product Type");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(4);
		cell.setCellValue("Operation type");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(5);
		cell.setCellValue("Login Status");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(6);
		cell.setCellValue("Registration");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(7);
		cell.setCellValue("Processing");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(8);
		cell.setCellValue("Inspection");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(9);
		cell.setCellValue("Country/Legal Vehicle");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(9);
		cell.setCellValue("Charges Y/N");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(10);
		cell.setCellValue("Our Ref Num");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(11);
		cell.setCellValue("Trans Ref Num");
		cell.setCellStyle(style);
		cell = rowStatusSheet.createCell(12);
		cell.setCellValue("Comments");
		cell.setCellStyle(style);
		for(int i = 1; i <= 200; i++) {
			rowStatusSheet = sheet.createRow(i);
			for(int j = 0; j <=20; j++) {
				cell = rowStatusSheet.createCell(j);

				//sheet.autoSizeColumn(j);
			}
		}

		fos = new FileOutputStream(executionSheet);
		wbexeStatus.write(fos);
		fos.close();
		Thread.sleep(3000);
	}
	
	public static CellStyle cellStyleColorGrey() {
		style = wbexeStatus.createCellStyle();		

		defaultFont= wbexeStatus.createFont();
		defaultFont.setFontHeightInPoints((short)10);
		defaultFont.setFontName("Arial");
		defaultFont.setColor(IndexedColors.BLACK.getIndex());
		defaultFont.setBold(false);
		defaultFont.setItalic(false);

		font= wbexeStatus.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("Arial");
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBold(true);
		font.setItalic(false);

		style.setFillForegroundColor(IndexedColors.BLACK.index);	    
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);
		return style;
	}

	public static CellStyle cellStyleColorGreen() {
		styleGreen = wbexeStatus.createCellStyle();
		styleGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
		styleGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return styleGreen;
	}

	public static CellStyle cellStyleColorRed() {
		styleRed = wbexeStatus.createCellStyle();
		styleRed.setFillForegroundColor(IndexedColors.RED.index);
		styleRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return styleRed;
	}

	public static CellStyle cellStyleColorWhite() {
		styleRed = wbexeStatus.createCellStyle();
		styleRed.setFillForegroundColor(IndexedColors.WHITE.index);
		styleRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return styleWhite;
	}

}
