package allTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import baseClass.TestTemplate;
import models.ProjectModel;

public class RegisterHourTests extends TestTemplate {
	
	private List<String> userNames = Arrays.asList("BB", "TT", "JW");
	
	public RegisterHourTests() 
	{
		String activityName = "GUI Test";
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
		
		if(!addActivity(project.projectLeaderId(), activityName, project, "05-05-2019", "15-05-2019", 
				20,userNames,""))
			fail();
	}
	
	@Test
	public void registerHour_InputSet_A()
	{
		String activityName = "GUI Run";
		String userId = "TT";
		String regName = "Menus";
		int workHours = 4;
		boolean result = addRegistrationObject(userId, projectName, activityName, regName, workHours, "");
		
		assertEquals(false, result);
	}
	
	@Test
	public void registerHour_InputSet_B()
	{
		String activityName = "GUI Test";
		String userId = "NE";
		String regName = "Menus";
		int workHours = 4;
		boolean result = addRegistrationObject(userId, projectName, activityName, regName, workHours, "");
		
		assertEquals(false, result);
	}
	
	@Test
	public void registerHour_InputSet_C()
	{
		String activityName = "GUI Test";
		String userId = "TT";
		String regName = "Menus";
		int workHours = 4;
		boolean result = addRegistrationObject(userId, projectName, activityName, regName, workHours, "");
		
		assertEquals(true, result);
	}
	
}