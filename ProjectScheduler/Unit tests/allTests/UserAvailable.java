package allTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import baseClass.TestTemplate;
import models.ActivityModel;
import models.ProjectModel;

public class UserAvailable extends TestTemplate {
	
	private String activityName = "GUI Test", projectLeaderId = "FL";
	private List<String> userNames;
	private int numberOfActivities;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private LocalDate startDate, endDate;
	private ProjectModel currentProject = null;
	
	public UserAvailable() {
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
			currentProject = coreApp.project(projectName);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void isUserAvailable()
	{
		assertEquals("Available", userIsAvailable());
		assertEquals("Partly available", userIsPartlyAvailable());
		assertEquals("Not available", userIsNotAvailable());
		assertEquals("Partly available", userIsPartlyOccupied());
		assertEquals("Not available", userIsFullOccupied());
	}
	
	public String userIsAvailable()
	{
		numberOfActivities = 15;
		LocalDate sDate = startDate, eDate = endDate;
		userNames = Arrays.asList("BB", "TT", "JW");
		int activityIndex = 0;
		
		currentProject.removeAllActivities();
		
		String aName = String.format(activityName + "(%d)", activityIndex);
		for (int i = 0; i < numberOfActivities; i++) {
			addActivity(projectLeaderId, 
					aName, 
					currentProject, 
					sDate.format(dateFormatter), 
					eDate.format(dateFormatter), 25, 
					userNames, 
					"Test");
		}
		
		return coreApp.userAvailability("TT", startDate, endDate);
	}
	
	public String userIsPartlyAvailable()
	{
		numberOfActivities = 20;
		LocalDate sDate = startDate, eDate = endDate;
		userNames = Arrays.asList("BB", "TT", "JW");
		int activityIndex = 0;
		currentProject.removeAllActivities();
		
		String aName = String.format(activityName + "(%d)", activityIndex);
		for (int i = 0; i < numberOfActivities; i++) {
			addActivity(projectLeaderId, 
					aName, 
					currentProject, 
					sDate.format(dateFormatter), 
					eDate.format(dateFormatter), 25, 
					userNames, 
					"Test");
			sDate = sDate.plusDays(1);
		}
		
		return coreApp.userAvailability("TT", startDate, endDate);
	}
	
	public String userIsNotAvailable()
	{
		numberOfActivities = 20;
		LocalDate sDate = startDate, eDate = endDate;
		userNames = Arrays.asList("BB", "TT", "JW");
		int activityIndex = 0;
		currentProject.removeAllActivities();
		
		String aName = String.format(activityName + "(%d)", activityIndex);
		for (int i = 0; i < numberOfActivities; i++) {
			addActivity(projectLeaderId, 
					aName, 
					currentProject, 
					sDate.format(dateFormatter), 
					eDate.format(dateFormatter), 25, 
					userNames, 
					"Test");
		}
		
		return coreApp.userAvailability("TT", startDate, endDate);
	}
	
	public String userIsPartlyOccupied()
	{
		currentProject.removeAllActivities();
		ActivityModel activity = new ActivityModel("Absence", "Vacation", startDate.plusDays(4), endDate.minusDays(1),"TT");
		coreApp.addAbsenceActivity(activity);
		
		return coreApp.userAvailability("TT", startDate, endDate);
	}
	
	public String userIsFullOccupied()
	{
		currentProject.removeAllActivities();
		ActivityModel activity = new ActivityModel("Absence", "Vacation", startDate, endDate,"TT");
		coreApp.addAbsenceActivity(activity);
		
		return coreApp.userAvailability("TT", startDate, endDate);
	}
}
