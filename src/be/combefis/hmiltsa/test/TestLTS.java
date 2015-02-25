// TestLTS.java

package be.combefis.hmiltsa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.combefis.hmiltsa.models.LTS;

/**
 * Test class of the LTS class
 * 
 * @author Sébastien Combéfis
 * @version February 25, 2015
 */
public class TestLTS
{
	private LTS<String,String> lts;
	
	@Before
	public void setUp() throws Exception
	{
		lts = new LTS<String, String> ("Start");
	}

	@Test
	public void testInitialState()
	{
		assertEquals ("Start", lts.initialState());
	}

	@Test
	public void testStatesCount()
	{
		assertEquals (1, lts.statesCount());
		
		lts.addState ("1");
		assertEquals (2, lts.statesCount());
		
		lts.addState ("3");
		lts.addState ("4");
		assertEquals (4, lts.statesCount());
	}

	@Test
	public void testTransitionsCount()
	{
		assertEquals (0, lts.transitionsCount());
	}
}