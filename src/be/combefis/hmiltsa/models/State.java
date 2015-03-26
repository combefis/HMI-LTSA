// State.java

package be.combefis.hmiltsa.models;

/**
 * State of an HMI-LTS
 * 
 * @author Sébastien Combéfis
 * @version February 27, 2015
 */
public final class State
{
	// Instance variables
	private final String name;
	
	/**
	 * Creates a new state
	 * 
	 * @pre "name" != null
	 * @post An instance of this is created, representing a state with the specified "name"
	 */
	public State (String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the name of the state
	 * 
	 * @pre -
	 * @post The returned value contains the name of this state
	 */
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public boolean equals (Object o)
	{
		if (o instanceof State)
		{
			State s = (State) o;
			return name.equals (s.name);
		}
		return false;
	}
}