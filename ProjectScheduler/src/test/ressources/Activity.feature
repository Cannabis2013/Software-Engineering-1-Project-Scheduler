Feature: Activities

Background: a project exists
	Given a project with name "Project CANVAS" exists
	And the projectleader is a user with username "Finn_Luger"
	Then Finn Luger should be able to add an activity to project CANVAS
	And other users shouldn't


Scenario: Finn Luger is able to add a actvity to Project CANVAS
	Given the user currently logged in has username "Finn_Luger"
	And is projectleader for "Project CANVAS"
	When he creates an activity with title "GUI Test" and some random interval dates
	And assigns "Bent_Bjerre" , "Jens_Werner2019" and "Technotonny"
	Then he succesfully manage to add the activity to "Project CANVAS"
	
Scenario: Jens_Werner2019 is unable to add a actvity to Project CANVAS
	Given the user currently logged in has username "Jens_Werner2019"
	When he creates an activity with title "GUI Test" and some random interval dates
	And assigns "Bent_Bjerre" , "Finn_Luger" and "Technotonny"
	Then he unsuccesfully manage to add the activity to "Project CANVAS"