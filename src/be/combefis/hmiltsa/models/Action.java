// Action.java

package be.combefis.hmiltsa.models;

/**
 * Action that is used to label the transitions of an HMI-LTS
 * 
 *  @author Sébastien Combéfis
 *  @version February 28, 2015
 */
public final class Action
{
	// Instance variables
	private final String name;
	private final ActionType type;
	
	/**
	 * Creates a new action
	 * 
	 * @pre "name", "type" != null
	 * @post An instance of this is created with the specified "name" and "type"
	 */
	public Action (String name, ActionType type)
	{
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Gets the name of the action
	 * 
	 * @pre -
	 * @post The returned value contains the name of this action
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the type of the action
	 * 
	 * @pre -
	 * @post The returned value contains the type of this action
	 */
	public ActionType getType()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public boolean equals (Object o)
	{
		if (o instanceof Action)
		{
			Action a = (Action)	o;
			return name.equals (a.name);
		}
		return false;
	}
}