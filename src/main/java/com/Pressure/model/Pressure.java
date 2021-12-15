package com.Pressure.model;

import java.util.Vector;

/**
 * Classe che descrive tutte le caratteristiche in termini di pressione(minima, massima,...)
 * di una città
 * @author Giansimone&Walter
 *
 */
public class Pressure {

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
	 * This method allows to set the pressure passed
	 * @param pressure The pressure to set
	 * @throws ArrayIndexOutOfBoundsException, Exception
	 */
	public void setPressure(long pressure) {

		try {
			this.pressure.add(pressure);

			/*if(this.pressure.size()>24)
				this.pressure.remove(0);*/

			double pressure_med=0;
			long pressure_max=this.pressure.get(0);
			long pressure_min=this.pressure.get(0);

			for(int i=0;i<this.pressure.size();i++) {
				pressure_med+=this.pressure.get(i);
			}

			for(int i=0;i<this.pressure.size();i++) {
				if(pressure_max<this.pressure.get(i))
					pressure_max=this.pressure.get(i);
			}

			for(int i=0;i<this.pressure.size();i++) {
				if(pressure_min>this.pressure.get(i))
					pressure_min=this.pressure.get(i);
			}

			this.pressure_med= pressure_med/this.pressure.size();

			this.pressure_min=pressure_min;

			this.pressure_max=pressure_max;

			this.pressure_diff=pressure_max-pressure_min;
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
	public long getPressure_max() {
		return pressure_max;
	}

	/**
	 * 
	 * @return The minimum pressure's value
	 */
	public long getPressure_min() {
		return pressure_min;
	}

	/**
	 * 
	 * @return The medium pressure
	 */
	public double getPressure_med() {
		return pressure_med;
	}

	/**
	 * 
	 * @return The difference between the maximum and minimum pressure
	 */
	public long getPressure_diff() {
		return pressure_diff;
	}

	/**
	 * 
	 * @return All pressure
	 */
	public Vector<Long> getPressureVector() {
		return this.pressure;
	}
}
