package test;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import Application_Facade.ApplicationCore;
import cucumber.api.java.en.*;
import models.ActivityModel;
import models.ProjectModel;


public class ActivitySteps {
	
	private ApplicationCore coreApp = new ApplicationCore();
	private ActivityModel tempActivity;
	private String projectName;
	private ProjectModel currentProject = null;
	
	public ActivitySteps()
	{
		
		projectName = "Project CANVAS";
		String pLeader = "FL";
		String startDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
		String endDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now().plusDays(2));
		String shortDescription = "This is a test project";
		
		currentProject = new ProjectModel(projectName,pLeader, startDate, endDate, shortDescription);
		projectName = currentProject.projectName();
		try {
			coreApp.login("admin");
			coreApp.addProject(currentProject);
			coreApp.logut();
		} catch (Exception e) {
			fail();
		}
	}
	
	
	@Given("is projectleader for a project")
	public void is_projectleader_for_a_project() {
		assertTrue(currentProject.projectLeaderId().equals(coreApp.currentUserLoggedIn().UserName()));
	}

	@Given("the projectleader is a user with username {string}")
	public void the_projectleader_is_a_user_with_username(String string) {
	    String pLeaderId;
		try {
			pLeaderId = currentProject.projectLeaderId();
		} catch (Exception e) {
			fail();
			return;
		}
	    assertTrue(pLeaderId.equals(string));
	}

	@Then("FL should be able to add an activity to project CANVAS")
	public void finn_Luger_should_be_able_to_add_an_activity_to_project_CANVAS() {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("other users shouldn't")
	public void other_users_shouldn_t() {
	    
	}

	@Given("the user currently logged in has username {string}")
	public void the_user_currently_logged_in_has_username(String string) {
		try {
			coreApp.login(string);
		} catch (Exception e) {
			fail();
		}
	    String currentUserName = coreApp.currentUserLoggedIn().UserName();
	    assertEquals(true, currentUserName.equals(string));
	}

	@Given("is projectleader for {string}")
	public void is_projectleader_for(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    
	    String projectLeader = currentProject.projectLeaderId();
	    String currentUserName = coreApp.currentUserLoggedIn().UserName();
	    
	    assertEquals(true, projectLeader.equals(currentUserName));
	}

	@When("he creates an activity with title {string} and some random interval dates")
	public void he_creates_an_activity_with_title_and_some_random_interval_dates(String string) {
		
	    String activityId = string;
	    LocalDate startDate = LocalDate.now();
	    LocalDate enddDate = startDate.plusDays(2);
	    
	    try {
			tempActivity = new ActivityModel(activityId,startDate,enddDate,4, null, "");
		} catch (IllegalArgumentException e) {
			fail();
		}
	    
	}

	@When("assigns {string} , {string} and {string}")
	public void assigns_and(String string, String string2, String string3) {
	    
		tempActivity.assignUser(string);
		tempActivity.assignUser(string2);
		tempActivity.assignUser(string3);
	}

	@Then("he succesfully manage to add the activity to {string}")
	public void he_succesfully_manage_to_add_the_activity_to(String string) {
	    try {
			coreApp.addActivity(projectName, tempActivity);
		} catch (Exception e) {
			fail();
		}
		ActivityModel activity;
		try {
			activity = coreApp.activity(projectName, tempActivity.activityId());
			assertEquals(true, activity != null);
		} catch (Exception e) {
			
		}
	}
	
	@Then("he unsuccesfully manage to add the activity to {string}")
	public void he_unsuccesfully_manage_to_add_the_activity_to(String string) {
		try {
			coreApp.addActivity(projectName, tempActivity);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
		
	@Given("a user with username {string} is logged in and is project leader for Project CANVAS")
	public void a_user_with_username_is_logged_in_and_is_project_leader_for_Project_CANVAS(String string) {
		try {
			coreApp.login(string);
		} catch (Exception e1) {
			fail();
		}
	    try {
			String currentUserLoggedIn = coreApp.currentUserLoggedIn().UserName();
			assertEquals(string, currentUserLoggedIn);
			String projectLeaderId = currentProject.projectLeaderId();
			
			assertEquals(string, projectLeaderId);
		} catch (NullPointerException e) {
			fail();
		}
	}

	@Given("an activity with title {string}, some random date intervals and {string} assigned as active user exists.")
	public void an_activity_with_title_some_random_date_intervals_and_assigned_as_active_user_exists(String string, String string2) {
		
		String activityName = string;
	    LocalDate startDate = TestUnit.DateFromString("05-05-2019");
	    LocalDate endDate = TestUnit.DateFromString("19-05-2019");
	    
	    
	    ActivityModel activity = new ActivityModel(activityName, startDate, endDate);
	    activity.assignUser(string2);
	    
	    try {
			coreApp.addActivity(currentProject.projectName(), activity);
			assertEquals(true, coreApp.activity(projectName, string) != null);
		} catch (Exception e) {
			fail();
		}
	    
	}
	
	@Then("FL looks up the activity with title {string} and removes it succesfully.")
	public void fl_looks_up_the_activity_with_title_and_removes_it_succesfully(String string) {
		try {
			coreApp.removeActivity(projectName, string);
		} catch (Exception e) {
			fail();
		}
		try {
			coreApp.activity(projectName, string);
			fail();
		} catch (Exception e) {
		}
		
	}
	
	@Given("he creates an activity with title {string}, some random date intervals and assigns a user with username {string}.")
	public void he_creates_an_activity_with_title_some_random_date_intervals_and_assigns_a_user_with_username(String string, String string2) {
		String activityName = string;
	    LocalDate startDate = TestUnit.DateFromString("05-05-2019");
	    LocalDate endDate = TestUnit.DateFromString("19-05-2019");
	    
	    
	    ActivityModel activity = new ActivityModel(activityName, startDate, endDate);
	    activity.assignUser(string2);
	    
	    try {
			coreApp.addActivity(currentProject.projectName(), activity);
			assertEquals(true, coreApp.activity(projectName, string) != null);
		} catch (Exception e) {
			fail();
		}
	    
	}
	
	@Then("{string} logs in and fails to remove the activity {string}.")
	public void logs_in_and_fails_to_remove_the_activity(String string, String string2) {
		try {
			coreApp.login(string);
		} catch (Exception e1) {
			fail();
		}
		try {
			coreApp.removeActivity(projectName, string2);
			fail();
		} catch (Exception e) {
			
		}
		
	}
	@Given("a user with username {string} is logged in and is not projectleader for Project CANVAS")
	public void aUserWithUsernameIsLoggedInAndIsNotProjectleaderForProjectCANVAS(String string) {
		 try{
	    	coreApp.login(string);
	    } catch (Exception e) {
	    	fail();
	    }
	    
	    try {
			 String currentUserLoggedIn = coreApp.currentUserLoggedIn().UserName();
			 assertEquals(string, currentUserLoggedIn);
			 String projectLeaderId = currentProject.projectLeaderId();
			
			 assertNotSame(string, projectLeaderId);
			} catch (NullPointerException e) {
				fail();
			}
	    
	}

	@Given("an activity {string} exists")
	public void anActivityExists(String string) {
		LocalDate startDate = TestUnit.DateFromString("05-05-2019");
	    LocalDate endDate = TestUnit.DateFromString("19-05-2019");
		try {
			ActivityModel activity = new ActivityModel(string,startDate,endDate);
			assertTrue(activity != null);
		} catch(Exception e) {
			fail();
		}
	    
	}
	
	@Given("an activity {string} exists and {string} is projectleader for its parent project")
	public void anActivityExistsAndIsProjectleaderForItsParentProject(String string, String string2) {
	    try {
			coreApp.login(string2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> assignedUsers = Arrays.asList("TT", "NE");
	    ActivityModel activity = new ActivityModel(string,
	    		LocalDate.now(),
	    		LocalDate.now().plusDays(2),
	    		10,
	    		assignedUsers, "");
	    
	    try {
			coreApp.addActivity(projectName, activity);
		} catch (Exception e1) {
			fail();
		}
	    
	    try {
			coreApp.activity(projectName, string);
			tempActivity = activity;
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

	@Given("that {string} is not assigned to {string} and is available")
	public void thatIsNotAssignedToAndIsAvailable(String string, String string2) {
	   //Add availability check
		try {
			assertEquals(false, coreApp.activity(projectName, string2).isUserAssignedToActivity(string));
	    // doesn't work currently due to lack of privileges	
	    } catch (Exception e) {
	    	fail();
	    }
	}

	@Then("{string} fails to assign {string} to {string}")
	public void failsToAssignTo(String string, String string2, String string3) {
	    try {
			coreApp.login(string);
		} catch (Exception e1) {
			fail();
		}
		try {
			coreApp.activity(projectName, tempActivity.activityId());
			fail();
		} catch (Exception e) {
			
		}
	}
	@Given("a user with {string} is logged in and is projectleader for Project CANVAS")
	public void aUserWithIsLoggedInAndIsProjectleaderForProjectCANVAS(String string) {
		try{
	    	coreApp.login(string);
	    } catch (Exception e) {
	    	fail();
	    }
	    
	    try {
			 String currentUserLoggedIn = coreApp.currentUserLoggedIn().UserName();
			 assertEquals(string, currentUserLoggedIn);
			 String projectLeaderId = currentProject.projectLeaderId();
			
			 assertEquals(string, projectLeaderId);
			} catch (NullPointerException e) {
				fail();
			}
	}

	@Then("he can check the availability status of employees")
	public void heCanCheckTheAvailabilityStatusOfEmployees() {
		LocalDate startDate = TestUnit.DateFromString("05-05-2019");
	    LocalDate endDate = TestUnit.DateFromString("19-05-2019");
		try {
	    	coreApp.allUserAvailability(startDate,endDate);
	    } catch (Exception e) {
	    	fail();
	    }
	}
	
	@Given("that a user {string} is logged in and is projectleader")
	public void thatAUserIsLoggedInAndIsProjectleader(String string) {
		try{
	    	coreApp.login(string);
	    } catch (Exception e) {
	    	fail();
	    }
	    
	    try {
			 String currentUserLoggedIn = coreApp.currentUserLoggedIn().UserName();
			 assertEquals(string, currentUserLoggedIn);
			 String projectLeaderId = currentProject.projectLeaderId();
			
			 assertEquals(string, projectLeaderId);
			} catch (NullPointerException e) {
				fail();
			}

	}

	@Given("that a user {string} is already assigned to twenty activities in the timespan")
	public void thatAUserIsAlreadyAssignedToTwentyActivitiesInTheTimespan(String string) {
		try {
			LocalDate startDate = TestUnit.DateFromString("05-05-2019");
			LocalDate endDate = TestUnit.DateFromString("05-06-2019");
			for (int i = 1; i<21;i++) {
				String activityName = i + "test";
				
				ActivityModel activity = new ActivityModel(activityName, startDate, endDate);
				activity.assignUser(string);
	    
	    
				coreApp.addActivity(currentProject.projectName(), activity);
				
				
			}
			assertEquals("Not available",coreApp.userAvailability(string,startDate,endDate));
		} catch (Exception e) {
			fail();
		}
			
			
		}
	

	@Then("the projectleader {string} fails to assign {string} to activity {string}")
	public void theProjectleaderFailsToAssignToActivity(String string, String string2, String string3) {
	   try {
		   	String activityName = "test21";
			LocalDate startDate = TestUnit.DateFromString("05-05-2019");
			LocalDate endDate = TestUnit.DateFromString("19-05-2019");
   
			ActivityModel activity = new ActivityModel(activityName, startDate, endDate);
	
			coreApp.addActivity(currentProject.projectName(), activity);
			if(coreApp.userAvailability(string2, startDate, endDate).equals("Not available"))
					activity.assignUser(string2);
			else
				fail();
	   } catch(Exception e) {
	   }
	}
}
