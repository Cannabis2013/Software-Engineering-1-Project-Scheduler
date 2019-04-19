package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Application_Facade.ApplicationCore;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.ProjectModel;

public class ProjectSteps {
	
	ApplicationCore coreApp = new ApplicationCore();
	DateFormat simpleDate = new SimpleDateFormat("dd-mm-yyyy");
	ProjectModel tempProject;
	String tempString;
	
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
	    tempString = string;
		
		String currentDate = simpleDate.format(Calendar.getInstance().getTime());
		
		ProjectModel project;
		try {
			project = new ProjectModel(string, currentDate, string2, string3,string4);
			tempProject = project;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("the project with the title {string} can be retrieved at the ProjectManager database.")
	public void the_project_with_the_title_can_be_retrieved_at_the_ProjectManager_database(String string) {
		
		assertEquals(true, coreApp.addProject(tempProject) == "");
		
		ProjectModel retrievedProject = coreApp.project(tempString);
		if(retrievedProject == null)
			fail();
		
		if(!retrievedProject.projectName().equals(tempProject.projectName()))
			fail();
		
		if(!retrievedProject.startDate().equals(tempProject.startDate()))
			fail();
		
		if(!retrievedProject.endDate().equals(tempProject.endDate()))
			fail();
		
		if(!retrievedProject.projectLeaderId().equals(tempProject.projectLeaderId()))
			fail();
		
		if(!retrievedProject.description().equals(tempProject.description()))
			fail();
		
		assertEquals(true, true);
		
	}
	
	@Given("the user with the username {string} is logged in")
	public void the_user_with_the_username_is_logged_in(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    coreApp.login(string);
	}

	@Then("he fails to add the project to the database.")
	public void he_fails_to_add_the_project_to_the_database() {
		assertEquals(false, coreApp.addProject(tempProject) == "");
	}
	
	@Given("a project exists with the name {string}")
	public void a_project_exists_with_the_name(String string) {
		tempString = string;
	    ProjectModel project = new ProjectModel(string, "Finn_Luger", Calendar.getInstance().getTime(), null, "");
	    coreApp.addProject(project);
	    assertEquals(true, coreApp.project(string) != null);
	}

	@Given("the user currently logged in is admin")
	public void the_user_currently_logged_in_is_admin() {
	    // Write code here that turns the phrase above into concrete actions
	    coreApp.login("admin");
		assertTrue(coreApp.isAdmin());
	}

	@Then("he performs an action that results in removing the project from the system.")
	public void he_performs_an_action_that_results_in_removing_the_project_from_the_system() {
		if(!coreApp.removeProject(tempString).equals(""))
			fail();
		if(coreApp.project(tempString) != null)
			fail();
		
		assertTrue(true);
		
	}
	
	@Given("the user with username {string} enters his username")
	public void the_user_with_username_enters_his_username(String string) {
	    assertTrue(coreApp.login(string));
	}

	@Given("he wants to remove the project with the title {string}.")
	public void he_wants_to_remove_the_project_with_the_title(String string) {
	    tempString = string;
	}

	@Then("he recieves a message that reads {string}.")
	public void he_recieves_a_message_that_reads(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(coreApp.removeProject(tempString).equals(string));
	}

}
