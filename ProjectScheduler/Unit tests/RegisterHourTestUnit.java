import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import Abstractions.AbstractModel;
import Application_Facade.ApplicationCore;
import Test.TestUnit;
import formComponents.ItemModel;
import models.ActivityModel;
import models.ProjectModel;

public class RegisterHourTestUnit {
	private ApplicationCore coreApp = new ApplicationCore();
	private String projectName = "Project TEST", activityName = "GUI Test", projectLeaderId = "FL";
	private List<String> userNames;
	
	
	public RegisterHourTestUnit() {
		
		userNames = Arrays.asList("BB", "TT", "JW");
		if(!coreApp.login("FL"))
			fail();
		
		if(!addProject("admin", projectName, projectLeaderId, "05-05-2019", "19-05-2019", "Test project"))
			fail();
		
		ProjectModel project = coreApp.project("Project TEST");
		
		if(!addActivity(project.projectLeaderId(), activityName, project, "05-05-2019", "15-05-2019", 
				20,userNames,""))
			fail();
	}
	
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
	public void removeActivity()
	{
		coreApp.login("FL");
		try {
			coreApp.removeActivity(projectName, activityName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
	@Test
	public void removeProject()
	{
		coreApp.login("admin");
		coreApp.removeProject(projectName);
	}
	
	@Test
	public void retrieveActivityItemModels()
	{
		coreApp.login("TT");
		ItemModel[] itemModels = coreApp.activityItemModels();
		
		assertTrue(itemModels.length == 1);
	}
	
	@Test
	public void retrieveProjectItemModels()
	{
		coreApp.login("admin");
		ItemModel[] itemModels = coreApp.projectItemModels();
		assertTrue(itemModels.length == 1);
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
		if(!addRegitstrationObject(registerHourUserName, 
				projectName, activityName, registerHourId, 2, ""))
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
		
		LocalDate sDate;
		LocalDate eDate;
		
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
		
		LocalDate sDate;
		LocalDate eDate;
		
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