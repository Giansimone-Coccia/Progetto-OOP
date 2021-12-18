package com.Pressure.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.Pressure.model.City;
import com.Pressure.model.Pressure;
import com.Pressure.model.PressureAndTime;

import Exception.DateIOException;
import Exception.VectorNull;
import Utilities.DateConverter;

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
	private String url = "https://api.openweathermap.org/data/2.5/weather?q=";
	Pressure p = new Pressure();
	
	/**
	 * This method gets the various JSON object from the JSON file returned by PostMan
	 * {@inheritDoc}
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return description;
	}
	
	/**
	 * This method is able to read the JSON file proposed by PostMan and to upload in our object City 
	 * and Pressure
	 * {@inheritDoc}
	 */
	@Override
	public City getWeather(JSONObject obj) {
		City city = new City();
		//Pressure pressure = new Pressure();
		//Vector<Pressure> pressureVec = new Vector<Pressure>();
		try {
			JSONObject coordinate = (JSONObject) obj.get("coord");
			JSONObject main = (JSONObject) obj.get("main");
			JSONObject sys = (JSONObject) obj.get("sys");
			//JSONObject id = (JSONObject) obj.get("id");
			//JSONObject name = (JSONObject) obj.get("name");

			city.setLat((Double)coordinate.get("lat"));
			city.setLongi((Double)coordinate.get("lon"));
			//city.getPressure().setPressure((Long)main.get("pressure"));
			city.getPressure().setValue((Long)main.get("pressure"));
			city.setName((String)obj.get("name"));
			city.setId((Long)obj.get("id"));//Long poichè  problemi di csting da Long a Integer
			city.setNameC((String)sys.get("country"));
		} catch(ArrayIndexOutOfBoundsException IndexE) {
			System.out.println("Errore nella lettura dell'array");
			System.out.println(IndexE);
		}

		return city;
	}
	
	/**
	 * This method converts the city's details in a JSON object ready to upload in Postman
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON(City city) {
		// TODO Auto-generated method stub
	
		JSONObject cityData =new JSONObject();
		cityData.put("City", city.getName());
		cityData.put("Country", city.getNameC());
		cityData.put("Id", city.getId());
		cityData.put("Lat", city.getLat());
		cityData.put("Lon", city.getLon());
		
		JSONArray pressureData=new JSONArray();
		
		JSONObject pressureObj=new JSONObject();
		
		pressureObj.put("Pressure_max", city.getPressure().getValue_max());
		pressureObj.put("Pressure_min", city.getPressure().getValue_min());
		pressureObj.put("Pressure_med", city.getPressure().getValue_med());
		pressureObj.put("Pressure_diff", city.getPressure().getValue_diff());
		
		pressureData.add(pressureObj);
		
		cityData.put("Pressure_Data", pressureData);
		
		return cityData;
	}
	
	/**
	 * This method return all the pressions' values registered in a city
	 * {@inheritDoc}
	 */
	@Override
	public Vector<Long> getAllPressure() throws VectorNull {
		if(p.getPressureVector().isEmpty())
			throw new VectorNull("Non ci sono pressioni registrate per questa città");
		else
			return p.getPressureVector();
	}
	
	/**
	 * This method is used to save the city's details in a local JSON file that We'll use to calcolate
	 * the stats and show them on PostMan
	 * {@inheritDoc}
	 */
	@Override
	public void saveData(String cityName) {
		
		File file = new File("allData."+cityName+".json");
		
		TimerTask timerTask=new TimerTask() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				/*City city=getWeather(getJSONfromPman(cityName));
				JSONObject allData=toJSON(city);
				 try {
			         FileWriter fileWriter = new FileWriter(file, true);
			         //FileWriter fileWriter = new FileWriter(file);
			         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			         bufferedWriter.write(allData.toJSONString());
			         //bufferedWriter.close();
			         //bufferedWriter.append(allData.toJSONString());
			         bufferedWriter.close();
			      } catch (IOException e) {
			         e.printStackTrace();
			      }*/
				
				JSONObject main = (JSONObject) getJSONfromPman(cityName).get("main");
				long pressure = (long) main.get("pressure");
				Long dt = (long) getJSONfromPman(cityName).get("dt");
				JSONObject allData=new JSONObject();
				allData.put("pressure", pressure);
				allData.put("dt", dt);
				 try {
			         FileWriter fileWriter = new FileWriter(file,true);
			         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			         bufferedWriter.write(allData.toJSONString()+"\n");
			         bufferedWriter.close();
			      } catch (IOException e) {
			         System.out.println("Errore di I/O");
			         System.out.println(e);
			      }
			}	
		};
		
		Timer timer=new Timer();
		timer.schedule(timerTask,0,1800000);
	}
	
	/**
	 * This method is used to read the file JSON saved recently and calculate the minimum, maximum and
	 * the medium pressions' values
	 * {@inheritDoc}
	 */
	@Override
	public Pressure readJSON(String fileName,String init,String last) {
		//TODO controlla tipi

		DateConverter converter=new DateConverter();
		Long initS = null, lastS = null;
			initS = converter.dateToSeconds(init);
			lastS=converter.dateToSeconds(last);
		
		Pressure pressure=new Pressure();
		try {
			Scanner file_input =new Scanner ( new BufferedReader (new FileReader (fileName )));

			while ( file_input.hasNext()) {

				String string=file_input.nextLine();
				JSONParser parser= new JSONParser();
				JSONObject obj=(JSONObject) parser.parse(string);
				if((Long)obj.get("dt")>=lastS)
					break;
				if((Long)obj.get("dt")>=initS && (Long)obj.get("dt")<=lastS)
					pressure.setValue((Long)obj.get("pressure"));
			}
		} catch(FileNotFoundException fnfE) {
			System.out.println("File non trovato");
			System.out.println(fnfE);
		} catch(IOException ioE) {
			System.out.println("Problema di I/O");
			System.out.println(ioE);
		} catch(Exception e) {
			System.out.println("Problema");
			System.out.println(e);
		}
		return pressure;
	}
	
	public PressureAndTime readAll(String fileName) {
		//TODO controlla tipi

		PressureAndTime pressure=new PressureAndTime();
		try {
			Scanner file_input =new Scanner ( new BufferedReader (new FileReader (fileName )));

			while ( file_input.hasNext()) {

				String string=file_input.nextLine();
				JSONParser parser= new JSONParser();
				JSONObject obj=(JSONObject) parser.parse(string);
				pressure.setTime((Long) obj.get("dt"));
				pressure.setValue((Long)obj.get("pressure"));
			}
		} catch(FileNotFoundException fnfE) {
			System.out.println("File non trovato");
			System.out.println(fnfE);
		} catch(IOException ioE) {
			System.out.println("Problema di I/O");
			System.out.println(ioE);
		} catch(Exception e) {
			System.out.println("Problema");
			System.out.println(e);
		}
		return pressure;
	}

}
