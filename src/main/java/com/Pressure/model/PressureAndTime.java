package com.Pressure.model;

import java.util.Vector;

import Utilities.DateConverter;

public class PressureAndTime extends Pressure {
	
	private Vector<String> time=new Vector<String>();
	
	public PressureAndTime() {
		super();
	}
	
	public void setTime(long time) {
		DateConverter dateConverter=new DateConverter();
		this.time.add((String) dateConverter.secondsToDate(time));
	}
	
	public Vector<String> getTimeVector(){
		return this.time;
	}

}
