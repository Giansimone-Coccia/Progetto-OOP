package com.Pressure.controller;

import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.Pressure.service.PressureService;
import com.Pressure.service.PressureServiceImpl;

import Statistics.cityCompare;

/**
 * Classe controller che gestisce le rotte per le chiamate dell'API da Postaman
 * @author Giansimone&Walter
 *
 */
@RestController
public class PressureController {
	
	@Autowired
	private PressureServiceImpl pressureServiceImpl;

	
	/*@RequestMapping(value = "/getMilan", method = RequestMethod.GET)
	public ResponseEntity<Object> getPressure(){
		return new ResponseEntity<>(pressureServiceImpl.toJSON(pressureServiceImpl.getWeather(pressureServiceImpl.getJSONfromPman("Milan"))), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPressure")
	public ResponseEntity<Object> getPressurebyCity(@RequestParam(name = "city", defaultValue = "Milan") String city){
		return new ResponseEntity<>(pressureServiceImpl.toJSON(pressureServiceImpl.getWeather(pressureServiceImpl.getJSONfromPman(city))), HttpStatus.OK);
	}
	
	@GetMapping("/getP")
	public ResponseEntity<Object> getAll(@RequestParam(name = "city", defaultValue = "London") String city){
		return new ResponseEntity<>(pressureServiceImpl.toJSON(pressureServiceImpl.getWeather(pressureServiceImpl.getJSONfromPman(city))), HttpStatus.OK);
	}*/
	
	/**
	 * 
	 * @param nomeCitta The city's name
	 * @return The file JSON saved in local with all the pressures registered
	 */
	@GetMapping("/save")
	public ResponseEntity<String> save(@RequestParam("name") String nomeCitta) {
		PressureServiceImpl pressService = new PressureServiceImpl();
		pressService.saveData(nomeCitta);
		
		return new ResponseEntity<>("File creato con successo", HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param name1 The first city's name
	 * @param name2 The second city's name
	 * @param in The initial instant time
	 * @param last The final instant time
	 * @return The comparison in terms of pressure's stats beetween the two cities choosen
	 */
	@GetMapping("/compare")
	public ResponseEntity<Object> compareStats (@RequestParam("city1") String name1, @RequestParam("city2") String name2, @RequestParam("timeInit") String in, @RequestParam("endTime") String last){
		cityCompare comp = new cityCompare();
		return new ResponseEntity<>(comp.compare(name1, name2, in, last)/*.toString()*/, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param nameCity The city's name
	 * @return The JSONObject city, with all values on pressure and some general values
	 */
	@GetMapping("/getCity")
	public ResponseEntity<Object> getCity(@RequestParam("city")String nameCity){
		return new ResponseEntity<>(pressureServiceImpl.toJSON(pressureServiceImpl.getWeather(pressureServiceImpl.getJSONfromPman(nameCity))), HttpStatus.OK);
	}
}
