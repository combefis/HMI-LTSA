// HMILTS.java

package be.combefis.hmiltsa.models;

/**
 * Human-Machine Interaction Labelled Transition System (HMI-LTS)
 * 
 * @author Sébastien Combéfis
 * @version February 27, 2015
 */
public class HMILTS extends LTS<State,Transition>
{
	public HMILTS (State s)
	{
		super (s);
	}
}