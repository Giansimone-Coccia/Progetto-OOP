package com.Pressure.service;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Collection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.Pressure.model.City;
import com.Pressure.model.Pressure;
import Exception.VectorNull;

import Exception.*;

/**
 * Questa classe implementa i metodi astratti di PressureService
 * @author Giansimone&Walter
 *
 */
@Service
public class PressureServiceImpl implements PressureService{

	//Stringa che contiene l'API key da utilizzare nella chiamata
	private String apiKey = "10b2f29f8e21bd179b27ff96923bca4a";
	//Stringa che contiene l'URL che verra richiamato
	private String url = "api.openweathermap.org/data/2.5/weather?q=";
	Pressure p = new Pressure();
	
	/**
	 * This method gets the various JSON object from the JSON file returned by PostMan
	 */
	@Override
	public JSONObject getJSONfromPman (String city) {
		JSONObject description = null;

		try {
			URLConnection openConnection = new URL(url + city + "&appid=" + apiKey).openConnection();
			InputStream input = openConnection.getInputStream();

			String data = "";
			String line = "";

			try {
				InputStreamReader reader = new InputStreamReader(input);
				BufferedReader buffer = new BufferedReader(reader);

				while((line = buffer.readLine()) != null) {
					data += line;
				}
			} finally {
				input.close();
			}
			description = (JSONObject) JSONValue.parseWithException(data);
		
		} catch(IOException IOe) {
			IOe.printStackTrace();
		}/*catch(ParseException parseE) {
			//TODO concludi eccezione personalizzata
			parseE.printStackTrace();
		}*/catch(Exception e) {
			e.printStackTrace();
		}
		
		return description;
	}
	
	/**
	 * This method is able to read the JSON file proposed by PostMan and to upload in our object City 
	 * and Pressure
	 */
	@Override
	public City getWeather(JSONObject obj) {
		City city = new City();
		//Pressure pressure = new Pressure();
		//Vector<Pressure> pressureVec = new Vector<Pressure>();
		
		JSONObject coordinate = (JSONObject) obj.get("coord");
		JSONObject main = (JSONObject) obj.get("main");
		JSONObject sys = (JSONObject) obj.get("sys");
		//JSONObject id = (JSONObject) obj.get("id");
		//JSONObject name = (JSONObject) obj.get("name");
		
		city.setLat((Double)coordinate.get("lat"));
		city.setLongi((Double)coordinate.get("lon"));
		city.getPressure().setPressure((int)main.get("pressure"));
		city.setName((String)obj.get("name"));
		city.setId((int)obj.get("id"));
		city.setCountry((String)obj.get("country"));
		
		return city;
	}

	@Override
	public JSONObject toJSON(City city) {
		// TODO Auto-generated method stub
		
		JSONObject output =new JSONObject();
		output.put("city", city.getName());
		output.put("Id", city.getId());
		
		return null;
	}
	
	/**
	 * This method return all the pressions' values registered in a city
	 */
	@Override
	public Vector<Integer> getAllPressure() throws VectorNull {
		if(p.getPressureVector().isEmpty())
			throw new VectorNull("Non ci sono pressioni registrate per questa città");
		else
			return p.getPressureVector();
	}
}
