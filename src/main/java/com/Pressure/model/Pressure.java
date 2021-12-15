package com.Pressure.model;

import java.util.Vector;

import Statistics.PressureStats;

/**
 * Classe che descrive tutte le caratteristiche in termini di pressione(minima, massima,...)
 * di una città
 * @author Giansimone&Walter
 *
 */
public class Pressure implements Value{

	private Vector<Long> pressure=new Vector<Long>();
	private long pressure_max;
	private long pressure_min;
	private double pressure_med;
	private long pressure_diff;
	
	/**
	 * Constructor
	 * @param pressure_max The pressure's max value
	 * @param pressure_min The pressure's min value
	 * @param pressure_med The pressure's medium value
	 */
	public Pressure(long pressure_max, long pressure_min, double pressure_med, long pressure_diff) {
		this.pressure_max = pressure_max;
		this.pressure_min = pressure_min;
		this.pressure_med = pressure_med;
		this.pressure_diff = pressure_diff;
	}
	
	/**
	 * Default constructor
	 */
	public Pressure() {
	}
	
	/**
	 * 
	 * @return All pressure
	 */
	public Vector<Long> getPressureVector() {
		return this.pressure;
	}
	
	/**
	 * This method allows to set the pressure passed
	 * @param pressure The pressure to set
	 * @throws ArrayIndexOutOfBoundsException, Exception
	 */
	@Override
	public void setValue(long pressure) {
		
		try {
			this.pressure.add(pressure);

			/*if(this.pressure.size()>24)
				this.pressure.remove(0);*/

			PressureStats pressureStats=new PressureStats();

			this.pressure_med= pressureStats.getMedValue(this.pressure);

			this.pressure_min=pressureStats.getMinValue(this.pressure);

			this.pressure_max=pressureStats.getMaxValue(this.pressure);

			this.pressure_diff=pressureStats.getDiffValue(pressure_max, pressure_min);
			
		} catch(ArrayIndexOutOfBoundsException ArrayExc) {
			System.out.println("Errore con indici del vettore");
			System.out.println(ArrayExc);
		} catch(Exception e) {
			System.out.println("Errore generico");
			System.out.println(e);
		}
		
	}
	
	/**
	 * 
	 * @return The maximum pressure's value
	 */
	@Override
	public long getValue_max() {
		return pressure_max;
	}
	
	/**
	 * 
	 * @return The minimum pressure's value
	 */
	@Override
	public long getValue_min() {
		return pressure_min;
	}
	
	/**
	 * 
	 * @return The medium pressure
	 */
	@Override
	public double getValue_med() {
		return pressure_med;
	}
	
	/**
	 * 
	 * @return The difference between the maximum and minimum pressure
	 */
	@Override
	public long getValue_diff() {
		return pressure_diff;
	}
}
