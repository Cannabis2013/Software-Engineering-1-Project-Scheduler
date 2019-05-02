package baseClass;

import java.time.LocalDate;
import java.util.List;

import Abstractions.Model;
import Application_Facade.ApplicationCore;
import models.ActivityModel;
import models.ProjectModel;
import test.TestUnit;

public class TestTemplate {
	
	protected ApplicationCore coreApp = new ApplicationCore();
	
	protected boolean addProject(String loggedInUserName, String projectTitle, 
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
	
	protected boolean addActivity(String loggedInUserName, 
			String activityName,
			Model parentModel,
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
			coreApp.addActivity(parentModel.modelId(), activity);
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
		if(!coreApp.login(loggedInUserName))
			return false;
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
		if(!coreApp.login(loggedInUserName))
			return false;
		try {
			coreApp.unRegisterHour(projectName, activityName, registrationId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
