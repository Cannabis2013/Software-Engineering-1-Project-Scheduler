package allTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import baseClass.TestTemplate;
import models.ActivityModel;
import models.ProjectModel;

public class CodeCoverage extends TestTemplate {
	private String activityName = "GUI Test", projectLeaderId = "FL";
	private List<String> userNames;
	private int numberOfActivities;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private LocalDate startDate, endDate;
	private ProjectModel currentProject = null;
	
	public CodeCoverage() {
		numberOfActivities = 20;
		startDate = LocalDate.parse("05-05-2019",dateFormatter);
		endDate = LocalDate.parse("05-06-2019",dateFormatter);
		
		try {
			addProject("admin",  
					projectLeaderId, 
					startDate.format(dateFormatter), 
					endDate.format(dateFormatter), 
					"Test project");
		} catch (Exception e) {
			fail();
		}
		
		try {
			coreApp.login("admin");
			currentProject = coreApp.project(projectName);
			coreApp.logut();
		} catch (Exception e) {
			fail();
		}
	}
	/*
	@Test
	public void removeProject_index() {
		
		int index = 0;
		String userName = "FL";
		boolean result = removeProject(index, userName);
		assertFalse(result);
		
		userName = "admin";
		result = removeProject(index, userName);
		assertTrue(result);
		
		index = -1;
		result = removeProject(index, userName);
		assertFalse(result);
	}
	*/
	@Test
	public void removeProject_id() {
		String userName = "PB";
		boolean result = removeProject(projectName, userName);
		assertFalse(result);
		
		userName = "admin";
		
		result = removeProject("nonexistent project",userName);
		
		result = removeProject(projectName, userName);
		assertTrue(result);
	}
	/*
	@Test
	public void removeProject_model() {
		String userName = "PB";
		boolean result = removeProject(currentProject, userName);
		assertFalse(result);
		
		userName = "admin";
		ProjectModel invalidProject = null;
		result = removeProject(invalidProject, userName);
		assertFalse(result);
		
		result = removeProject(currentProject, userName);
		assertTrue(result);
		
	}
*/
	@Test
	public void activities_username() {
		String userName = "TT";
		
		try {
			
			List<ActivityModel> activities = coreApp.activities(userName);
		}catch(Exception e) {
			fail();
		}
	}
}
