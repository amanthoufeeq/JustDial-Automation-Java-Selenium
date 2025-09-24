package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Factory.BaseClass;

public class BasePage {

	protected WebDriver driver;
	public BasePage()
	{
		this.driver=BaseClass.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
}
