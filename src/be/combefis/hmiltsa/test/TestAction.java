// TestAction.java

package be.combefis.hmiltsa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.combefis.hmiltsa.models.Action;
import be.combefis.hmiltsa.models.ActionType;

/**
 * Test class of the Action class
 * 
 * @author Sébastien Combéfis
 * @version March 3, 2015
 */
public final class TestAction
{
	// Instance variables
	private Action a0, a1, a2;
	
	@Before
	public void setUp() throws Exception
	{
		a0 = new Action ("A0", ActionType.COMMAND);
		a1 = new Action ("A1", ActionType.OBSERVATION);
		a2 = new Action ("A2", ActionType.TAU);
	}
	
	@Test
	public void testGetAction()
	{
		assertEquals (ActionType.COMMAND, a0.getType());
		assertEquals (ActionType.OBSERVATION, a1.getType());
		assertEquals (ActionType.TAU, a2.getType());
	}
}