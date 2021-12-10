package com.Pressure.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.Pressure.model.City;

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

	@Override
	public JSONObject getJSONfromPman(String city) {
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
		}catch(ParseException parseE) {
			parseE.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return description;
	}
	
	@Override
	public City getPressure(JSONObject obj) {
		City city = new City();
		Vector<Pressure> pressureVec = new Vector<Pressure>();
		
		JSONObject cityRep = (JSONObject) obj.get("city");
		JSONArray array = (JSONArray) obj.get("array");
		
		city.setName((String) cityRep.get("name"));
		city.setId(String.valueOf(cityRep.get("Id")));
		
		for(int i = 0; i<array.size(); i++) {
			JSONObject listElement = (JSONObject) array.get(i);
			Pressure press = new Pressure();
			
			JSONObject weather = (JSONObject)((JSONArray)listElement.get("weather"));
			JSONObject main = (JSONObject)listElement.get("main");
			
			
		}
	}
}
