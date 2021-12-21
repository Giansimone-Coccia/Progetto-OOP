package Statistics;

import java.io.FileNotFoundException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Pressure.model.PressureAndTime;
import com.Pressure.service.PressureServiceImpl;

import Exception.DateFormatException;
import Utilities.DateConverter;

/**
 * Classe che viene richiamata per mostrare tutti i valori di pressioni registrati per una città
 * @author Giasimone&Walter
 *
 */
public class ShowAllPressure {
	
	PressureAndTime press = new PressureAndTime();
	PressureServiceImpl pressService = new PressureServiceImpl();
	
	/**
	 * 
	 * @param cityName The city's name
	 * @return The JSONObject
	 * @throws DateFormatException 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject showAllPressure(String cityName) throws DateFormatException{
		
		PressureAndTime p=pressService.readAll("allData."+cityName+".json");
		
		JSONObject objM = new JSONObject();
		
		JSONObject objS = new JSONObject();
		
		JSONArray objArray = new JSONArray();
		
	
		objS.put("Numero totale di info raccolte", p.getPressureVector().size());
		objS.put("Valore di pressione massima totale", p.getValue_max());
		objS.put("Valore di pressione minima totale", p.getValue_min());
		objS.put("Valore di pressione medi totale", p.getValue_med());
		objS.put("Differenze di pressione totale", p.getValue_diff());
		
		objM.put("Statistics", objS);
		
		
		Vector<Long> pressure=p.getPressureVector();
		Vector<String> dates=p.getTimeVector();
		DateConverter dateConverter=new DateConverter();
		
		for(int i=0;i<pressure.size() && i<dates.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("index",i+1);
			obj.put("pressure",pressure.get(i));
			obj.put("date", dates.get(i));
			obj.put("unix time", dateConverter.dateToSeconds(dates.get(i)));
			
			objArray.add(obj);
		}
		
		objM.put("info",objArray);
		
		return objM;
	}
}
