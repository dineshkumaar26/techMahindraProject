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


public class DataStepDefinitions  {
	List<String[]> rows1 , rows2;

	@Given("read data from Input File1")
	public void read_data_from_input_file1() throws IOException, CsvException {
		String csvFile = "C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\InputFile1.csv";
	        String line = "";
	        String csvDelimiter = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(csvDelimiter);
	                System.out.println("data read is -------"+data.length);
	                System.out.println("data read is ++++++++"+data.hashCode());
	                System.out.println("String read is ******"+String[].class);
	                 // process the data array here
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	

	@Then("read data from Input File2")
	public void read_data_from_input_file2() throws IOException, CsvException {
		String csvFile2 = "C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\InputFile2.csv";
		CSVReader csvReader2 = new CSVReader(new BufferedReader(new FileReader(csvFile2)));
		rows2 = csvReader2.readAll();
		csvReader2.close();
	}

	@Then("write data at Output File")
	public void write_data_at_output_file() throws IOException {
		String outputCsvFile = "C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\output.csv";
		CSVWriter csvWriter = new CSVWriter(new FileWriter(outputCsvFile));

		// Perform the multiplication on the data from the CSV files
		List<String[]> combinedRows = new ArrayList<String[]>();
		for (int i = 0; i < rows1.size(); i++) {
			String[] row1 = rows1.get(i);
			String[] row2 = rows2.get(i);
			double result = 0;
			if(i==2) {
				// Extract the required columns and perform the multiplication
				double value1 = Double.parseDouble(row1[0]);
				double value2 = Double.parseDouble(row2[1]);
				 result = value1 * value2;
			}
			// Combine the data from both CSV files
			String[] combinedRow = new String[] { row1[0], row2[1], row1[1], row2[0], String.valueOf(result) };
			combinedRows.add(combinedRow);
		}

		// Write the combined data to the output CSV file
		csvWriter.writeAll(combinedRows);

		// Close the CSV writer
		csvWriter.close();
	}
}
