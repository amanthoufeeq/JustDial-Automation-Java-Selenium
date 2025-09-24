package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Factory.BaseClass;

public class GymMenu extends BasePage{
	public GymMenu()
	{
		super();
	}
	
	WebDriverWait wait=BaseClass.getExplicitWait();
	
	@FindBy(xpath="//ul[@id='filter_ul']//span")
	List<WebElement> submenu;
	
	public List<WebElement> getGymSubMenu()
	{
		return submenu;
	}
}
