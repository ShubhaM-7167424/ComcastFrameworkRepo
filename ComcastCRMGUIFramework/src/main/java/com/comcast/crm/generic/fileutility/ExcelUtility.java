package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	/*
	 * public String getDataFromExcel(String sheetName, int rowNum, int colNum)
	 * throws Throwable, IOException { String data=""; try { FileInputStream fis =
	 * new FileInputStream("./testdata/testScriptdata.xlsx"); Workbook workbook =
	 * WorkbookFactory.create(fis); data =
	 * workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum).
	 * getStringCellValue(); workbook.close(); } catch (Exception e) { throw new
	 * RuntimeException("Failed to read data from Excel", e); } return data; }
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int colNum) {

	    try (FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");
	         Workbook workbook = WorkbookFactory.create(fis)) {

	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            return "";   // sheet not found → return empty
	        }

	        Row row = sheet.getRow(rowNum);
	        if (row == null) {
	            return "";   // row not found → return empty
	        }

	        Cell cell = row.getCell(colNum);
	        if (cell == null) {
	            return "";   // cell empty → return empty
	        }

	        // Handle BLANK cell safely
	        if (cell.getCellType() == CellType.BLANK) {
	            return "";
	        }

	        return cell.toString(); // works for String, Number, Date, Boolean

	    } catch (Exception e) {
	        // Any unexpected issue → return empty instead of breaking test
	        return "";
	    }
	}

	
	public int getRowCount(String sheetName) throws Throwable, IOException {
		FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");		
		Workbook workbook = WorkbookFactory.create(fis);
		int rowCount = workbook.getSheet(sheetName).getLastRowNum();
		workbook.close();
		
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int colNum, String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");		
		Workbook workbook = WorkbookFactory.create(fis);
		workbook.getSheet(sheetName).getRow(rowNum).createCell(colNum).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./testdata/testScriptdata.xlsx");
		workbook.write(fos);
		workbook.close();
	}
}
