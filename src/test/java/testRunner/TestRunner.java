package testRunner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Factory.BaseClass;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features={"src/test/resources/features/CarWash.feature",
				"src/test/resources/features/FreeListing.feature",
				"src/test/resources/features/GymMenu.feature"},
		glue= {"StepsDefinitions","hooks"},
	    plugin = {
	            "pretty",
	            "json:target/cucumber-report/cucumber.json",
	            "html:target/cucumber-report/cucumber.html",
	            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	            "rerun:target/rerun.txt"
	        },
	    monochrome=true
	    )





public class TestRunner extends AbstractTestNGCucumberTests {
	
	@BeforeClass
	@Parameters({"browser"})
	public void setBrowser(String browser)
	{
		BaseClass.setBrowser(browser);
	}

}
