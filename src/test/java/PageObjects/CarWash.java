package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Factory.BaseClass;
import utitlities.CarInfo;
import utitlities.DataWriter;

public class CarWash extends BasePage{
	
	public JavascriptExecutor js;
	DataWriter writer;
	WebDriverWait wait=BaseClass.getExplicitWait();
	
	public CarWash()
	{
		super();
	}
	
	@FindBy(xpath="//button[@class='jsx-679474515 resfilter_item gray_whitefill_animat font15 color111 fw500 all_filter_container']")
	WebElement selectFilters;
	
	@FindBy(xpath="//span[text()='4.0+']")
	WebElement selectRating;	
	
	@FindBy(xpath="//button[text()='Apply Filters']")
	WebElement applyFilters;
	
	@FindBy(xpath="//ul[@id='filter_ul']/li[1]")
	WebElement sortBy;
	
	@FindBy(xpath="//*[text()='Rating']")
	WebElement sortByRating;
	
	@FindBy(xpath="//div[@class='jsx-7cbb814d75c86232 resultbox ']")
	List<WebElement> carWashResult;
	
	@FindBy(xpath="//div[@class='jsx-fcf3b44760f91361 jd_modal_close jdicon jd_modal_close_bd' and @role='button']")
	WebElement closeLoginLargePopUp;
	
	
	public void clickSelectFilters()
	{
		selectFilters.click();
	}
	
	public void clickSelectRating()
	{
		js=(JavascriptExecutor) BaseClass.getDriver();
		js.executeScript("arguments[0].click();", selectRating);
	}
	
	public void clickApplyFilters()
	{
		applyFilters.click();
	}
	
	public void clickSortBy()
	{
		sortBy.click();
	}
	
	public void applySortBy()
	{
		js=(JavascriptExecutor) BaseClass.getDriver();
		js.executeScript("arguments[0].click()", sortByRating);
	}
	
	
	public List<WebElement> getCarWashResult() throws InterruptedException
	{
		return carWashResult;
	
	}
	
	

}
