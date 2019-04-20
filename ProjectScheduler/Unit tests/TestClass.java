import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Application_Facade.ApplicationCore;

public class TestClass {
	ApplicationCore coreApp = new ApplicationCore();
	@Test
	public void addProjectTest()
	{
		assertEquals(true, true);
	}
	
	@Test
	public void addActivityTest()
	{
		assertEquals(true, false);
	}
}
