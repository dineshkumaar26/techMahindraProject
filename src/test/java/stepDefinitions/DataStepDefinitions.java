package stepDefinitions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


public class DataStepDefinitions  {
	List<String[]> rows1 , rows2;


	@Given("read data from Input File and merge in Output file")
	public void read_data_from_input_file_and_merge_in_output_file() {


		try {
			// load the first excel file
			FileInputStream file1 = new FileInputStream(new File("C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\InputFile1.csv"));
			XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
			XSSFSheet sheet1 = workbook1.getSheetAt(0);

			// load the second excel file
			FileInputStream file2 = new FileInputStream(new File("C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\InputFile2.csv"));
			XSSFWorkbook workbook2 = new XSSFWorkbook(file2);
			XSSFSheet sheet2 = workbook2.getSheetAt(0);

			// create the output workbook and sheet
			XSSFWorkbook outputWorkbook = new XSSFWorkbook();
			XSSFSheet outputSheet = outputWorkbook.createSheet("C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\Output");

			// write the headers to the output sheet
			Row headerRow = outputSheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("PositionID");
			headerRow.createCell(2).setCellValue("ISIN");
			headerRow.createCell(3).setCellValue("Quantity");
			headerRow.createCell(4).setCellValue("Total Price");

			// iterate through the rows in the second sheet
			for (int i = 1; i <= sheet2.getLastRowNum(); i++) {
				XSSFRow row2 = sheet2.getRow(i);
				int primaryID = (int) row2.getCell(1).getNumericCellValue();
				int quantity = (int) row2.getCell(2).getNumericCellValue();

				// look for matching row in the first sheet
				for (int j = 1; j <= sheet1.getLastRowNum(); j++) {
					XSSFRow row1 = sheet1.getRow(j);
					if ((int) row1.getCell(0).getNumericCellValue() == primaryID) {
						String ISIN = row1.getCell(2).getStringCellValue();
						double unitPrice = row1.getCell(3).getNumericCellValue();
						double totalPrice = quantity * unitPrice;

						// write the values to the output sheet
						Row outputRow = outputSheet.createRow(outputSheet.getLastRowNum() + 1);
						outputRow.createCell(0).setCellValue(primaryID);
						outputRow.createCell(1).setCellValue(row2.getCell(0).getStringCellValue());
						outputRow.createCell(2).setCellValue(ISIN);
						outputRow.createCell(3).setCellValue(quantity);
						outputRow.createCell(4).setCellValue(totalPrice);
					}
				}
			}

			// write the output file
			FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\output.xlsx"));
			outputWorkbook.write(outFile);
			outFile.close();

			// close all workbooks and streams
			workbook1.close();
			workbook2.close();
			outputWorkbook.close();
			file1.close();
			file2.close();

			System.out.println("Output file generated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
