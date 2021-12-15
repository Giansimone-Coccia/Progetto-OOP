package com.Pressure.controller;

import java.io.IOException;

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

/**
 * Piccola classe del controller per provare che tutto
 * proceda senza problemi
 * @author Giansimone&Walter
 *
 */
@RestController
public class PressureController {
	
	@Autowired
	private PressureServiceImpl pressureServiceImpl;

	
	@RequestMapping(value = "/getMilan", method = RequestMethod.GET)
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
	}
	
	@GetMapping("/save")
	public String save(@RequestParam("nome") String nomeCitta) {
		PressureServiceImpl pressService = new PressureServiceImpl();
		pressService.saveData(nomeCitta);
		
		return "File creato con successo";
	}
}
