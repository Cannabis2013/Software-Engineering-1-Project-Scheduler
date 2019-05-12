package test;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import Application_Facade.ApplicationCore;
import cucumber.api.java.en.*;
import models.ActivityModel;
import models.ProjectModel;

public class ProjectCreationSteps {
	
	ApplicationCore coreApp = new ApplicationCore();
	String projectName = "Project CANVAS";
	
	public ProjectCreationSteps() {
		
	}
	
	@Given("that a non-administrator user named {string} is logged in")
	public void thatANonAdministratorUserNamedIsLoggedIn(String string) {
	    try 
	    {
	    	coreApp.login(string);
	    	assertTrue(!coreApp.currentUserLoggedIn().UserName().equals("admin"));
	    } catch (Exception e) {
	    	fail();
	    }
	}

	@Then("he fails to create a project called {string}")
	public void heFailsToCreateAProjectCalled(String string) {
		
		
		String pLeader = "FL";
		String startDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
		String endDate =DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now().plusDays(2));
		String shortDescription = "This is a test project";
		
		try {
			ProjectModel project = new ProjectModel(projectName,pLeader, startDate, endDate, shortDescription);
			projectName = project.projectName();
			coreApp.addProject(project);
			fail();
			} catch (Exception e) {
			assertTrue(true);
		}
	}
}
