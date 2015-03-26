// FCCheck.java

package be.combefis.hmiltsa.algorithms;

import be.combefis.hmiltsa.models.HMILTS;

/**
 * Algorithm to check the full-control property
 * 
 * @author Sébastien Combéfis
 * @version March 8, 2015
 */
public final class FCCheck
{
	// Instance variables
	private final HMILTS system;
	
	/**
	 * Creates a new full-control check algorithm
	 * 
	 * @pre "system" != null
	 * @post An instance of this has been created, representing a full-control
	 *       algorithm to analyse the specified "system"
	 */
	public FCCheck (HMILTS system)
	{
		this.system = system;
	}
	
	/**
	 * Gets the system model of the algorithm
	 * 
	 * @pre -
	 * @post The returned value contains the system model
	 *       linked to this algorithm
	 */
	public HMILTS getSystemModel()
	{
		return system;
	}
	
	/**
	 * Tests the full-control criterion
	 * 
	 * @pre "mental" != null
	 * @post The returned value contains true if the specified "mental" model
	 *       allows full-control of the system linked to this algorithm
	 */
	public boolean isFullControl (HMILTS mental)
	{
		return false;
	}
}