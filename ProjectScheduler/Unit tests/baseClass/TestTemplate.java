package baseClass;

import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import Application_Facade.ApplicationCore;
import abstractions.AbstractModel;
import models.ActivityModel;
import models.HourRegistrationModel;
import models.ProjectModel;
import test.TestUnit;

public class TestTemplate {
	
	protected ApplicationCore coreApp = new ApplicationCore();
	protected String projectName = "Project CANVAS";
	
	protected boolean addProject(String loggedInUserName,
			String projectLeaderId, 
			String startDate, 
			String endDate, 
			String description)
	{
		try {
			coreApp.login(loggedInUserName);
		} catch (Exception e1) {
			return false;
		}
		
		LocalDate sDate;
		LocalDate eDate;
		
		try {
			sDate = TestUnit.DateFromString(startDate);
			eDate = TestUnit.DateFromString(endDate);
		} catch (NullPointerException e) {
			return false;
		}
		
		ProjectModel project;
		try {
			project = new ProjectModel(projectName,projectLeaderId, sDate, eDate, description);
		} catch (Exception e) {
			return false;
		}
		
		try {
			coreApp.addProject(project);
		} catch (Exception e) {
			return false;
		}
		
		coreApp.logut();
		return true;
	}
	
	protected boolean addProject(String loggedInUserName,
			String projectName,
			String projectLeaderId, 
			String startDate, 
			String endDate, 
			String description)
	{
		try {
			coreApp.login(loggedInUserName);
		} catch (Exception e1) {
			return false;
		}
		
		LocalDate sDate;
		LocalDate eDate;
		
		try {
			sDate = TestUnit.DateFromString(startDate);
			eDate = TestUnit.DateFromString(endDate);
		} catch (NullPointerException e) {
			return false;
		}
		
		ProjectModel project;
		try {
			project = new ProjectModel(projectName,projectLeaderId, sDate, eDate, description);
		} catch (Exception e) {
			return false;
		}
		
		try {
			coreApp.addProject(project);
		} catch (Exception e) {
			return false;
		}
		
		coreApp.logut();
		return true;
	}
	
	protected ProjectModel getProject(String currentloggedinUser, String projectId)
	{
		try {
			coreApp.login(currentloggedinUser);
			return coreApp.project(projectId);
		} catch (Exception e) {
			return null;
		}
	}
	
	protected boolean addActivity(String loggedInUserName, 
			String activityName,
			ProjectModel parentModel,
			String startDate, String endDate,
			int estimatedWorkHours,
			List<String> assignUsers, 
			String description)
	{
		try {
			coreApp.login(loggedInUserName);
		} catch (Exception e1) {
			return false;
		}
		
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
			activity = new ActivityModel(activityName, sDate, eDate,estimatedWorkHours,assignUsers,description);
		} catch (IllegalArgumentException e) {
			return false;
		}
		
		try {
			coreApp.addActivity(parentModel.projectName(), activity);
		} catch (Exception e) {
			coreApp.logut();
			return false;
		}
		coreApp.logut();
		return true;
	}
	
	public ActivityModel getActivity(String currentLoggedInUser,String projectId,String activityId)
	{
		try {
			coreApp.login(currentLoggedInUser);
			return coreApp.activity(projectId, activityId);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean removeActivity(String loggedInUserName,
			String projectName, 
			String activityName)
	{
		try {
			coreApp.login("FL");
		} catch (Exception e1) {
			return false;
		}
		try {
			coreApp.removeActivity(projectName, activityName);
		} catch (Exception e) {
			coreApp.logut();
			return false;
		}
		coreApp.logut();
		return true;
	}
	
	protected boolean addRegistrationObject(String loggedInUserName,
			String projectName, 
			String activityName,
			String registrationId,
			int workHours,
			String description)
	{
		try {
			coreApp.login(loggedInUserName);
		} catch (Exception e1) {
			return false;
		}
		try {
			coreApp.registerHour(projectName, activityName, registrationId, workHours, description);
		} catch (Exception e) {
			coreApp.logut();
			return false;
		}
		coreApp.logut();
		return true;
	}
	
	protected boolean removeRegistrationObject(String loggedInUserName,
			String projectName, 
			String activityName,
			String registrationId)
	{
		try {
			coreApp.login(loggedInUserName);
		} catch (Exception e1) {
			return false;
		}
		try {
			coreApp.unRegisterHour(projectName, activityName, registrationId);
		} catch (Exception e) {
			coreApp.logut();
			return false;
		}
		coreApp.logut();
		return true;
	}
	
	protected boolean editRegistrationObject(String loggedInUserName,
			String projectName, 
			String activityName,
			String registrationId,
			int newHour)
	{
		try {
			coreApp.login(loggedInUserName);
		} catch (Exception e1) {
			return false;
		}
		HourRegistrationModel model = null;
		try {
			model = coreApp.hourRegistrationModel(projectName,activityName, registrationId);
		} catch (Exception e) {
			coreApp.logut();
			return false;
		}
		
		model.setHours(newHour);
		
		try {
			model = coreApp.hourRegistrationModel(projectName,activityName, registrationId);
		} catch (Exception e) {
			coreApp.logut();
			return false;
		}
		coreApp.logut();
		return model.hours() == newHour;
	}
	
	protected boolean removeProject(String id, String userName) {
		try {
			coreApp.login(userName);
		} catch (Exception e1) {
			return false;
		}
		try {
			coreApp.removeProject(id);
			return true;
		} catch (Exception e2) {
			return false;
		}
		
	}

	
	
	
}
