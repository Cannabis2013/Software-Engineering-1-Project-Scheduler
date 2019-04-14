package Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

import UserDomain.UserManager;

public class UserSteps {
	
	private UserManager uManager = new UserManager();
	String userName;
  
	@Given("The user has the following username: {string}")
	public void the_user_has_the_following_username(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    userName = string;
		assertEquals(true,true);
	}

	@Then("he enters his credentials and logs in succesfully")
	public void he_enters_his_credentials_and_logs_in_succesfully() {
	    // Write code here that turns the phrase above into concrete actions
	    Boolean u = uManager.logIn(userName);
	    assertEquals(true, u);
	}

}
