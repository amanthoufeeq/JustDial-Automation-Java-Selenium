package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Factory.BaseClass;

public class FreeListing extends BasePage {
	
	WebDriverWait wait=BaseClass.getExplicitWait();
	public FreeListing()
	{
		super();
	}

	JavascriptExecutor js = (JavascriptExecutor) BaseClass.getDriver();
	
	@FindBy(xpath="//button[@class='iconwrap closeicon__grey']")
	WebElement close;
	
	@FindBy(xpath="//input[@aria-label='Enter Mobile Number']")
	WebElement inputNumber;
	
	@FindBy(xpath="//div[@class='businesslistfree_businesslistfree__left__EHNaD']/child::form/button")
	WebElement startNow;
	
	@FindBy(xpath="//span[@class='undefined entermobilenumber_error__text__uPM09']")
	WebElement error;
	
	@FindBy(xpath="//div[@class='otp_modal__header__right__SZXbm color111']/b")
	WebElement otpVerification;
	
	@FindBy(xpath="//a[@id='homebreadcrum']")
	WebElement home;
	
	public void clickHome()
	{
		wait.until(ExpectedConditions.elementToBeClickable(home)).click();
	}
	
	public void handleNumberPopUp()
	{
		wait.until(ExpectedConditions.visibilityOf(close)).click();
	}
	
	public void setInputNumber(String number) throws InterruptedException
	{
		inputNumber.clear();
		inputNumber.sendKeys(number);
	}
	
	public void clickStartNow()
	{
		startNow.click();
	}
	
	public String getErMessage()
	{
		 return error.getText();
	}

	
	public Boolean verifyNumber()
	{
		return wait.until(ExpectedConditions.visibilityOf(otpVerification)).isDisplayed();
		
	}
	
	
}
