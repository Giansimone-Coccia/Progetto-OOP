package Statistics;

import java.io.FileNotFoundException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Pressure.model.PressureAndTime;
import com.Pressure.service.PressureServiceImpl;

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
	 */
	@SuppressWarnings("unchecked")
	public JSONObject showAllPressure(String cityName){
		
		PressureAndTime p=pressService.readAll("allData."+cityName+".json");
		
		JSONObject objM = new JSONObject();
		JSONObject objI = new JSONObject();
		JSONObject objS = new JSONObject();
		
		JSONArray array=new JSONArray();
		
	
		objS.put("Numero totale di info raccolte", p.getPressureVector().size());
		objS.put("Valore di pressione massima totale", p.getValue_max());
		objS.put("Valore di pressione minima totale", p.getValue_min());
		objS.put("Valore di pressione medi totale", p.getValue_med());
		objS.put("Differenze di pressione totale", p.getValue_diff());
		
		objM.put("Statistics", objS);
		
		
		Vector<Long> pressure=p.getPressureVector();
		Vector<String> dates=p.getTimeVector();
		
		for(int i=0;i<pressure.size() && i<dates.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("pressure",pressure.get(i));
			obj.put("date", dates.get(i));
			objI.put("info n."+(i+1),obj);
		}
		array.add(objI);
		
		objM.put("info",array);
		
		return objM;
	}
}
