// Transition.java

package be.combefis.hmiltsa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.combefis.hmiltsa.models.Action;
import be.combefis.hmiltsa.models.ActionType;
import be.combefis.hmiltsa.models.Transition;

/**
 * Test class of the Transition class
 * 
 * @author Sébastien Combéfis
 * @version March 2, 2015
 */
public final class TestTransition
{
	// Instance variables
	private Transition transition;
	private Action action;
	
	@Before
	public void setUp() throws Exception
	{
		action = new Action ("A0", ActionType.COMMAND);
		transition = new Transition (action);
	}
	
	@Test
	public void testGetAction()
	{
		assertEquals (action, transition.getAction());
	}
}