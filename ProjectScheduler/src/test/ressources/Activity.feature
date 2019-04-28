Feature: Activities

Background: a project exists
	Given a project with name "Project CANVAS" exists
	And the projectleader is a user with username "FL"
	Then FL should be able to add an activity to project CANVAS
	And other users shouldn't


Scenario: FL is able to add a actvity to Project CANVAS
	Given the user currently logged in has username "FL"
	And is projectleader for "Project CANVAS"
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