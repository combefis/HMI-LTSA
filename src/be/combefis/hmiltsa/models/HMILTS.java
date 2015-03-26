// HMILTS.java

package be.combefis.hmiltsa.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Human-Machine Interaction Labelled Transition System (HMI-LTS)
 * 
 * @author Sébastien Combéfis
 * @version March 5, 2015
 */
public final class HMILTS extends LTS<State,Transition>
{
	// Instance variables
	private final Set<Action> alphabet;
	
	/**
	 * Creates a new HMI-LTS with one state
	 * 
	 * @pre s != null
	 * @post A new instance of this is created, representing an empty HMI-LTS
	 */
	public HMILTS (State s)
	{
		super (s);
		
		alphabet = new HashSet<Action>();
	}

	@Override
	public void handleTransition (Transition t)
	{
		super.handleTransition (t);
		
		Action action = t.getAction();
		if (action.getType() != ActionType.TAU)
		{
			alphabet.add (action);
		}
	}
	
	/**
	 * Gets the alphabet of the HMI-LTS
	 * 
	 * @pre -
	 * @post The returned value contains the alphabet of this HMI-LTS
	 */
	public Set<Action> getAlphabet()
	{
		return Collections.unmodifiableSet (alphabet);
	}
}