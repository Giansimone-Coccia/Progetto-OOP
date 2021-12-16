package com.Pressure.model;

import java.util.Vector;

/**
 * Classe per associare a delle pressioni reigistrate in
 * certi istante di tempi le loro rispettive date di registrazione. 
 * @author Giansimone&Walter
 */

import Utilities.DateConverter;

public class PressureAndTime extends Pressure {
	
	/**
	 * Vettore che racchiude le date delle pressioni registrate
	 */
	private Vector<String> time=new Vector<String>();
	
	/**
	 * Costruttore di default
	 */
	public PressureAndTime() {
		super();
	}
	
	/**
	 * Setter del vettore time
	 * @param time
	 */
	
	public void setTime(long time) {
		DateConverter dateConverter=new DateConverter();
		this.time.add((String) dateConverter.secondsToDate(time));
	}
	
	/**
	 * Getter del vettore time
	 * @return
	 */
	public Vector<String> getTimeVector(){
		return this.time;
	}

}
