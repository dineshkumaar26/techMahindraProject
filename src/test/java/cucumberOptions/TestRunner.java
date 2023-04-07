package cucumberOptions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\sabar\\eclipse-workspace\\techMahindraProject\\src\\test\\resources\\Features\\003_Data_Spec.feature", //the path of the feature files
		glue={"stepDefinitions"}, //the path of the step definition files
		//format= {"pretty","html:test-output", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"}, //to generate different types of reporting
		monochrome = true, //display the console output in a proper readable format
		//strict = true, //it will check if any step is not defined in step definition file
		dryRun = false, //to check the mapping is proper between feature file and step def file
		//tags = {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}	
		//plugin = {"json:target/cucumber.json", "html:target/cucumber-html-report"},
		plugin = {"pretty", "html:target/cucumber-reports"}
		)
 
public class TestRunner {
	
}
