package allTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import baseClass.TestTemplate;

public class ActivityTests extends TestTemplate {
	
	public ActivityTests()
	{
		String userName = "admin",
				projectName = "Project CANVAS",
				projectLeaderId = "FL",
				startDate = "23-05-2019",
				endDate = "29-05-2019";
		
		addProject(userName, projectName, projectLeaderId, startDate, endDate, "");
	}
}
