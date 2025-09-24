package utitlities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Factory.logger;


public class DataWriter {
//	private static final Logger logger=LogManager.getLogger(DataWriter.class);
	
	public static void writeData(String browser, List<CarInfo> data) throws IOException
	{
		String filename="C:\\Users\\2403586\\Desktop\\Seleniumwebdriver\\MainProject\\test-ouput-excel\\TestOutput_Excel.xlsx";
		String sheetName=browser.toLowerCase().equals("chrome") ? "Chrome Wash Result" : "Edge Wash Result";
        logger.info("Writing results to Excel: Sheet = " + sheetName);

		
		try(Workbook workbook=getOrCreateWorkbook(filename))
		{
			Sheet sheet=workbook.getSheet(sheetName);
			if(sheet != null)
			{
				int sheetIndex=workbook.getSheetIndex(sheetName);
				workbook.removeSheetAt(sheetIndex);
                logger.info("Old sheet removed: " + sheetName);
			}
			
			sheet=workbook.createSheet(sheetName);
			Row headerRow=sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Name");
			headerRow.createCell(1).setCellValue("Phone");
			
			int rowCount=1;
			for(CarInfo info:data)
			{
				Row row=sheet.createRow(rowCount++);
				row.createCell(0).setCellValue(info.getName());
				row.createCell(1).setCellValue(info.getPhone());
                logger.debug("Written: " + info.getName() + ", " + info.getPhone());

			}
			try {
				FileOutputStream fos=new FileOutputStream(filename);
				workbook.write(fos);
                logger.info("Excel file saved: " + filename);
            }

         catch (Exception e) {
            logger.error("Error while writing Excel file");
			}
		}
		
	}
	
	private static Workbook getOrCreateWorkbook(String filename)
	{
		try
		{
			File file=new File(filename);
			if(file.exists())
			{
                logger.info("Existing Excel file found. Loading workbook...");
				return new XSSFWorkbook(new FileInputStream(filename));
			}
			else
			{
                logger.info("Excel file not found. Creating new workbook...");
                return new XSSFWorkbook();
			}
		}
		catch(Exception e)
		{
			logger.warn("error while getting Excel... creating new workbook");
			return new XSSFWorkbook();
		}
	}
	static int RowCount=1;
	public static void writeDataGym(String browser,String menu) throws IOException
	{
		String filename="C:\\Users\\2403586\\Desktop\\Seleniumwebdriver\\MainProject\\test-ouput-excel\\TestOutput_Excel.xlsx";
		String sheetName=browser.toLowerCase().equals("chrome") ? "Chrome Gym Result" : "Edge Gym Result";
        logger.info("Writing results to Excel: Sheet = " + sheetName);

		
		try(Workbook workbook=getOrCreateWorkbook(filename))
		{
			Sheet sheet=workbook.getSheet(sheetName);
			if(sheet == null)
			{
				sheet=workbook.createSheet(sheetName);
                logger.info("Old sheet removed: " + sheetName);
			}
			
			sheet=workbook.getSheet(sheetName);
			Row headerRow=sheet.createRow(0);
			headerRow.createCell(0).setCellValue("SubMenu");
			
			

				Row row=sheet.createRow(RowCount++);
				row.createCell(0).setCellValue(menu);
                logger.debug("Written: " + menu);

			try {
				FileOutputStream fos=new FileOutputStream(filename);
				workbook.write(fos);
                logger.info("Excel file saved: " + filename);
            }

         catch (Exception e) {
            logger.error("Error while writing Excel file");
			}
		}
	}


}
