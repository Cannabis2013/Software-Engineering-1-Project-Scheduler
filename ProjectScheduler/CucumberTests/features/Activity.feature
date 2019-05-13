


Feature: Activities

Background: a project exists
	Given a project exists
	And the projectleader is a user with username "FL"
	Then FL should be able to add an activity to project CANVAS
	And other users shouldn't

Scenario: FL is able to add a actvity to Project CANVAS
	Given the user currently logged in has username "FL"
	And is projectleader for a project
	When he creates an activity with title "GUI Test" and some random interval dates
	And assigns "BB" , "JW" and "TT"
	Then he succesfully manage to add the activity to "Project CANVAS"
	
Scenario: JW is not projectleader, and therefore unable to add an activity to Project CANVAS
	Given the user currently logged in has username "JW"
	When he creates an activity with title "GUI Test" and some random interval dates
	And assigns "BB" , "FL" and "TT"
	Then he unsuccesfully manage to add the activity to "Project CANVAS"

Scenario: Projectleader removes activity from Project CANVAS succesfully
	Given a user with username "FL" is logged in and is project leader for Project CANVAS
	And an activity with title "GUI Test", some random date intervals and "BB" assigned as active user exists.
	Then FL looks up the activity with title "GUI Test" and removes it succesfully.
	
Scenario: A user fails to remove acitivty from Project CANVAS
	Given a user with username "FL" is logged in and is project leader for Project CANVAS
	And he creates an activity with title "GUI Test", some random date intervals and assigns a user with username "BB".
	Then "TT" logs in and fails to remove the activity "GUI Test".

Scenario: An unauthorized user fails to assign employee
	Given a user with username "HT" is logged in and is not projectleader for Project CANVAS
	And an activity "GUI Test" exists and "FL" is projectleader for its parent project
	And that "PB" is not assigned to "GUI Test" and is available
	Then "HT" fails to assign "PB" to "GUI Test"

Scenario: A projectleader reviews available employees
	Given a user with "FL" is logged in and is projectleader for Project CANVAS
	Then he can check the availability status of employees

Scenario: A projectleader tries to assign an employee to more than twenty activities
	Given that a user "FL" is logged in and is projectleader
	And an activity "GUI Test" exists
	And that a user "MH" is already assigned to twenty activities in the timespan
	Then the projectleader "FL" fails to assign "MH" to activity "GUI Test"
	

