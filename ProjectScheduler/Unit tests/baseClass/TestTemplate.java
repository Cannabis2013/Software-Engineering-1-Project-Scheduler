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
	protected String projectName;
	
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
			sDate = TestUnit.DateFromString("05-05-2019");
			eDate = TestUnit.DateFromString("19-05-2019");
		} catch (NullPointerException e) {
			return false;
		}
		
		ProjectModel project;
		try {
			project = new ProjectModel(projectLeaderId, sDate, eDate, description);
		} catch (Exception e) {
			return false;
		}
		
		try {
			coreApp.addProject(project);
		} catch (Exception e) {
			return false;
		}
		projectName = project.projectName();
		return true;
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
			return false;
		}
		
		return true;
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
			return false;
		}
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
			return false;
		}
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
			return false;
		}
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
			model = coreApp.hourRegistrationModel(activityName, registrationId);
		} catch (Exception e) {
			return false;
		}
		
		model.setHours(newHour);
		
		try {
			model = coreApp.hourRegistrationModel(activityName, registrationId);
		} catch (Exception e) {
			return false;
		}
		
		return model.hours() == newHour;
	}
	
	protected boolean removeProject(int index, String userName) {
		try {
			coreApp.login(userName);
		} catch (Exception e1) {
			return false;
		}
	
		try {
		coreApp.removeProject(index);
		return true;
		} catch (Exception e2) {
		return false;
		}
		
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
	protected boolean removeProject(ProjectModel project, String userName) {
		try {
			coreApp.login(userName);
		} catch (Exception e1) {
			return false;
		}
		try {
			coreApp.removeProject(project);
			return true;
		} catch (Exception e2) {
			return false;
		}
	}
	
	
}
