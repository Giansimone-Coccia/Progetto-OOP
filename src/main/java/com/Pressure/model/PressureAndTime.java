package com.Pressure.model;

import java.util.Vector;

import com.Pressure.utilities.DateConverter;

public class PressureAndTime extends Pressure {
	
	/**
	 * Vector that encloses the dates of the recorded pressures
	 */
	private Vector<String> time=new Vector<String>();
	
	/**
	 * Default constructor
	 */
	public PressureAndTime() {
		super();
	}
	
	/**
	 * Setter of the Vector time
	 * @param time
	 */
	
	public void setTime(long time) {
		DateConverter dateConverter=new DateConverter();
		this.time.add((String) dateConverter.secondsToDate(time));
	}
	
	/**
	 * Getter of the Vector time
	 * @return
	 */
	public Vector<String> getTimeVector(){
		return this.time;
	}

}
