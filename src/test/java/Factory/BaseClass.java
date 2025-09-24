package Factory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	
	private static Logger logger=LogManager.getLogger(BaseClass.class);
	private static Properties p;
	
	private static ThreadLocal<WebDriver> drivers=new ThreadLocal<>();
	private static ThreadLocal<String> browsers=new ThreadLocal<>();
	
	
	
	public static void setBrowser(String browser)
	{
		browsers.set(browser);
	}
	public static String getBrowser()
	{
		return browsers.get();
	}
	public static void setDriver(WebDriver driver)
	{
		drivers.set(driver);
	}
	public static WebDriver getDriver()
	{
		return drivers.get();
	}
	
	
	
	public static void initialiseDriver()
	{
		String br=browsers.get();
		System.out.println(br);
		switch(br.toLowerCase())
		{
		case "chrome":
	        ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.addArguments(
	            "--disable-notifications",
	            "--disable-popup-blocking",
	            "--disable-blink-features=AutomationControlled",
	            "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
	        );

			setDriver(new ChromeDriver(chromeOptions));
			logger.info("Chrome browser launched successfully");
			break;
			
		case "edge":
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-blink-features=AutomationControlled",
                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
            );
            System.setProperty("webdriver.edge.driver","C:\\Users\\2403586\\Downloads\\edgedriver_win64\\msedgedriver.exe");
            setDriver(new EdgeDriver(edgeOptions));
            logger.info("Edge browser launched successfully");
            break;
        
        default:
        	logger.info("Unsupported browser request.");
        	throw new RuntimeException("Unsupported browser: " + br);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	public static Properties getProperties() throws IOException
	{
		if(p==null)
		{
			try
			{
			FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
			p=new Properties();
			p.load(file);
			
			}
			catch(IOException e)
			{
				System.out.println("Failed to load config file");
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public static WebDriverWait getExplicitWait()
	{
		WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		return wait;
	}
	
	public static Logger getLogger()
	{
		Logger logger=LogManager.getLogger();
		return logger;
	}
	
	public static void captureScreenshot(String fname)
	{
		TakesScreenshot shot=(TakesScreenshot) getDriver();
		File src=shot.getScreenshotAs(OutputType.FILE);
		String timestamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
       try {
		FileHandler.copy(src, new File(System.getProperty("user.dir")+"/screenshots/" + fname + "_" + timestamp + ".png"));
        System.out.println("Screenshot saved: " + fname + " at " + timestamp);

       }
       catch(IOException e)
       {
    	   e.printStackTrace();
       }

	}
	
	

	


}
