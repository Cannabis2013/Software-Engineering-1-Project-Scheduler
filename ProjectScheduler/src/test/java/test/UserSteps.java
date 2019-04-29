package test;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import managers.UserManager;

import static org.junit.Assert.assertEquals;

import Application_Facade.ApplicationCore;

public class UserSteps {
	
	ApplicationCore coreApp = new ApplicationCore();
	String tempUserName;
	
	@Given("a user enters username {string}")
	public void a_user_enters_username(String string) {
	    // Write code here that turns the phrase above into concrete actions
		tempUserName = string;
	}
	
	@Then("he logs in succesfully")
	public void he_logs_in_succesfully() {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(true, coreApp.login(tempUserName));
	}
	
	@Then("he fails to log in")
	public void he_fails_to_log_in() {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(false, coreApp.login(tempUserName));
	}
}
