$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/ressources/Activity.feature");
formatter.feature({
  "name": "Activities",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "a project exists",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "a project with name \"Project CANVAS\" exists",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the projectleader is a user with username \"FL\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "FL should be able to add an activity to project CANVAS",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "other users shouldn\u0027t",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "FL is able to add a actvity to Project CANVAS",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "the user currently logged in has username \"FL\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "is projectleader for \"Project CANVAS\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he creates an activity with title \"GUI Test\" and some random interval dates",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "assigns \"BB\" , \"JW\" and \"TT\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he succesfully manage to add the activity to \"Project CANVAS\"",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "a project exists",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "a project with name \"Project CANVAS\" exists",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the projectleader is a user with username \"FL\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "FL should be able to add an activity to project CANVAS",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "other users shouldn\u0027t",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "JW is not projectleader, and therefore unable to add an activity to Project CANVAS",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "the user currently logged in has username \"JW\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he creates an activity with title \"GUI Test\" and some random interval dates",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "assigns \"BB\" , \"FL\" and \"TT\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he unsuccesfully manage to add the activity to \"Project CANVAS\"",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "a project exists",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "a project with name \"Project CANVAS\" exists",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the projectleader is a user with username \"FL\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "FL should be able to add an activity to project CANVAS",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "other users shouldn\u0027t",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Projectleader removes activity from Project CANVAS succesfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user with username \"FL\" is logged in and is project leader for Project CANVAS",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "an activity with title \"GUI Test\", some random date intervals and \"BB\" assigned as active user exists.",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "FL looks up the activity with title \"GUI Test\" and removes it succesfully.",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "a project exists",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "a project with name \"Project CANVAS\" exists",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the projectleader is a user with username \"FL\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "FL should be able to add an activity to project CANVAS",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "other users shouldn\u0027t",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "A user fails to remove acitivty from Project CANVAS",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user with username \"FL\" is logged in and is project leader for Project CANVAS",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he creates an activity with title \"GUI Test\", some random date intervals and assigns a user with username \"BB\".",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "\"TT\" logs in and fails to remove the activity \"GUI Test\".",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.uri("src/test/ressources/Projects.feature");
formatter.feature({
  "name": "Projects",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Admin create project succesfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "the currently logged in user is admin",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he fills an project application with the following information: Title \"Test project\", start date today, end date at \"20-05-2019\", projectleaderid \"FL\" , short description \"This is a test project\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the project with the title \"Test project\" can be retrieved at the ProjectManager database.",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "User creates project and unsuccesfully adds it to the database",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "the user with the username \"FL\" is logged in",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he fills an project application with the following information: Title \"Test project\", start date today, end date at \"20-05-2019\", projectleaderid \"FL\" , short description \"This is a test project\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he fails to add the project to the database.",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Admin removes project succesfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a project exists with the name \"Project CANVAS\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the user currently logged in is admin",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he performs an action that results in removing the project from the system.",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "User removes project unsuccesfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a project exists with the name \"Project CANVAS\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the user with username \"FL\" enters his username",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he wants to remove the project with the title \"Project Canvas\".",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he recieves a message that reads \"Admin privilliges required\".",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.uri("src/test/ressources/RegsterHour.feature");
formatter.feature({
  "name": "Register work hour feature",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "A project and activity exists",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "a project with name \"Project CANVAS\" exists with a user with username \"FL\" as projectleader",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "an activity extists with name \"GUI Test\" with some random chosen date intervals chosen",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "with the following users assigned: \"BB\" , \"JW\" and \"TT\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "User register hour succesfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user with userName \"JW\" is logged in",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he fills an application with the title \"Worked at menus\" for registering 4 hours to the activity \"GUI Test\"",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the registered hour object is stored in the system.",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "A project and activity exists",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "a project with name \"Project CANVAS\" exists with a user with username \"FL\" as projectleader",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "an activity extists with name \"GUI Test\" with some random chosen date intervals chosen",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "with the following users assigned: \"BB\" , \"JW\" and \"TT\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "User register hour unsuccesfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user with userName \"NE\" is logged in",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he fills an application with the title \"Worked at menus\" for registering 4 hours to the activity \"GUI Test\"",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the registered hour is not stored in the system.",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.uri("src/test/ressources/User.feature");
formatter.feature({
  "name": "Users",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "User log in succesfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user enters username \"admin\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he logs in succesfully",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "user log in with wrong credential and fails to login",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user enters username \"JP\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "he fails to log in",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});