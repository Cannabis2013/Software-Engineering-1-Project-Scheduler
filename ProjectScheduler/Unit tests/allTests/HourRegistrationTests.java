package allTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import baseClass.TestTemplate;
import formComponents.ItemModel;
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
	public void registerHourUnSuccesfully()
	{
		int workHours = 4;
		boolean result = addRegistrationObject("FL", projectName, activityName, "Menus", workHours, "");
		
		assertEquals(false, result);
	}
	
	@Test
	public void removeActivitySuccesfully()
	{
		try {
			coreApp.login("FL");
		} catch (Exception e1) {
			fail();
		}
		try {
			coreApp.removeActivity(projectName, activityName);
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void removeProject()
	{
		try {
			coreApp.login("admin");
		} catch (Exception e) {
			fail();
		}
		try {
			coreApp.removeProject(projectName);
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void retrieveActivityItemModels()
	{
		try {
			coreApp.login("TT");
		} catch (Exception e) {
			fail();
		}
		ItemModel[] itemModels = coreApp.activityItemModels();
		
		assertTrue(itemModels.length == 1);
	}
	@Test
	public void retrieveProjectItemModels()
	{
		try {
			coreApp.login("admin");
		} catch (Exception e) {
			fail();
		}
		ItemModel[] itemModels = coreApp.projectItemModels();
		assertTrue(itemModels.length == 1);
	}
	
	@Test
	public void editHourRegistration()
	{
		
	}
	
}