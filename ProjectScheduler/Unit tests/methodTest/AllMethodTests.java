package methodTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import baseClass.TestTemplate;
import models.ItemModel;
import models.ProjectModel;

public class AllMethodTests extends TestTemplate {
	
	@Test
	public void projectItemModelsTest()
	{
		try {
			addProject("admin", "Project CANVAS","FL", "05-05-2019", "05-06-2019", "");
			addProject("admin", "Project NEMO","FL", "05-05-2019", "05-06-2019", "");
		} catch (Exception e1) {
			fail();
		}
		
		try {
			coreApp.login("FL");
		} catch (Exception e) {
			fail();
		}
		
		List<ItemModel> itemModels = coreApp.projectItemModels();
		
		assertEquals(2, itemModels.size());
	}
	
	@Test
	public void projectActivityItemModelsTest()
	{
		try {
			addProject("admin", projectName,"FL", "05-05-2019", "05-06-2019", "");
		} catch (Exception e2) {
			fail();
		}
		ProjectModel project = getProject("admin", projectName);
		
		addActivity("FL", "GUI Test", project, 
				"07-05-2019", 
				"17-05-2019", 10, 
				Arrays.asList("TT", "BB", "JW"), "");
		addActivity("FL", "GUI Test2", project, 
				"08-05-2019", 
				"19-05-2019", 10, 
				Arrays.asList("TT", "BB", "JW"), "");
		
		try {
			coreApp.login("FL");
		} catch (Exception e1) {
			fail();
			return;
		}
		
		List<ItemModel> itemModels;
		try {
			itemModels = coreApp.projectActivityItemModels(projectName);
		} catch (Exception e) {
			fail();
			return;
		}
		
		assertEquals(2,itemModels.size());
	}
	
	@Test
	public void activityItemModels()
	{
		try {
			addProject("admin", projectName,"FL", "05-05-2019", "05-06-2019", "");
		} catch (Exception e2) {
			fail();
		}
		ProjectModel project = getProject("admin", projectName);
		
		addActivity("FL", "GUI Test", project, 
				"07-05-2019", 
				"17-05-2019", 10, 
				Arrays.asList("TT", "BB", "JW"), "");
		addActivity("FL", "GUI Test2", project, 
				"08-05-2019", 
				"19-05-2019", 10, 
				Arrays.asList("TT", "BB", "JW"), "");
		
		try {
			coreApp.login("TT");
		} catch (Exception e1) {
			fail();
			return;
		}
		
		List<ItemModel> itemModels;
		try {
			itemModels = coreApp.activityItemModels();
		} catch (Exception e) {
			fail();
			return;
		}
		
		assertEquals(2,itemModels.size());
	}
	@Test
	public void projectItemModelsTestDuplicate()
	{ 
		try {
		addProject("admin", projectName,"FL", "05-05-2019", "05-06-2019", "");
		
		
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			return;
		}
		try {
			addProject("admin", projectName,"FL", "05-05-2019", "05-06-2019", "");
			fail();
		} catch (Exception e) {
			
		}
	}
}
