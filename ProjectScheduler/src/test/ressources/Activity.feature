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

Scenario: Projectleader removes activity from Project CANVAS succesfully
	Given a user with username "Finn_Luger" is logged in and is project leader for Project CANVAS
	And an activity with title "GUI Test", some random date intervals and "Bent_Bjerre" assigned as active user exists.
	Then Finn_Luger looks up the activity with title "GUI Test" and removes it succesfully.
	
Scenario: A user fails to remove acitivty from Project CANVAS
	Given a user with username "Finn_Luger" is logged in and is project leader for Project CANVAS
	And he creates an activity with title "GUI Test", some random date intervals and assigns a user with username "Bent_Bjerre".
	Then "Technotonny" logs in and fails to remove the activity "GUI Test".