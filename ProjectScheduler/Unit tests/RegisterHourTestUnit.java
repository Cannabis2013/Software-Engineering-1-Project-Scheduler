import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;

import Abstractions.AbstractModel;
import Application_Facade.ApplicationCore;
import Test.TestUnit;
import models.ActivityModel;
import models.ProjectModel;

public class RegisterHourTestUnit {
	ApplicationCore coreApp = new ApplicationCore();
	@Test
	public void registerHourTestCase()
	{	
		String projectName = "Project TEST", 
				activityName = "GUI Test", 
				registerId = "Menu work";
		
		int workHour = 4, estimatedWorkHours = 4;
		
		if(!coreApp.login("FL"))
			fail();
		
		if(!addProject("admin", projectName, "FL", "05-05-2019", "19-05-2019", "Test project"))
			fail();
		
		ProjectModel project = coreApp.project("Project TEST");
		
		List<String> chosenUsers = Arrays.asList(new String[] {"TT","JW", "BB"});
		
		if(!addActivity(project.projectLeaderId(), activityName, project, "05-05-2019", "15-05-2019", 
				estimatedWorkHours,chosenUsers,""))
			fail();
		
		
		assertEquals(true, addRegitstrationObject("TT", 
				projectName, activityName, registerId, workHour, ""));
		
	}
	
	private boolean addProject(String loggedInUserName, String projectTitle, 
			String projectLeaderId, 
			String startDate, 
			String endDate, 
			String description)
	{
		
		if(!coreApp.login(loggedInUserName))
			return false;
		
		Date sDate;
		Date eDate;
		
		try {
			sDate = TestUnit.DateFromString("05-05-2019");
			eDate = TestUnit.DateFromString("19-05-2019");
		} catch (NullPointerException e) {
			return false;
		}
		
		ProjectModel project;
		try {
			project = new ProjectModel(projectTitle, projectLeaderId, sDate, eDate, description);
		} catch (Exception e) {
			return false;
		}
		
		if(!coreApp.addProject(project).equals(""))
			return false;
		
		return true;
	}
	
	private boolean addActivity(String loggedInUserName, 
			String activityName,
			AbstractModel parentModel,
			String startDate, String endDate,
			int estimatedWorkHours,
			List<String> assignUsers, 
			String description)
	{
		
		if(!coreApp.login(loggedInUserName))
			return false;
		
		Date sDate;
		Date eDate;
		
		try {
			sDate = TestUnit.DateFromString(startDate);
			eDate = TestUnit.DateFromString(endDate);
		} catch (NullPointerException e) {
			return false;
		}
		
		ActivityModel activity;
		try {
			activity = new ActivityModel(activityName, parentModel, sDate, eDate,estimatedWorkHours,assignUsers,description);
		} catch (IllegalArgumentException e) {
			return false;
		}
		
		try {
			coreApp.addActivity(parentModel.modelIdentity(), activity);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	private boolean addRegitstrationObject(String loggedInUserName,
			String projectName, 
			String activityName,
			String registrationName,
			int workHours,
			String description)
	{
		if(!coreApp.login(loggedInUserName))
			return false;
		
		try {
			coreApp.registerHour(projectName, activityName, registrationName, workHours, description);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
