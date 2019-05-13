/*
 * David Tran
 */


package acceptanceTest;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "CucumberTests", 
	monochrome=true, 
	snippets = SnippetType.CAMELCASE, 
	glue = { "test"})
public class AcceptanceTest {

}
