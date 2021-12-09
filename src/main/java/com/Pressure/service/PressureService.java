package com.Pressure.service;

import org.json.simple.*;
import com.Pressure.model.*;

/**
 * Public interface con metodi astratti basilari che verranno
 * implementati da altre classi (analogia con CRUD)
 * @author Giansimone&Walter
 *
 */
public interface PressureService {
	
	/**
	 * 
	 * @param city The city
	 * @return A JSONObject 
	 */
	public abstract JSONObject toJSON(City city);
	
	/**
	 * 
	 * @param city The city
	 * @return The Postman's JSONObject
	 */
	public abstract JSONObject getJSONfromPman(String city);
	
	/**
	 * 
	 * @param pressure Is the JSONObject passed
	 * @return The city
	 */
	public abstract City getPressure(JSONObject pressure);
	
	/**
	 * 
	 * @param obj The object to save
	 */
	public abstract void save(JSONObject obj);
}
