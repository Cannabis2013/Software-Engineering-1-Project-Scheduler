package allTests;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import baseClass.TestTemplate;
import models.ProjectModel;

public class OtherHourRegistrationTests extends TestTemplate {
	
	private List<String> userNames = Arrays.asList("BB", "TT", "JW");
	
	public OtherHourRegistrationTests() {
		String projectName = "Project TEST";
		String activityName = "GUI Test";
		String projectLeaderId = "FL";
		
		if(!addProject("admin", projectName, projectLeaderId, "05-05-2019", "19-05-2019", "Test project"))
			fail();
		
		ProjectModel project = coreApp.project("Project TEST");
		
		if(!addActivity(project.projectLeaderId(), activityName, project, "05-05-2019", "15-05-2019", 
				20,userNames,""))
			fail();
	}
	
	@Test
	public void unRegisterHourSuccesfull()
	{
		String projectName = "Project TEST";
		String activityName = "GUI Test";
		String regName = "Menus";
		String userId = "BB";
		int workHours = 4;
		
		boolean result = addRegistrationObject(userId, projectName, activityName, regName, workHours, "");
		
		if(!result)
			fail();
		
		try {
			coreApp.unRegisterHour(projectName, activityName, regName);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void editHourRegistration()
	{
		String projectName = "Project TEST";
		String activityName = "GUI Test";
		String regName = "Menus";
		String userId = "TT";
		int workHours = 8;
		if(!addRegistrationObject(userId,projectName, activityName, regName, workHours, ""))
			fail();
		
		if(!editRegistrationObject(userId, projectName, activityName, regName, 4))
			fail();
	}
}
