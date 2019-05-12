package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.LocalDate;

import Application_Facade.ApplicationCore;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.ProjectModel;

public class ProjectSteps {
	
	ApplicationCore coreApp = new ApplicationCore();
	DateFormat simpleDate = new SimpleDateFormat("dd-mm-yyyy");
	ProjectModel tempProject;
	String projectName = "Project CANVAS";
	
	
	public ProjectSteps()
	{
		try {
			coreApp.login("admin");
		} catch (Exception e) {
			fail();
		}
	}
  
	@Given("the currently logged in user is admin")
	public void the_currently_logged_in_user_is_admin() {
		assertEquals(true, coreApp.isAdmin());
	}

	
	@Given("he fills an project application with the following information: start date today, end date five days later, projectleaderid {string} , short description {string}")
	public void he_fills_an_project_application_with_the_following_information_start_date_today_end_date_five_days_later_projectleaderid_short_description(String string, String string2) {
		ProjectModel project;
		try {
			project = new ProjectModel(projectName,string, LocalDate.now(),LocalDate.now().plusDays(5),string2);
			tempProject = project;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail();
		} catch(Exception e1)
		{
			e1.printStackTrace();
			fail();
		}
	}

	@Then("the project can be retrieved at the ProjectManager database.")
	public void the_project_can_be_retrieved_at_the_ProjectManager_database() {
	    
		try {
			coreApp.addProject(tempProject);
		} catch (Exception e) {
			fail();
		}
		
		ProjectModel retrievedProject;
		try {
			retrievedProject = coreApp.project(projectName);
		} catch (Exception e) {
			fail();
			return;
		}
		
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
	    try {
			coreApp.login(string);
		} catch (Exception e) {
			fail();
		}
	}

	@Then("he fails to add the project to the database.")
	public void he_fails_to_add_the_project_to_the_database() {
		try {
			coreApp.addProject(tempProject);
			fail();
		} catch (Exception e) {
		}
	}
	
	@Given("a project exists with the name {string}")
	public void a_project_exists_with_the_name(String string) {
		
	}
	@Given("a project exists")
	public void a_project_exists() {
	    
	    ProjectModel project = new ProjectModel(projectName,"FL", LocalDate.now(), LocalDate.now(), "");
	    try {
			coreApp.addProject(project);
		} catch (Exception e) {
			fail();
		}
	    try {
			coreApp.project(projectName);
		} catch (Exception e) {
			fail();
		}
	}

	@Given("the user currently logged in is admin")
	public void the_user_currently_logged_in_is_admin() {
	    // Write code here that turns the phrase above into concrete actions
	    try {
			coreApp.login("admin");
		} catch (Exception e) {
			fail();
		}
		assertTrue(coreApp.isAdmin());
	}

	@Then("he performs an action that results in removing the project from the system.")
	public void he_performs_an_action_that_results_in_removing_the_project_from_the_system() {
		try {
			coreApp.removeProject(projectName);
		} catch (Exception e) {
			fail();
		}
		
		try {
			coreApp.project(projectName);
			fail();
		} catch (Exception e) {
			
		}
		
	}
	
	@Given("the user with username {string} enters his username")
	public void the_user_with_username_enters_his_username(String string) {
		try {
			coreApp.login(string);
		} catch (Exception e) {
			fail();
		}
	}

	@Given("he wants to remove the project with the title {string}.")
	public void he_wants_to_remove_the_project_with_the_title(String string) {
	    projectName = string;
	}

	@Then("he recieves a message that reads {string}.")
	public void he_recieves_a_message_that_reads(String string) {
		try {
			coreApp.removeProject(projectName);
			fail();
		} catch (Exception e) {
			assertEquals(string, e.getMessage());
		}
	}

}
