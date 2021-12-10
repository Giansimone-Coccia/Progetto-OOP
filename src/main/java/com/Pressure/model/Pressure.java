package com.Pressure.model;

import java.util.Vector;

public class Pressure {

	private Vector<Integer> pressure;
	private int pressure_max;
	private int pressure_min;
	private double pressure_med;
	private int pressure_diff;

	public void setPressure(int pressure) {

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

		this.pressure_med= pressure_med/this.pressure.size();

		this.pressure_min=pressure_min;

		this.pressure_max=pressure_max;

		this.pressure_diff=pressure_max-pressure_min;
	}

	public int getPressure_max() {
		return pressure_max;
	}

	public int getPressure_min() {
		return pressure_min;
	}

	public double getPressure_med() {
		return pressure_med;
	}

	public int getPressure_diff() {
		return pressure_diff;
	}
	
	public Vector<Integer> getPressureVector() {
		return this.pressure;
	}
}
