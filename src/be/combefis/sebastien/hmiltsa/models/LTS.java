// LTS.java

package be.combefis.sebastien.hmiltsa.models;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Generic Labelled Transition System (LTS)
 * - S labels the states
 * - T labels the transitions
 * 
 * @author Sébastien Combéfis
 * @version February 24, 2015
 */
public class LTS<S,T>
{
	// Instance variables
	private S initialState;
	private final Set<State<S,T>> states;
	private final Set<Transition<S,T>> transitions;
	private final Map<S,State<S,T>> statesMap;
	private final Map<T,Transition<S,T>> transitionsMap;
	private final Set<TauTransition<S,T>> taus;
	
	/**
	 * Creates a new empty LTS
	 * 
	 * @pre s != null
	 * @post A new instance of this is created, representing an empty LTS
	 */
	public LTS (S s)
	{
		initialState = s;
		states = new HashSet<State<S,T>>();
		transitions = new HashSet<Transition<S,T>>();
		statesMap = new HashMap<S,State<S,T>>();
		transitionsMap = new HashMap<T,Transition<S,T>>();
		taus = new HashSet<TauTransition<S,T>>();
	}
	
	/**
	 * Gets the initial state of the LTS
	 * 
	 * @pre -
	 * @post The returned value contains the initial state of this LTS
	 */
	public S initialState()
	{
		return initialState;
	}
	
	/**
	 * Gets the number of states of the LTS
	 * 
	 * @pre -
	 * @post The returned value contains the number of states of this LTS
	 */
	public int statesCount()
	{
		return states.size();
	}
	
	/**
	 * Gets the number of transitions of the LTS
	 * 
	 * @pre -
	 * @post The returned value contains the number of transitions of this LTS
	 */
	public int transitionsCount()
	{
		return transitions.size();
	}
	
	/**
	 * Adds a state to the LTS
	 * 
	 * @pre "s" != null
	 *      "s" is not a state of this LTS
	 * @post The specified state "s" has been added to this LTS
	 */
	public void addState (S s)
	{
		if (s == null)
		{
			throw new InvalidParameterException ("Cannot add a null state to an LTS");
		}
		if (statesMap.containsKey (s))
		{
			throw new InvalidParameterException ("The specified state (" + s + ") already belongs to this LTS");
		}
		
		State<S,T> newState = new State<S,T> (s);
		states.add (newState);
		statesMap.put (s, newState);
	}
	
	/**
	 * Adds a transition to the LTS
	 * 
	 * @pre "t", "from", "to" != null
	 *      "from" and "to" are states from this LTS
	 * @post A transition has been added to this LTS, with "from" as source state 
	 *       and "to" as destination state
	 */
	public void addTransition (T t, S from, S to)
	{
		if (t == null)
		{
			throw new InvalidParameterException ("Cannot add a null transition to an LTS");
		}
		if (! (statesMap.containsKey (from) && statesMap.containsKey (to)))
		{
			throw new InvalidParameterException ("The two specified states do not both belongs to this LTS");
		}
		
		addTransition (new Transition<S,T> (t, statesMap.get (from), statesMap.get (to)));
	}
	
	/**
	 * Adds a tau transition to the LTS
	 * 
	 * @pre "t", "from", "to" != null
	 *      "from" and "to" are states from this LTS
	 *      There is no tau transition between "from" and "to"
	 * @post A tau transition has been added to this LTS, with "from" as source state 
	 *       and "to" as destination state
	 */
	public void addTauTransition (S from, S to)
	{
		if (! (statesMap.containsKey (from) && statesMap.containsKey (to)))
		{
			throw new InvalidParameterException ("The two specified states do not both belongs to this LTS");
		}
		
		addTransition (new TauTransition<S,T> (statesMap.get (from), statesMap.get (to)));
	}
	
	/**
	 * Adds a transition to the LTS
	 * 
	 * @pre t != null
	 * @post The specified transition has been added to this LTS
	 */
	private void addTransition (Transition<S,T> t)
	{
		transitions.add (t);
		t.from.addOutTransition (t);
		t.to.addInTransition (t);
	}
	
	/**
	 * State of the LTS
	 * It stores:
	 * - an S object (the state)
	 * - a set of outgoing transitions T
	 * - a set of ingoing transitions T
	 */
	private final static class State<S,T>
	{
		// Instance variables
		private final S state;
		private final Set<Transition<S,T>> in, out;
		
		/**
		 * Creates a new state
		 * 
		 * @pre "s" != null
		 * @post An instance of this is created, representing a state with "s"
		 */
		public State (S s)
		{
			state = s;
			in = new HashSet<Transition<S,T>>();
			out = new HashSet<Transition<S,T>>();
		}
		
		/**
		 * Adds an ingoing transition
		 * 
		 * @pre t != null
		 *      The destination of "t" is this state
		 * @post The specified transition has been added to the ingoing transitions of this state
		 */
		public void addInTransition (Transition<S,T> t)
		{
			in.add (t);
		}
		
		/**
		 * Adds an outgoing transition
		 * 
		 * @pre t != null
		 *      The source of "t" is this state
		 * @post The specified transition has been added to the outgoing transitions of this state
		 */
		public void addOutTransition (Transition<S,T> t)
		{
			out.add (t);
		}
		
		@Override
		public String toString()
		{
			return state.toString();
		}
	}
	
	/**
	 * Transition of the LTS
	 * It stores:
	 * - a T object (the transition)
	 * - the source state
	 * - the destination state
	 */
	private static class Transition<S,T>
	{
		// Instance variables
		private final T transition;
		private final State<S,T> from, to;
		
		/**
		 * Creates a new transition with specified source and destination
		 * 
		 * @pre "t", "from", "to" != null
		 * @post An instance of this is created representing a transition with "t"
		 *       and specified source "from" and destination "to" states
		 */
		public Transition (T t, State<S,T> from, State<S,T> to)
		{
			transition = t;
			this.from = from;
			this.to = to;
		}
		
		/**
		 * Gets the source state of the transition
		 * 
		 * @pre -
		 * @post The returned value contains the source state of this transition
		 */
		public State<S,T> getSource()
		{
			return from;
		}
		
		/**
		 * Gets the destination state of the transition
		 * 
		 * @pre -
		 * @post The returned value contains the destination state of this transition
		 */
		public State<S,T> getDestination()
		{
			return to;
		}
		
		@Override
		public String toString()
		{
			return transition.toString();
		}
	}
	
	/**
	 * Tau transition of the LTS
	 * It stores:
	 * - a T object (the transition)
	 * - the source state
	 * - the destination state
	 */
	private static class TauTransition<S,T> extends Transition<S,T>
	{
		/**
		 * Creates a new tau transition with specified source and destination
		 * 
		 * @pre "from", "to" != null
		 * @post An instance of this is created, representing a transition with "t"
		 *       and specified source "from" and destination "to" states
		 */
		public TauTransition (State<S,T> from, State<S,T> to)
		{
			super (null, from, to);
		}
		
		@Override
		public String toString()
		{
			return "tau";
		}
	}
}