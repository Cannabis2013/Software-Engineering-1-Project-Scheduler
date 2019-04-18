Feature: Users

Scenario: User log in succesfully
	Given a user enters username "admin"
	Then he logs in succesfully

Scenario: user log in with wrong credential and fails to login
	Given a user enters username "Jens_Poul"
	Then he fails to log in