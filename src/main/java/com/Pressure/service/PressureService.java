package com.Pressure.service;

import org.json.simple.*;
import com.Pressure.model.*;

public interface PressureService {
	
	public abstract JSONObject toJSON(City city);
	public abstract JSONObject getJSONfromPman(String city);
	public abstract City getPressure(JSONObject pressure);
	public abstract void save(JSONObject obj);
}
