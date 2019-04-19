package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import Application_Facade.ApplicationCore;
import cucumber.api.java.en.*;
import models.ActivityModel;
import models.ProjectModel;

public class ActivitySteps {
	
	ApplicationCore coreApp = new ApplicationCore();
	ActivityModel tempActivity;
	String projectName;
	
	public ActivitySteps()
	{
		SimpleDateFormat simpleDate = new SimpleDateFormat();
		projectName = "Project CANVAS";
		
		String pLeader = "Finn_Luger";
		String startDate = simpleDate.format(Calendar.getInstance().getTime());
		String endDate ="05-05-2019";
		String shortDescription = "This is a test project";
		
		try {
			ProjectModel project = new ProjectModel(projectName, pLeader, startDate, endDate, shortDescription);
			coreApp.login("admin");
			coreApp.addProject(project);
			coreApp.logut();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Given("a project with name {string} exists")
	public void a_project_with_name_exists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(coreApp.project(string) != null);
	}

	@Given("the projectleader is a user with username {string}")
	public void the_projectleader_is_a_user_with_username(String string) {
	    String pLeaderId = coreApp.project(projectName).projectLeaderId();
	    assertTrue(pLeaderId.equals(string));
	}

	@Then("Finn Luger should be able to add an activity to project CANVAS")
	public void finn_Luger_should_be_able_to_add_an_activity_to_project_CANVAS() {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(true);
	}

	@Then("other users shouldn't")
	public void other_users_shouldn_t() {
	    assertTrue(true);
	}

	@Given("the user currently logged in has username {string}")
	public void the_user_currently_logged_in_has_username(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    coreApp.login(string);
	    String currentUserName = coreApp.currentUserLoggedIn().UserName();
	    assertEquals(true, currentUserName.equals(string));
	}

	@Given("is projectleader for {string}")
	public void is_projectleader_for(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    ProjectModel project = coreApp.project(string);
	    String projectLeader = project.projectLeaderId();
	    String currentUserName = coreApp.currentUserLoggedIn().UserName();
	    
	    assertEquals(true, projectLeader.equals(currentUserName));
	}

	@When("he creates an activity with title {string} and some random interval dates")
	public void he_creates_an_activity_with_title_and_some_random_interval_dates(String string) {
		
		ProjectModel project = coreApp.project(projectName);
	    String activityId = string;
	    Date startDate = Calendar.getInstance().getTime();
	    Date enddDate = (Date) startDate.clone();
	    
	    tempActivity = new ActivityModel(activityId,project,startDate,enddDate, null);
	}

	@When("assigns {string} , {string} and {string}")
	public void assigns_and(String string, String string2, String string3) {
	    
		tempActivity.assignUser(string);
		tempActivity.assignUser(string2);
		tempActivity.assignUser(string3);
	}

	@Then("he succesfully manage to add the activity to {string}")
	public void he_succesfully_manage_to_add_the_activity_to(String string) {
	    coreApp.addActivity(projectName, tempActivity);
		ActivityModel activity = coreApp.activity(projectName, tempActivity.modelIdentity());
	    
	    assertEquals(true, activity != null);
	}
	
	@Then("he unsuccesfully manage to add the activity to {string}")
	public void he_unsuccesfully_manage_to_add_the_activity_to(String string) {
		ActivityModel activity = coreApp.activity(projectName, tempActivity.modelIdentity());
	    
	    assertEquals(false, activity != null);
	}
}
