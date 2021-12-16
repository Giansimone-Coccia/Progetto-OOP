package Statistics;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Pressure.model.PressureAndTime;
import com.Pressure.service.PressureServiceImpl;

public class ShowAllPressure {
	
	PressureAndTime press = new PressureAndTime();
	PressureServiceImpl pressService = new PressureServiceImpl();
	
	@SuppressWarnings("unchecked")
	public JSONObject showAllPressure(String cityName) {
		
		PressureAndTime p=pressService.readAll("allData."+cityName+".json");
		
		JSONObject objM = new JSONObject();
		JSONObject objI = new JSONObject();
		JSONObject objS = new JSONObject();
		
		JSONArray array=new JSONArray();
		
	
		
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
		
		objM.put("infos",array);
		
		return objM;
	}

}
