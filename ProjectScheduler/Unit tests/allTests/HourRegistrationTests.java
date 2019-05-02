package allTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import baseClass.TestTemplate;
import models.ProjectModel;

public class HourRegistrationTests extends TestTemplate {
	
	private String projectName = "Project TEST", activityName = "GUI Test", projectLeaderId = "FL";
	private List<String> userNames = Arrays.asList("BB", "TT", "JW");
	
	
	public HourRegistrationTests() 
	{
		if(!addProject("admin", projectName, projectLeaderId, "05-05-2019", "19-05-2019", "Test project"))
			fail();
		
		ProjectModel project = coreApp.project("Project TEST");
		
		if(!addActivity(project.projectLeaderId(), activityName, project, "05-05-2019", "15-05-2019", 
				20,userNames,""))
			fail();
	}
	
	@Test
	public void registerHourSuccesfully()
	{
		int workHours = 4;
		boolean result = addRegistrationObject("TT", projectName, activityName, "Menus", workHours, "");
		
		assertEquals(true, result);
	}
	
	@Test
	public void unRegisterHourSuccesfull()
	{
		String projectName = "Project TEST";
		String activityName = "GUI Test";
		String regName = "Menus";
		int workHours = 4;
		boolean result = addRegistrationObject("BB", projectName, activityName, regName, workHours, "");
		
		if(!result)
			fail();
		
		try {
			coreApp.login("BB");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			coreApp.unRegisterHour(projectName, activityName, regName);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void editHourRegistration()
	{
		String regId = "Menus";
		int workHours = 8;
		if(!addRegistrationObject("BB",projectName, activityName, regId, workHours, ""))
			fail();
		
		if(!editRegistrationObject("BB", projectName, activityName, regId, 4))
			fail();
	}
	
}