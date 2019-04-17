package Test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Application_Facade.ApplicationCore;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ProjectSteps {
	
	ApplicationCore coreApp = new ApplicationCore();
	SimpleDateFormat simpleDate = new SimpleDateFormat("dd-mm-yy");
	
	public ProjectSteps()
	{
		coreApp.login("admin");
	}
  
	@Given("the currently logged in user is admin")
	public void the_currently_logged_in_user_is_admin() {
		assertEquals(true, coreApp.isAdmin());
	}

	@Given("he fills an project application with the following information: Title {string}, start date today, end date at {string}, projectleaderid {string}, short description {string}")
	public void he_fills_an_project_application_with_the_following_information_Title_start_date_today_end_date_at_projectleaderid_short_description(String string, String string2, String string3, String string4) {
	    
		try {
			Date sDate = simpleDate.parse(string2);
			Date eDate = simpleDate.parse(string3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			asser
		}
	    
	    throw new cucumber.api.PendingException();
	}

	@Then("the project with the title {string} can be retrieved at the ProjectManager database.")
	public void the_project_with_the_title_can_be_retrieved_at_the_ProjectManager_database(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

}
