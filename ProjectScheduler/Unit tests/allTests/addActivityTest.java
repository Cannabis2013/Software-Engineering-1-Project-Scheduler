package allTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import baseClass.TestTemplate;
import models.ActivityModel;
import models.ProjectModel;
import static_Domain.DateFormatizer;

public class addActivityTest extends TestTemplate {
	
	String activityName = "GUI Test";
	LocalDate startDate = DateFormatizer.dateFromString("08-05-2019"), 
			endDate = DateFormatizer.dateFromString("12-05-2019");
	
	List<String> assignedUsers = Arrays.asList("BB", "TT", "JW");
	
	public addActivityTest()
	{
		String projectLeaderId = "FL";
		
		if(!addProject("admin", projectLeaderId, "05-05-2019", "19-05-2019", "Test project"))
			fail();
		
		ProjectModel project;
		try {
			coreApp.login("admin");
			project = coreApp.project(projectName);
			coreApp.logut();
		} catch (Exception e) {
			fail();
			return;
		}
	}
	
	@Test
	public void addActivity_A()
	{
		try {
			coreApp.login("admin");
		} catch (Exception e1) {
			fail();
		}
		ActivityModel activity = new ActivityModel(activityName,startDate, endDate, 25, assignedUsers, "Test");
		try {
			coreApp.addActivity(projectName, activity);
			activity = coreApp.activity(projectName, activityName);
			
			assertNotNull(activity);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void addActivity_B()
	{
		try {
			coreApp.login("FL");
		} catch (Exception e1) {
			fail();
		}
		ActivityModel activity = new ActivityModel(activityName,startDate, endDate, 25, assignedUsers, "Test");
		try {
			coreApp.addActivity(projectName, activity);
			activity = coreApp.activity(projectName, activityName);
			
			assertNotNull(activity);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void addActivity_C()
	{
		try {
			coreApp.login("TT");
		} catch (Exception e1) {
			fail();
		}
		try {
			ActivityModel activity = new ActivityModel(activityName,startDate, endDate, 25, assignedUsers, "Test");
			coreApp.addActivity(projectName, activity);
			fail();
			
		} catch (Exception e) {
			
		}
		ActivityModel activity = null; 
		try {
			activity = coreApp.activity(projectName, activityName);
		} catch (Exception e) {
			assertNull(activity);			
		}
	}
	
	
}
