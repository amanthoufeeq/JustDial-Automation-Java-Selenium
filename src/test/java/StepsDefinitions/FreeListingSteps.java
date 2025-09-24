package StepsDefinitions;

import java.io.IOException;

import org.junit.Assert;

import Factory.BaseClass;
import Factory.logger;
import PageObjects.FreeListing;
import PageObjects.Home;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class FreeListingSteps {

	Home home=new Home();
	FreeListing listing=new FreeListing();

	@When("the user navigates to Free Listing")
	public void the_user_navigates_to_free_listing() {
		home.clickFreeListing();
		logger.info("Navigates to Free Listing");
	}

	@When("the user enters an invalid mobile number {string}")
	public void the_user_enters_an_invalid_mobile_number(String phone) throws InterruptedException {

		try {
			listing.handleNumberPopUp();

		}
		catch(Exception ignored) {}
	
		listing.setInputNumber(phone);
		listing.clickStartNow();
		logger.info("Invalid phone number is verified");
	}

	@Then("an error message should be displayed")
	public void an_error_message_should_be_displayed() throws IOException {
		String errorText=listing.getErMessage();
		if(!errorText.isEmpty())
		{
			BaseClass.captureScreenshot("invalid_number");
			System.out.println("Error: "+listing.getErMessage());
			logger.info("Error Message:"+listing.getErMessage());
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}

	@Then("the user proceeds with valid mobile number and verifies it")
	public void the_user_proceeds_with_valid_mobile_number_and_verifies_it() throws IOException, InterruptedException {
		listing.clickHome();
		home.clickFreeListing();
		listing.setInputNumber(BaseClass.getProperties().getProperty("phoneNumber"));
		listing.clickStartNow();
		
		if(listing.verifyNumber())
		{
			Assert.assertTrue(true);
			logger.info("Phone number verification successfull");
		}
		else
		{
			Assert.fail();
		}
		
	}


}
