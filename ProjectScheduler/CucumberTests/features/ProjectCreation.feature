/*
 * Martin Hansen
 */


Feature: Project Creation
Scenario: An unauthorized user attempts to create a project.
	Given that a non-administrator user named "HT" is logged in
	Then he fails to create a project called "Test"