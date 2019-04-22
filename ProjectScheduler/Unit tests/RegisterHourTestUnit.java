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
	private ApplicationCore coreApp = new ApplicationCore();
	
	@Test
	public void registerHour()
	{
		List<String> chosenUsers = Arrays.asList(new String[] {"TT","JW", "BB"});
		boolean result = registerHourTestCase("admin", "Project TEST", "FL", "FL", "GUI Test",chosenUsers,
				"Menus", "BB");
		
		assertEquals(true, result);
	}
	
	@Test
	public void unRegisterHourSuccesfull()
	{
		String projectName = "Project TEST";
		String activityName = "GUI Test";
		String regName = "Menus";
		List<String> chosenUsers = Arrays.asList(new String[] {"TT","JW", "BB"});
		boolean result = registerHourTestCase("admin", projectName, "FL", "FL", activityName,chosenUsers,
				regName, "BB");
		
		if(!result)
			fail();
		if(!coreApp.login("BB"))
			fail();
		
		try {
			coreApp.unRegisterHour(projectName, activityName, regName);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void unRegisterHourUnSuccesfull()
	{
		String projectName = "Project TEST";
		String activityName = "GUI Test";
		String regName = "Menus";
		List<String> chosenUsers = Arrays.asList(new String[] {"TT","JW", "BB"});
		boolean result = registerHourTestCase("admin", projectName, "FL", "FL", activityName,chosenUsers,
				regName, "BB");
		
		if(!result)
			fail();
		if(!coreApp.login("TT"))
			fail();
		
		try {
			coreApp.unRegisterHour(projectName, activityName, regName);
			fail();
		} catch (Exception e) {
			
		}
	}
	
	public boolean registerHourTestCase(String projectUserName,
			String projectName,
			String projectLeaderName,
			String activityUserName,
			String activityName,
			List<String> assignedUsers,
			String registerHourId, 
			String registerHourUserName)
	{
		
		int workHour = 4, estimatedWorkHours = 4;
		
		if(!coreApp.login("FL"))
			return false;
		
		if(!addProject(projectUserName, projectName, projectLeaderName, "05-05-2019", "19-05-2019", "Test project"))
			return false;
		
		ProjectModel project = coreApp.project("Project TEST");
		
		if(!addActivity(project.projectLeaderId(), activityName, project, "05-05-2019", "15-05-2019", 
				estimatedWorkHours,assignedUsers,""))
			return false;
		
		if(!addRegitstrationObject(registerHourUserName, 
				projectName, activityName, registerHourId, workHour, ""))
			return false;
		
		return true;
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