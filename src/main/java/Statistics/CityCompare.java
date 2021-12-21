package Statistics;

import java.io.FileNotFoundException;

import org.json.simple.JSONObject;


import com.Pressure.model.Pressure;
import com.Pressure.service.PressureServiceImpl;

import Utilities.DateConverter;
import Exception.DateChronologyException;
import Exception.DateFormatException;

/**
 * Classe utilizzata per comparare le statistiche tra due città
 *@author Giasimone&Walter
 *
 */
public class CityCompare {
	
	private double minC1, minC2;
	private double maxC1, maxC2;
	private double medC1, medC2;
	private double diffC1, diffC2;
	
	Pressure press = new Pressure();
	PressureServiceImpl pressService = new PressureServiceImpl();
	DateConverter converter = new DateConverter();
	
	/**
	 * 
	 * @param name1 The first city name
	 * @param name2 The second city name
	 * @param init The initial instant time for calculating stats
	 * @param last The final instant time for calculating stats
	 * @return The JSONObject with right values
	 * @throws DateFormatException for date format error
	 * @throws DateChronologyException for date chronological error 
	 * @throws FileNotFoundException for file not founded
	 */
	@SuppressWarnings("unchecked")
	public JSONObject compare(String name1, String name2, String init, String last) throws DateFormatException, DateChronologyException, FileNotFoundException {
		
		if(converter.dateToSeconds(init) > converter.dateToSeconds(last)) throw new DateChronologyException("Immissione cronologica delle date errato");
		
		Pressure p1;
		Pressure p2;
		p1 = pressService.readJSON("allData."+name1+".json", init, last);
		p2 = pressService.readJSON("allData."+name2+".json", init, last);
		
		minC1 = p1.getValue_min();
		maxC1 = p1.getValue_max();
		medC1 = p1.getValue_med();
		diffC1 = p1.getValue_diff();
		minC2 = p2.getValue_min();
		maxC2 = p2.getValue_max();
		medC2 = p2.getValue_med();
		diffC2 = p2.getValue_diff();

		
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONObject objM = new JSONObject();
		//JSONArray array = new JSONArray();
		
		obj1.put("name", name1);
		obj2.put("name", name2);
		
		JSONObject minPress = new JSONObject();
		minPress.put("Valore di pressione minima "+name1, minC1);
		minPress.put("Valore di pressione minima "+name2, minC2);
		objM.put("Valori di pressione minimi", minPress);
		
		JSONObject maxPress = new JSONObject();
		maxPress.put("Valore di pressione massima "+name1, maxC1);
		maxPress.put("Valore di pressione massima "+name2, maxC2);
		objM.put("Valori di pressione massimi", maxPress);

		
		JSONObject medPress = new JSONObject();
		medPress.put("Valore di pressione medi "+name1, medC1);
		medPress.put("Valore di pressione medi "+name2, medC2);
		objM.put("Valori di pressione medi", medPress);
		
		JSONObject diffPress = new JSONObject();
		diffPress.put("Differenze di pressione "+name1, diffC1);
		diffPress.put("Differenze di pressione "+name2, diffC2);
		objM.put("Differenze di pressione", diffPress);
		
		return objM;
	}
}
