package com.Pressure.exceptions;

/**
 * Classe che lancia l'eccezione personalizzata riguardante il formato della data
 * @author Giansimone&Walter
 *
 */
public class DateFormatException extends Exception{
	
	public DateFormatException() {
		super();
	}
	
	public DateFormatException(String msg) {
		super(msg);
	}

}
