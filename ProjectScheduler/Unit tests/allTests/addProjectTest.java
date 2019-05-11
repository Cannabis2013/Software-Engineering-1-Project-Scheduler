package allTests;

import static org.junit.Assert.fail;

import org.junit.Test;

import baseClass.TestTemplate;

public class addProjectTest extends TestTemplate {
	
	public void addProject_A()
	{
		String currentUserName = "admin",
				projectLeaderId = "FL",
				startDate = "05-05-2019",
				endDate = "06-05-2019",
				description = "This is a test project";
		
		if(!addProject(currentUserName, projectLeaderId, startDate, endDate, description))
			fail();
		
		
		
	}
}
