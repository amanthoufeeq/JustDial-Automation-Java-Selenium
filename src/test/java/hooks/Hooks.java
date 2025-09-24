package hooks;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Factory.BaseClass;
import Factory.logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
 

public class Hooks {

	private Properties p;

	@Before
	public void setup() throws IOException
	{
		BaseClass base=new BaseClass();
		base.initialiseDriver();
		p=base.getProperties();
		base.getDriver().get(p.getProperty("url"));
		logger.info("Navigated to the url");
		
	}
	
	@After
	public void tearDown(Scenario scenario)
	{ 
		
		TakesScreenshot shot=(TakesScreenshot) BaseClass.getDriver();
		byte[] screenshot=shot.getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());
	    
		BaseClass.getDriver().quit();
		logger.info("Browser closed.");
	}
	

 
}
