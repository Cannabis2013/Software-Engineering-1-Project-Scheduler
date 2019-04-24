package userAvailabilityTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import baseClass.TestTemplate;
import models.ProjectModel;

public class UserAvailable extends TestTemplate {
	
	private String projectName = "Project TEST", activityName = "GUI Test", projectLeaderId = "FL";
	private List<String> userNames;
	private int numberOfActivities;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate startDate, endDate;
	
	public UserAvailable() {
		numberOfActivities = 20;
		startDate = LocalDate.parse("05-05-2019",dateFormatter);
		endDate = LocalDate.parse("05-06-2019",dateFormatter);
		
		if(!addProject("admin", 
				projectName, 
				projectLeaderId, 
				startDate.format(dateFormatter), 
				endDate.format(dateFormatter), 
				"Test project"))
			fail();
	}
	
	
	public String isTTNotAvailable()
	{
		numberOfActivities = 20;
		LocalDate sDate = startDate, eDate = endDate;
		userNames = Arrays.asList("BB", "TT", "JW");
		int activityIndex = 0;
		ProjectModel project = coreApp.project("Project TEST");
		
		
		String aName = String.format(activityName + "(%d)", activityIndex);
		for (int i = 0; i < numberOfActivities; i++) {
			addActivity(projectLeaderId, 
					aName, 
					project, 
					sDate.format(dateFormatter), 
					eDate.format(dateFormatter), 25, 
					userNames, 
					"Test");
		}
		
		return coreApp.userAvailability("TT", startDate, endDate);
	}
	
	public void isTTPartlyAvailable()
	{
		
		LocalDate sDate = startDate, eDate = endDate;
		userNames = Arrays.asList("BB", "TT", "JW");
		int activityIndex = 0;
		ProjectModel project = coreApp.project("Project TEST");
		
		
		String aName = String.format(activityName + "(%d)", activityIndex);
		for (int i = 0; i < numberOfActivities; i++) {
			addActivity(projectLeaderId, 
					aName, 
					project, 
					sDate.format(dateFormatter), 
					eDate.format(dateFormatter), 25, 
					userNames, 
					"Test");
			sDate = sDate.plusDays(1);
		}
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		assertEquals("Partly available", availability);
	}
	
}
