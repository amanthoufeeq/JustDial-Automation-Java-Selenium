package PageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Factory.BaseClass;

public class Home extends BasePage{
	
	WebDriverWait wait=BaseClass.getExplicitWait();
	
	public Home()
	{
		super();
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath="//span[@role='button' and @class='jsx-70849ab28e2808f9 floatbanner_close jdicon']")
	WebElement adCloseButton;
	
	@FindBy(xpath="//a[text()='Maybe Later']")
	WebElement closeLoginPopUp;
	
	@FindBy(xpath="//input[@class='input_location font14 fw400 color111']")
	WebElement locationSelector;
	
	@FindBy(xpath="//input[@id='main-auto']")
	WebElement searchBar;
		
	@FindBy(xpath="//div[@id='srchbtn']")
	WebElement searchButton;
	
	@FindBy(xpath="//div[text()='Free Listing']")
	WebElement freeListing;
	
	@FindBy(xpath="//ul[@class='home_rowbox home_row_2 home_hotkeybox mb-50']/li[14]")
	WebElement gym;
	
	public void closeLoginPopUp()
	{
		wait.until(ExpectedConditions.visibilityOf(closeLoginPopUp));
		closeLoginPopUp.click();
	}
	
	public void closeAd()
	{
		wait.until(ExpectedConditions.visibilityOf(adCloseButton));
		adCloseButton.click();
	}
	
	public void selectNearByLocation()
	{
		locationSelector.sendKeys(Keys.ARROW_DOWN);
		locationSelector.sendKeys(Keys.ARROW_DOWN);
		locationSelector.sendKeys(Keys.ENTER);
	}
	
	public void setSearchInput(String searchInput)
	{
		searchBar.sendKeys(searchInput);
	}
	
	public void clickSearch() throws InterruptedException
	{
		Thread.sleep(1000);
		locationSelector.sendKeys(Keys.ARROW_DOWN);
		searchButton.click();
		
	}
	
	public void clickFreeListing()
	{
		wait.until(ExpectedConditions.visibilityOf(freeListing)).click();
	}
	
	public void clickGym()
	{
		gym.click();
	}
	
}
