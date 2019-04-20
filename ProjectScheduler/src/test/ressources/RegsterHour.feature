Feature: Register work hour feature

Background: A project and activity exists
	Given a project with name "Project CANVAS" exists with a user with username "FL" as projectleader
	And an activity with name "GUI Test" with some random chosen date intervals chosen
	And with the following users assigned: "BB" , "JW" and "TT"

Scenario: scenario description
    Given a user with userName "JW" is logged in
    When he fills an application with the title "Worked at menus" for registering 4 hours to the activity "GUI Test"
    Then the registered hour object is stored in the system.