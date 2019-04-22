package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Application_Facade.ApplicationCore;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.ActivityModel;
import models.HourRegistrationModel;
import models.ProjectModel;

public class HourRegisterSteps {
	
	ApplicationCore coreApp = new ApplicationCore();
	String projectName, activityName, tempRegName;
	
	@Given("a project with name {string} exists with a user with username {string} as projectleader")
	public void a_project_with_name_exists_with_a_user_with_username_as_projectleader(String string, String string2) {
	    coreApp.login("admin");
		String projectTitle = string, projectLeaderUserName = string2;
		Date startDate = TestUnit.DateFromString("05-05-2019");
		Date endDate = TestUnit.DateFromString("19-05-2019");
		
		ProjectModel project = new ProjectModel(projectTitle, projectLeaderUserName, startDate, endDate, "");
		coreApp.addProject(project);
		projectName = projectTitle;
	}
	
	@Given("an activity extists with name {string} with some random chosen date intervals chosen")
	public void an_activity_extists_with_name_with_some_random_chosen_date_intervals_chosen(String string) {
		Date startDate = TestUnit.DateFromString("05-05-2019");
	    Date endDate = TestUnit.DateFromString("11-05-2019");
	    ProjectModel parentProject = coreApp.project(projectName);
	    ActivityModel activity = new ActivityModel(string, parentProject, startDate, endDate);
	    try {
			coreApp.addActivity(projectName, activity);
			activityName = string;
		} catch (Exception e) {
			fail();
		}
	}

	@Given("with the following users assigned: {string} , {string} and {string}")
	public void with_the_following_users_assigned_and(String string, String string2, String string3) {
	    List<String> assignedUsers = new ArrayList<>();
	    
	    assignedUsers.add(string);
	    assignedUsers.add(string2);
	    assignedUsers.add(string3);
	    
	    try {
			coreApp.activity(projectName, activityName).AssignUsers(assignedUsers);
		} catch (Exception e) {
			fail();
		}
	}

	@Given("a user with userName {string} is logged in")
	public void a_user_with_userName_is_logged_in(String string) {
	    coreApp.login(string);
	}

	@When("he fills an application with the title {string} for registering {int} hours to the activity {string}")
	public void he_fills_an_application_with_the_title_for_registering_hours_to_the_activity(String string, Integer int1, String string2) {
		try {
			coreApp.registerHour(projectName, string2, string, int1, "");
			tempRegName = string;
		} catch (Exception e) {
			
		}
	}

	@Then("the registered hour object is stored in the system.")
	public void the_registered_hour_object_is_stored_in_the_system() {
	    HourRegistrationModel regObject = coreApp.hourRegistrationModel(activityName, tempRegName);
	    assertEquals(true, regObject != null);
	}
	
	@Then("the registered hour is not stored in the system.")
	public void the_registered_hour_is_not_stored_in_the_system() {
		HourRegistrationModel regObject = coreApp.hourRegistrationModel(activityName, tempRegName);
	    assertEquals(false, regObject != null);
	}
	
}
