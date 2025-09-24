package StepsDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Factory.BaseClass;
import Factory.logger;
import PageObjects.CarWash;
import PageObjects.Home;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utitlities.CarInfo;
import utitlities.DataWriter;


public class CarWashSteps {
	
	Home home=new Home();
	CarWash wash=new CarWash();
	
	private Properties p;
	JavascriptExecutor js=(JavascriptExecutor) BaseClass.getDriver();
	WebDriverWait wait=BaseClass.getExplicitWait();

	@Given("the user launches browser and open JustDial website")
	public void the_user_launches_browser_and_open_just_dial_website() {
	    logger.info("Launched "+BaseClass.getBrowser()+"browser and opening JustDial website");
	    
	}

	@When("the user handles car wash pop ups")
	public void the_user_handles_car_wash_pop_ups() {
	    logger.info("Handling car wash pop-ups on JustDial");
	    home.closeLoginPopUp();
	    home.closeAd();
	}

	@When("the user search nearby car washing services")
	public void the_user_search_nearby_car_washing_services() throws IOException, InterruptedException {
	    logger.info("Searching for nearby car washing services");
	    home.selectNearByLocation();
	    
	    p=BaseClass.getProperties();
	    
	    home.setSearchInput(p.getProperty("searchInput"));
	    home.clickSearch();
	    
	}

	@When("the user applies rating filter")
	public void the_user_applies_rating_filter() {
	    logger.info("Applying rating filter to car wash services");
	    wash.clickSelectFilters();
	    wash.clickSelectRating();
	    wash.clickApplyFilters();


	}

	@When("the user sort top rated service centres")
	public void the_user_sort_top_rated_service_centres() {
	    logger.info("Sorting top-rated car wash service centres");
	    wash.clickSortBy();
	    wash.applySortBy();	
	}

	@Then("the user should be able to view and print top rated service centres")
	public void the_user_should_be_able_to_view_and_print_top_rated_service_centres() throws InterruptedException {
	    logger.info("Viewing and printing top-rated car wash service centres");
	    
	    List<WebElement> carWashResult=wash.getCarWashResult();
	    
		List<CarInfo> mylist=new ArrayList<CarInfo>();
		
		int count=1;
		for(WebElement searchResult:carWashResult)
		{
			if(count>5)
			{
				break;
			}
			try
			{
			WebElement rating=searchResult.findElement(By.xpath(".//li[contains(text(),'Ratings')]"));
			String ratingText=rating.getText().replaceAll("[^\\d]","");
			int text=Integer.parseInt(ratingText);
			if(text<20)
			{
				continue;
			}

			WebElement washTitle=searchResult.findElement(By.xpath(".//h3[@class='jsx-7cbb814d75c86232 resultbox_title_anchor font22 fw500 color111 line_clamp_1 ']"));
			String washName=washTitle.getText();
			
			String phoneNumber;
			try {
				WebElement showNumber=searchResult.findElement(By.xpath(".//span[text()='Show Number']"));
				js.executeScript("arguments[0].click();", showNumber);
				Thread.sleep(2000);
				try
				{
				phoneNumber=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='font20 fw600 colorFFF mt-15']"))).getText().substring(1, 11);
				}
				catch(Exception e)
				{
					
					searchResult.findElement(By.xpath("//div[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']")).click();
					continue;
				}
				searchResult.findElement(By.xpath("//div[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']")).click();
			
			}
			catch(Exception e)
			{
				WebElement showNumber=searchResult.findElement(By.xpath(".//*[@class='jsx-7cbb814d75c86232 callcontent callNowAnchor']"));
				phoneNumber=showNumber.getText().substring(1, 11);
			}

			
			
			mylist.add(new CarInfo(washName,phoneNumber));
			DataWriter.writeData(BaseClass.getBrowser(),mylist);
//			System.out.println(washName+"    "+phoneNumber);
			
			count++;
			
			}
			
			catch (Exception e) {
				System.out.println(e.getMessage());
				Assert.fail();
				
			}
		}
	}
}
