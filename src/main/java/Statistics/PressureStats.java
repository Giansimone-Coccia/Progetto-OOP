package Statistics;

import java.util.Vector;

/**
 * Classe che calcola le statistiche legate alla pressione, i cui metodi verranno richiamati
 * nel metodo della classe Pressure
 * @author Giansimone&Walter
 *
 */
public class PressureStats {

	/**
	 * 
	 * @param pressure The pressure
	 * @return The pressure's max value
	 */
	public Long getMaxValue(Vector<Long> pressure) {

		long pressure_max=pressure.get(0);
		for(int i=0;i<pressure.size();i++) {
			if(pressure_max<pressure.get(i))
				pressure_max=pressure.get(i);
		}

		return pressure_max;
	}
	
	/**
	 * 
	 * @param pressure The pressure
	 * @return The pressure's minimum value
	 */
	public Long getMinValue(Vector<Long> pressure) {

		long pressure_min=pressure.get(0);
		for(int i=0;i<pressure.size();i++) {
			if(pressure_min>pressure.get(i))
				pressure_min=pressure.get(i);
		}

		return pressure_min;
	}
	
	/**
	 * 
	 * @param pressure The pressure
	 * @return The pressure's medium value
	 */
	public Long getMedValue(Vector<Long> pressure) {

		long pressure_med=0;
		for(int i=0;i<pressure.size();i++) {
			pressure_med+=pressure.get(i);
		}

		return pressure_med/pressure.size();
	}
	
	/**
	 * 
	 * @param value1 The first value
	 * @param value2 The second value
	 * @return The difference beetwwen these two values
	 */
	public Long getDiffValue(long value1,long value2) {

		return value1-value2;
	}

}
