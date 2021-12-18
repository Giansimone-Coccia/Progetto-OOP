package com.Pressure.service;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Collection;
import java.util.Vector;

import org.json.simple.*;
import com.Pressure.model.*;

import Exception.DateFormatException;
import Exception.VectorNull;

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
	public JSONObject toJSON(City city);
	
	/**
	 * 
	 * @param city The city
	 * @return The Postman's JSONObject
	 * @throws IOException, Exception
	 */
	public JSONObject getJSONfromPman(String city);
	
	/**
	 * 
	 * @param pressure Is the JSONObject passed
	 * @return The city
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public City getWeather(JSONObject pressure);
	
	/**
	 * 
	 * @return All the pressures misured in that city
	 * @throws VectorNull 
	 */
	public Vector<Long> getAllPressure() throws VectorNull;
	
	/**
	 * 
	 * @param cityName The city's name
	 * @throws IOException
	 */
	public void saveData(String cityName);
	
	/**
	 * 
	 * @param fileName The file's name to read
	 * @throws DateFormatException 
	 * @throws FileNotFoundException, IOExcepton, Exception
	 */
	public Pressure readJSON(String fileName,String init,String last) throws DateFormatException;

}
