Feature: Projects

Scenario: Admin create project succesfully
	Given the currently logged in user is admin
	And he fills an project application with the following information: Title "Test project", start date today, end date at "20-04-2019", projectleaderid "Finn_Luger", short description "This is a test project"
	Then the project with the title "Test project" can be retrieved at the ProjectManager database.