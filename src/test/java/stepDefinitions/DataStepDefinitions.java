package stepDefinitions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;

public class DataStepDefinitions {
	WebDriver driver;
	LinkedHashMap<String, String> intrumentFilehmap = new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> positionDetailhmap = new LinkedHashMap<String, String>();
	List<String[]> outputFilelist = new ArrayList<>();
	private static final Logger logger = LoggerFactory.getLogger(DataStepDefinitions.class);

	@Given("read data from Input File1")
	public void read_data_from_input_file1() throws IOException {
		String csvFile = "C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\InputFile1.csv";
		String line;
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] row = line.split(cvsSplitBy);
				for (String value : row) {
					System.out.print(value + " ");
					intrumentFilehmap.put(row[2], row[3]);
				}
				System.out.println("");
			}
			System.out.println("IntrumentFile Map values are......" + intrumentFilehmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Then("read data from Input File2")
	public void read_data_from_input_file2() {
		String csvFile = "C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\InputFile2.csv";
		String line;
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {
				String[] row = line.split(cvsSplitBy);
				for (String value : row) {
					System.out.print(value + " ");
					positionDetailhmap.put(row[0], row[2]);
				}
				System.out.println("");
			}
			System.out.println("Position Details File Map values are......" + positionDetailhmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Then("read and validate data from OutputFile")
	public void read_and_validate_data_from_output_file() throws FileNotFoundException, IOException {
		String csvFilePath = "C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\CSV_Files\\output.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				outputFilelist.add(values);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String[] row : outputFilelist) {
			System.out.println(String.join(", ", row));

		}
		System.out.println("Validating the data in OutPut File ........");
		for (int i = 1; i < outputFilelist.size(); i++) {
			int totalPrice = Integer.parseInt((outputFilelist.get(i)[4]));
			int quantity = Integer.parseInt((outputFilelist.get(i)[3]));
			int price = Integer.parseInt((intrumentFilehmap.get(outputFilelist.get(i)[2])));
			if (totalPrice == quantity * price) {
				System.out.printf(" %d is equal to %d * %d%n", totalPrice, quantity, price);
				// logger.info(() -> "The Total Price Value is: " + totalPrice);
			}

		}
	}

}