package com.Pressure.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.Pressure.exceptions.DateChronologyException;
import com.Pressure.exceptions.DateFormatException;
import com.Pressure.service.PressureServiceImpl;
import com.Pressure.statistics.CityCompare;
import com.Pressure.statistics.ShowAllPressure;



/**
 * Classe controller che gestisce le rotte per le chiamate dell'API da Postaman
 * @author Giansimone&Walter
 *
 */
@RestController
public class PressureController {
	
	@Autowired
	private PressureServiceImpl pressureServiceImpl;

	/**
	 * 
	 * @return The Milan's carachteristics We've selectioned
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = "/getMilan", method = RequestMethod.GET)
	public ResponseEntity<Object> getPressure(){
		return new ResponseEntity<>(pressureServiceImpl.toJSON(pressureServiceImpl.getWeather(pressureServiceImpl.getJSONfromPman("Milan"))), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param nomeCitta The city's name
	 * @return The file JSON saved in local with all the pressures registered
	 */
	@GetMapping("/save")
	public ResponseEntity<String> save(@RequestParam("name") String nomeCitta){
		PressureServiceImpl pressService = new PressureServiceImpl();
		pressService.saveData(nomeCitta);
		
		return new ResponseEntity<>("File creato/aggiornato con successo", HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param name1 The first city's name
	 * @param name2 The second city's name
	 * @param in The initial instant time
	 * @param last The final instant time
	 * @return The comparison in terms of pressure's stats beetween the two cities choosen
	 * @throws DateFormatException 
	 * @throws DateChronologyException 
	 * @throws FileNotFoundException 
	 */
	@GetMapping("/compare")
	public ResponseEntity<Object> compareStats (@RequestParam("city1") String name1, @RequestParam("city2") String name2, @RequestParam("timeInit") String in, @RequestParam("endTime") String last) throws DateFormatException, DateChronologyException, FileNotFoundException{
		CityCompare comp = new CityCompare();
		return new ResponseEntity<>(comp.compare(name1, name2, in, last), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param nameCity The city's name
	 * @return The JSONObject city, with all values on pressure and some general values
	 * @throws FileNotFoundException 
	 */
	@GetMapping("/getCity")
	public ResponseEntity<Object> getCity(@RequestParam("city")String nameCity){
		return new ResponseEntity<>(pressureServiceImpl.toJSON(pressureServiceImpl.getWeather(pressureServiceImpl.getJSONfromPman(nameCity))), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param nameCity The city'sname
	 * @return All pressure's value registered for a city
	 * @throws DateFormatException 
	 * @throws FileNotFoundException 
	 */
	@GetMapping("/getAllPressure")
	public ResponseEntity<Object> getAllPressure(@RequestParam("city")String nameCity) throws DateFormatException, FileNotFoundException{
		ShowAllPressure show = new ShowAllPressure ();
		return new ResponseEntity<>((show.showAllPressure(nameCity)), HttpStatus.OK);
	}
}
