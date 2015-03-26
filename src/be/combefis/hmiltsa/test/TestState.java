// TestState.java

package be.combefis.hmiltsa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.combefis.hmiltsa.models.State;

/**
 * Test class of the State class
 * 
 * @author Sébastien Combéfis
 * @version March 2, 2015
 */
public final class TestState
{
	// Instance variables
	private State state;
	
	@Before
	public void setUp() throws Exception
	{
		state = new State ("S0");
	}
	
	@Test
	public void testGetName()
	{
		assertEquals ("S0", state.getName());
	}
}