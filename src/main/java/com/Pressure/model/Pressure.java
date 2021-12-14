package com.Pressure.model;

import java.util.Vector;

/**
 * Classe che descrive tutte le caratteristiche in termini di pressione(minima, massima,...)
 * di una città
 * @author Giansimone&Walter
 *
 */
public class Pressure {

	private Vector<Integer> pressure;
	private int pressure_max;
	private int pressure_min;
	private double pressure_med;
	private int pressure_diff;

	/**
	 * This method allows to set the pressure passed
	 * @param pressure The pressure to set
	 * @throws ArrayIndexOutOfBoundsException, Exception
	 */
	public void setPressure(int pressure) {

		try {
			this.pressure.add(pressure);

			if(this.pressure.size()>24)
				this.pressure.remove(0);

			double pressure_med=0;
			int pressure_max=this.pressure.get(0);
			int pressure_min=this.pressure.get(0);

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
		} catch(ArrayIndexOutOfBoundsException ArrayExc) {
			System.out.println("Errore con indici del vettore");
		} catch(Exception e) {
			System.out.println("Errore generico");
		}

		this.pressure_med= pressure_med/this.pressure.size();

		this.pressure_min=pressure_min;

		this.pressure_max=pressure_max;

		this.pressure_diff=pressure_max-pressure_min;
	}

	/**
	 * 
	 * @return The maximum pressure's value
	 */
	public int getPressure_max() {
		return pressure_max;
	}

	/**
	 * 
	 * @return The minimum pressure's value
	 */
	public int getPressure_min() {
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
	public int getPressure_diff() {
		return pressure_diff;
	}

	/**
	 * 
	 * @return All pressure
	 */
	public Vector<Integer> getPressureVector() {
		return this.pressure;
	}
}
