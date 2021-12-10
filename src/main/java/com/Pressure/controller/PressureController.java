package com.Pressure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.Pressure.service.PressureService;

/**
 * Piccola classe del controller per provare che tutto
 * proceda senza problemi
 * @author Giansimone&Walter
 *
 */
@RestController
public class PressureController {
	
	@Autowired
	private PressureService pressureService;
	
	@RequestMapping(value = "/getMilan")
	public ResponseEntity<Object> getPressure(){
		return new ResponseEntity<>(pressureService.toJSON(pressureService.getPressure(pressureService.getJSONfromPman("Milan"))), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPressure")
	public ResponseEntity<Object> getPressurebyCity(@RequestParam(name = "city", defaultValue = "Milan") String city){
		return new ResponseEntity<>(pressureService.toJSON(pressureService.getPressure(pressureService.getJSONfromPman(city))), HttpStatus.OK);
	}
}
