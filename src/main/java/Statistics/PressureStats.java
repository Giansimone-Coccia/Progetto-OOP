package Statistics;

import java.util.Vector;

public class PressureStats {

	public Long getMaxValue(Vector<Long> pressure) {

		long pressure_max=pressure.get(0);
		for(int i=0;i<pressure.size();i++) {
			if(pressure_max<pressure.get(i))
				pressure_max=pressure.get(i);
		}

		return pressure_max;
	}

	public Long getMinValue(Vector<Long> pressure) {

		long pressure_min=pressure.get(0);
		for(int i=0;i<pressure.size();i++) {
			if(pressure_min>pressure.get(i))
				pressure_min=pressure.get(i);
		}

		return pressure_min;
	}

	public Long getMedValue(Vector<Long> pressure) {

		long pressure_med=0;
		for(int i=0;i<pressure.size();i++) {
			pressure_med+=pressure.get(i);
		}

		return pressure_med/pressure.size();
	}
	
	public Long getDiffValue(long value1,long value2) {

		return value1-value2;
	}

}
