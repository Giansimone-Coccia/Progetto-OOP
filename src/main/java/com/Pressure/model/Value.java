package com.Pressure.model;

/**
 * Interfaccia che viene implementata dalla classe Pressure
 * @author Giansimone&Walter
 *
 */
public interface Value {
	
	/**
	 * This method is used to set the value passed as a parameter
	 * @param value The value to set
	 */
	public void setValue(long value);
	
	/**
	 * 
	 * @return The maximum value
	 */
	public long getValue_max();
	
	/**
	 * 
	 * @return The minimum value
	 */
	public long getValue_min();
	
	/**
	 * 
	 * @return The medium value
	 */
	public double getValue_med();
	
	/**
	 * 
	 * @return The difference between two values
	 */
	public long getValue_diff();
}
