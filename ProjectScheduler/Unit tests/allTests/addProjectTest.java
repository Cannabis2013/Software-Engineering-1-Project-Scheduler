package allTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import baseClass.TestTemplate;
import models.ProjectModel;

public class addProjectTest extends TestTemplate {
	
	@Test
	public void addProject_A()
	{
		String currentUserName = "admin",
				projectLeaderId = "FL",
				startDate = "05-05-2019",
				endDate = "06-05-2019",
				description = "This is a test project";
		
		if(!addProject(currentUserName, projectLeaderId, startDate, endDate, description))
			fail();
		
		ProjectModel project = getProject(currentUserName, projectName);
		
		assertNotNull(project);
	}
	
	@Test
	public void addProject_B()
	{
		String currentUserName = "BB",
				projectLeaderId = "FL",
				startDate = "05-05-2019",
				endDate = "06-05-2019",
				description = "This is a test project";
		
		if(addProject(currentUserName, projectLeaderId, startDate, endDate, description))
			fail();
		
		ProjectModel project = getProject(currentUserName, projectName);
		
		assertNull(project);
	}
}
