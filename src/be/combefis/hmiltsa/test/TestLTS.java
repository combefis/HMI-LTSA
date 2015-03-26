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
public final class TestLTS
{
	// Instance variables
	private LTS<String,String> lts;
	
	@Before
	public void setUp() throws Exception
	{
		lts = new LTS<String, String> ("S0");
	}

	@Test
	public void testInitialState()
	{
		// The initial state must be A
		assertEquals ("S0", lts.initialState());
		
		// Adding a state must not change the initial state 
		lts.addState ("S1");
		assertEquals ("S0", lts.initialState());
	}

	@Test
	public void testStatesCount()
	{
		// There is only one state (the initial state)
		assertEquals (1, lts.statesCount());
		
		// Adding one state (now two states)
		lts.addState ("S1");
		assertEquals (2, lts.statesCount());
		
		// Adding two more states (now four states)
		lts.addState ("S2");
		lts.addState ("S3");
		assertEquals (4, lts.statesCount());
	}

	@Test
	public void testTransitionsCount()
	{
		// There is no transition initially
		assertEquals (0, lts.transitionsCount());
		
		// Adding one transition (now one transition)
		lts.addState ("S1");
		lts.addTransition ("T0", "S0", "S1");
		assertEquals (1, lts.transitionsCount());
		
		// Adding one tau transition (now two transitions)
		lts.addTauTransition ("T1", "S0", "S1");
		assertEquals (2, lts.transitionsCount());
		
		// Adding three more transitions (now five transitions)
		lts.addState ("S2");
		lts.addState ("S3");
		lts.addTransition ("T2", "S2", "S3");
		lts.addTauTransition ("T3", "S0", "S2");
		lts.addTauTransition ("T4", "S3", "S1");
		assertEquals (5, lts.transitionsCount());
	}
	
	@Test
	public void testAddState()
	{
		assertEquals (1, lts.statesCount());
		
		// Adding one state
		lts.addState ("S1");
		assertEquals (2, lts.statesCount());
		
		// Adding two more states (now four states)
		lts.addState ("S2");
		lts.addState ("S3");
		assertEquals (4, lts.statesCount());
		
		// Adding again the initial state (still four states)
		try
		{
			lts.addState ("S0");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (4, lts.statesCount());
		
		// Adding an existing state (still four states)
		try
		{
			lts.addState ("S2");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (4, lts.statesCount());
	}
	
	@Test
	public void testAddTransition()
	{
		assertEquals (0, lts.transitionsCount());
		lts.addState ("S1");
		lts.addState ("S2");
		
		// Adding one transition
		lts.addTransition ("T0", "S0", "S1");
		assertEquals (1, lts.transitionsCount());
		
		// Adding one loop (now two transitions)
		lts.addTransition ("T1", "S0", "S0");
		assertEquals (2, lts.transitionsCount());
		
		// Adding three more transitions (now five transitions)
		lts.addTransition ("T2", "S2", "S0");
		lts.addTransition ("T3", "S2", "S1");
		lts.addTransition ("T4", "S0", "S1");
		assertEquals (5, lts.transitionsCount());
		
		// Adding an existing transition (still five transitions)
		try
		{
			lts.addTransition ("T0", "S2", "S0");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (5, lts.transitionsCount());
	}
	
	@Test
	public void testAddTauTransition()
	{
		assertEquals (0, lts.transitionsCount());
		lts.addState ("S1");
		lts.addState ("S2");
		
		// Adding one tau transition
		lts.addTauTransition ("T0", "S0", "S2");
		assertEquals (1, lts.transitionsCount());
		assertTrue (lts.hasTauTransition ("S0", "S2"));
		
		// Adding one loop (now two transitions)
		lts.addTauTransition ("T1", "S0", "S0");
		assertEquals (2, lts.transitionsCount());
		assertTrue (lts.hasTauTransition ("S0", "S0"));
		
		// Adding two more tau transitions (now four transitions)
		lts.addTauTransition ("T2", "S2", "S0");
		lts.addTauTransition ("T3", "S2", "S1");
		assertEquals (4, lts.transitionsCount());
		assertTrue (lts.hasTauTransition ("S2", "S0"));
		assertTrue (lts.hasTauTransition ("S2", "S1"));
		
		// Adding a second tau transition between the two same states (still four transitions)
		try
		{
			lts.addTauTransition ("T4", "S0", "S2");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (4, lts.transitionsCount());
	}
	
	@Test
	public void testHasTauTransition()
	{
		assertFalse (lts.hasTauTransition ("S0", "S0"));
		lts.addState ("S1");
		lts.addState ("S2");
		
		assertFalse (lts.hasTauTransition ("S0", "S1"));
		assertFalse (lts.hasTauTransition ("S0", "S2"));
		assertFalse (lts.hasTauTransition ("S1", "S0"));
		assertFalse (lts.hasTauTransition ("S1", "S1"));
		assertFalse (lts.hasTauTransition ("S1", "S2"));
		assertFalse (lts.hasTauTransition ("S2", "S0"));
		assertFalse (lts.hasTauTransition ("S2", "S1"));
		assertFalse (lts.hasTauTransition ("S2", "S2"));
		
		lts.addTauTransition ("T0", "S0", "S0");
		assertTrue (lts.hasTauTransition ("S0", "S0"));
		
		lts.addTransition ("T1", "S0", "S1");
		assertFalse (lts.hasTauTransition ("S0", "S1"));
		
		lts.addTauTransition ("T2", "S0", "S1");
		assertTrue (lts.hasTauTransition ("S0", "S1"));
	}
}