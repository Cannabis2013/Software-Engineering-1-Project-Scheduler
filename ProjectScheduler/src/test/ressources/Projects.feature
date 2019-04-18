Feature: Projects

Scenario: Admin create project succesfully
	Given the currently logged in user is admin
	And he fills an project application with the following information: Title "Test project", start date today, end date at "20-04-2019", projectleaderid "Finn_Luger", short description "This is a test project"
	Then the project with the title "Test project" can be retrieved at the ProjectManager database.

Scenario: User creates project and unsuccesfully adds it to the database
    Given the user with the username "Finn_Luger" is logged in
    And he fills an project application with the following information: Title "Test project", start date today, end date at "20-04-2019", projectleaderid "Finn_Luger", short description "This is a test project"
    Then he fails to add the project to the database.
    
Scenario: Admin removes project succesfully
	Given a project exists with the name "Project CANVAS"
	And the user currently logged in is admin
	Then he performs an action that results in removing the project from the system.