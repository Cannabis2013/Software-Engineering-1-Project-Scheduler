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
	public void userIsAvailable_A()
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
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		assertEquals("Available", availability);
	}
	
	@Test
	public void userIsPartlyAvailable_B()
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
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		assertEquals("Partly available", availability);
	}
	
	@Test
	public void userIsNotAvailable_C()
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
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		assertEquals("Not available", availability);	
	}
	
	@Test
	public void userIsPartlyOccupied_D()
	{
		currentProject.removeAllActivities();
		ActivityModel activity = new ActivityModel("Absence", "Vacation", startDate.plusDays(4), endDate.minusDays(1),"TT");
		coreApp.addAbsenceActivity(activity);
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		
		assertEquals("Partly available", availability);
	}
	
	@Test
	public void userIsFullOccupied_E()
	{
		currentProject.removeAllActivities();
		ActivityModel activity = new ActivityModel("Absence", "Vacation", startDate, endDate,"TT");
		coreApp.addAbsenceActivity(activity);
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		assertEquals("Not available", availability);
	}
	
	@Test
	public void userIsPartlyAvailable_F()
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
			eDate = eDate.minusDays(1);
		}
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		
		assertEquals("Partly available", availability);
	}
	
	@Test
	public void userIsPartlyAvailable_G()
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
					sDate.plusDays(2).format(dateFormatter), 
					eDate.minusDays(2).format(dateFormatter), 25, 
					userNames, 
					"Test");
		}
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		
		assertEquals("Partly available", availability);
	}
	
	@Test
	public void userIsFullOccupied_H()
	{
		currentProject.removeAllActivities();
		ActivityModel activity = new ActivityModel("Absence", 
				"Vacation", 
				startDate.minusDays(2), 
				endDate.plusDays(2),
				"TT");
		coreApp.addAbsenceActivity(activity);
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		assertEquals("Not available", availability);
	}
	
	@Test
	public void userIsPartlyOccupied_I()
	{
		currentProject.removeAllActivities();
		ActivityModel activity = new ActivityModel("Absence", "Vacation", startDate.plusDays(4), endDate,"TT");
		coreApp.addAbsenceActivity(activity);
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		
		assertEquals("Partly available", availability);
	}
	
	@Test
	public void userIsPartlyOccupied_J()
	{
		currentProject.removeAllActivities();
		ActivityModel activity = new ActivityModel("Absence",
				"Vacation", 
				startDate.minusDays(4), 
				endDate.minusDays(1),
				"TT");
		coreApp.addAbsenceActivity(activity);
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		
		assertEquals("Partly available", availability);
	}
	
	@Test
	public void userIsNotAvailable_K()
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
					sDate.minusDays(4).format(dateFormatter), 
					eDate.plusDays(4).format(dateFormatter), 25, 
					userNames, 
					"Test");
		}
		
		String availability = coreApp.userAvailability("TT", startDate, endDate);
		assertEquals("Not available", availability);	
	}
	
}
