package com.demo.automation.POMFramework.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public FileOutputStream fileOut = null;
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public ExcelReader(String path){
		this.path = path;
		try{
			fis = new FileInputStream(path);
			 workbook = new XSSFWorkbook(fis);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[][] getDataFromSheet(String sheetName, String ExcelName){
		
		String dataSets[][] = null;
		try{
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRow = sheet.getLastRowNum()+1;
			int totalColumn = sheet.getRow(0).getLastCellNum();
			dataSets = new String[totalRow-1][totalColumn];
			
			for(int i=1; i< totalRow; i++){
				row = sheet.getRow(i);
				for(int j=0; j<totalColumn; j++){
					cell = row.getCell(j);
					
					if(cell.getCellType()==cell.CELL_TYPE_STRING){
						dataSets[i-1][j]=cell.getStringCellValue();
					}
						else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
							dataSets[i-1][j]= String.valueOf(cell.getNumericCellValue());
						}
							else{
								dataSets[i-1][j]= String.valueOf(cell.getBooleanCellValue());
								
							}
						}
				
			}
			
			return dataSets;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return dataSets;
		
		
	}
	
	
	public String getCellData(String sheetName, String colName, int rowNum){
		
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		//Iterator rowItr = sheet.rowIterator();

	   // while ( rowItr.hasNext() ) {
	    //    row = (XSSFRow) rowItr.next();
	    
		if(row== null){
			return"";
		}
		
		//System.out.println(sheet.getFirstRowNum());
				//System.out.println(sheet.getLastRowNum());
				//System.out.println(sheet.getRow(1).getLastCellNum());
				for(int j=0; j<=sheet.getLastRowNum();j++){
				
					if(sheet.getRow(j)!=null){
						for(int i=0;i<=sheet.getRow(j).getLastCellNum();i++){
					//System.out.println(sheet.getRow(j).getCell(i).getStringCellValue());
					if(sheet.getRow(j).getCell(i)!=null)
					{
						
					//System.out.println(sheet.getRow(j).getCell(i).getStringCellValue().trim());
					if(sheet.getRow(j).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim()))
					{
						col_Num=i;
					    break;
					}   
					
					else{
						//System.out.println();
					}
					}
					
					}
						
					
				}
				}
		
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
		//System.out.println(cell.getCellType());
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (DateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(DateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellText;
		           
		           //System.out.println(cellText);

		         }

			  
			  
			  return cellText;
		  }else if(cell != null || cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
	    
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
		
	}
	

}
