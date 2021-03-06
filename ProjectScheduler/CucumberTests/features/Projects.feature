Feature: Projects

Scenario: Admin create project succesfully
	Given the currently logged in user is admin
	And he fills an project application with the following information: start date today, end date five days later, projectleaderid "FL" , short description "This is a test project"
	Then the project can be retrieved at the ProjectManager database.

Scenario: User creates project and unsuccesfully adds it to the database
    Given the user with the username "FL" is logged in
    And he fills an project application with the following information: start date today, end date five days later, projectleaderid "FL" , short description "This is a test project"
    Then he fails to add the project to the database.
    
Scenario: Admin removes project succesfully
	Given a project exists
	And the user currently logged in is admin
	Then he performs an action that results in removing the project from the system.
	
Scenario: User removes project unsuccesfully
	Given a project exists
	And the user with username "FL" enters his username
	And he wants to remove the project with the title "Project Canvas".
	Then he recieves a message that reads "Admin privilliges required".