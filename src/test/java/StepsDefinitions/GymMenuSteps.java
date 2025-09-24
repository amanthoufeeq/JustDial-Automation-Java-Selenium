package StepsDefinitions;

import java.io.IOException;
import java.util.List;

//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import Factory.BaseClass;
import Factory.logger;
import PageObjects.GymMenu;
import PageObjects.Home;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utitlities.DataWriter;


public class GymMenuSteps {
	
	Home home=new Home();
	GymMenu menu=new GymMenu();

	
	@When("user navigates to Gym")
	public void user_navigates_to_gym() {
		logger.info("Navigates to Gym");
	    home.clickGym();
	}

	@Then("user prints Gym SubMenu items")
	public void user_prints_gym_sub_menu_items() throws IOException, InterruptedException {
		List<WebElement> subMenu=menu.getGymSubMenu();
		for(WebElement element:subMenu)
		{
			String menu=element.getText();
			if(!menu.isEmpty())
				DataWriter.writeDataGym(BaseClass.getBrowser(), menu);
			
		}
	    logger.info("Gym Sub menu retrieved and printed");
	}


}
