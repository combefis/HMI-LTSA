// Transition.java

package be.combefis.hmiltsa.models;

/**
 * Transition of an HMI-LTS
 * 
 * @author Sébastien Combéfis
 * @version February 28, 2015
 */
public final class Transition
{
	// Instance variables
	private final Action action;
	
	/**
	 * Creates a new transition
	 * 
	 * @pre "action" != null
	 * @post An instance of this is created, representing a transition with the specified "action"
	 */
	public Transition (Action action)
	{
		this.action = action;
	}
	
	/**
	 * Gets the action of the transition
	 * 
	 * @pre -
	 * @post The returned value contains the action of this transition
	 */
	public Action getAction()
	{
		return action;
	}
	
	@Override
	public String toString()
	{
		return action.toString();
	}
	
	@Override
	public boolean equals (Object o)
	{
		if (o instanceof Transition)
		{
			Transition t = (Transition) o;
			return action.equals (t.action);
		}
		return false;
	}
}