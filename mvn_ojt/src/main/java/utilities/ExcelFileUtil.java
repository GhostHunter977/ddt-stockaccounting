package utilities;


import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{
	Workbook wb;
	public ExcelFileUtil(String Excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(Excelpath);
		wb= WorkbookFactory.create(fi);
		
	}
	//Count no of Rows in a excel sheet
	public int rowCount(String sheetName)
	{
		int i=wb.getSheet(sheetName).getLastRowNum();
		return i;
		
	}
	//get cell data
	public String getCellData(String sheetName,int row, int column)
	{
		String data = " ";
		if (wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) 
		{
			int celldata = (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data= String.valueOf(celldata);
		}
		else {
			data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
			
		}
		return data;
		
	}
	//set cell data
	public void setCellData(String sheetName,int row,int column,String status,String writeExcel) throws Throwable
	{
		Sheet ws = wb.getSheet(sheetName);
		Row rowNum = ws.getRow(row);
		Cell cell = rowNum.createCell(column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass"))
		{
			CellStyle style = wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
			
		}
		else if (status.equalsIgnoreCase("fail"))
		{

			CellStyle style = wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font = wb.createFont();
			font.setColor(IndexedColors.DARK_RED.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
			
			
		}
		
		else if (status.equalsIgnoreCase("Blocked"))
		{

			CellStyle style = wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font = wb.createFont();
			font.setColor(IndexedColors.ROYAL_BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
			
			
		}
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
			
		
	}
}

