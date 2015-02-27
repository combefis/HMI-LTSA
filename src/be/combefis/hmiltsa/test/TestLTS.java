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
		lts = new LTS<String, String> ("A");
	}

	@Test
	public void testInitialState()
	{
		// The initial state must be A
		assertEquals ("A", lts.initialState());
		
		// Adding a state must not change the initial state 
		lts.addState ("B");
		assertEquals ("A", lts.initialState());
	}

	@Test
	public void testStatesCount()
	{
		// There is only one state (the initial state)
		assertEquals (1, lts.statesCount());
		
		// Adding one state (now two states)
		lts.addState ("B");
		assertEquals (2, lts.statesCount());
		
		// Adding two more states (now four states)
		lts.addState ("C");
		lts.addState ("D");
		assertEquals (4, lts.statesCount());
	}

	@Test
	public void testTransitionsCount()
	{
		// There is no transition initially
		assertEquals (0, lts.transitionsCount());
		
		// Adding one transition (now one transition)
		lts.addState ("B");
		lts.addTransition ("T1", "A", "B");
		assertEquals (1, lts.transitionsCount());
		
		// Adding one tau transition (now two transitions)
		lts.addTauTransition ("T2", "A", "B");
		assertEquals (2, lts.transitionsCount());
		
		// Adding three more transitions (now five transitions)
		lts.addState ("C");
		lts.addState ("D");
		lts.addTransition ("T3", "C", "D");
		lts.addTauTransition ("T4", "A", "C");
		lts.addTauTransition ("T5", "D", "B");
		assertEquals (5, lts.transitionsCount());
	}
	
	@Test
	public void testAddState()
	{
		assertEquals (1, lts.statesCount());
		
		// Adding one state
		lts.addState ("B");
		assertEquals (2, lts.statesCount());
		
		// Adding two more states (now four states)
		lts.addState ("C");
		lts.addState ("D");
		assertEquals (4, lts.statesCount());
		
		// Adding again the initial state (still four states)
		try
		{
			lts.addState ("A");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (4, lts.statesCount());
		
		// Adding an existing state (still four states)
		try
		{
			lts.addState ("C");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (4, lts.statesCount());
	}
	
	@Test
	public void testAddTransition()
	{
		assertEquals (0, lts.transitionsCount());
		lts.addState ("B");
		lts.addState ("C");
		
		// Adding one transition
		lts.addTransition ("T1", "A", "B");
		assertEquals (1, lts.transitionsCount());
		
		// Adding one tau transition (now two transitions)
		lts.addTauTransition ("T2", "A", "C");
		assertEquals (2, lts.transitionsCount());
		
		// Adding loops (now four transitions)
		lts.addTauTransition ("T3", "A", "A");
		lts.addTransition ("T4", "A", "A");
		assertEquals (4, lts.transitionsCount());
		
		// Adding two more transitions (now six transitions)
		lts.addTransition ("T5", "C", "A");
		lts.addTransition ("T6", "C", "B");
		assertEquals (6, lts.transitionsCount());
		
		// Adding an existing transition (still six transitions)
		try
		{
			lts.addTransition ("T1", "C", "A");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (6, lts.transitionsCount());
		
		// Adding a second tau transition between the two same states (still six transitions)
		try
		{
			lts.addTauTransition ("T7", "A", "C");
			fail();
		}
		catch (IllegalArgumentException exception){}
		assertEquals (6, lts.transitionsCount());
	}
}