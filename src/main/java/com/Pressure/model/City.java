package com.Pressure.model;

import java.util.Objects;

import java.util.Vector;

/**
 * Classe che descrive una generica città attraverso i suoi attributi
 * @author Giasimone&Walter
 *
 */
public class City {
	/**
	 * Nome città
	 */
	private String name;
	/**
	 * id città
	 */
	private int id;
	/**
	 * Valore di pressione della città
	 */
	private Vector<Integer> pressure;
	
	/**
	 * Longitudine della città
	 */
	private Double lon;
	
	/**
	 * Latitudine della città
	 */
	private Double lat;
	/**
	 * Costruttore di default
	 */
	public City() {
		super();
	}
	
	/**
	 * Costruttore
	 * @param name Il nome della città
	 * @param id L'identificativo della città
	 * @param code Il codice della città
	 * @param timezone Fuso orario della città
	 * @param pressure Valore pressione nella città
	 */
	
	public City(String name, int id, int code, int timezone, Vector<Integer> pressure, Double lon, Double lat) {
		super();
		this.name = name;
		this.id = id;
		this.pressure = pressure;
		this.lon = lon;
		this.lat = lat;
	}
	
	/**
	 * 
	 * @return The city's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set for the city
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return City's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id City's id to set for
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return The pressure's Vector
	 */
	public Vector<Integer> getPressure() {
		return pressure;
	}
	
	/**
	 * 
	 * @param pressure The pressure values to set
	 */
	public void setPressure(Vector<Integer> pressure) {
		this.pressure = pressure;
	}

	/**
	 * 
	 * @return longi The city's longitude
	 */
	public Double getLon() {
		return lon;
	}
	
	/**
	 * 
	 * @param longi The longitude to set
	 */
	public void setLongi(Double lon) {
		this.lon = lon;
	}
	
	/**
	 * 
	 * @return lat The city's latitude
	 */
	public Double getLat() {
		return lat;
	}
	
	/**
	 * 
	 * @param lat The city's latitude to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * Override del metodo toString di Object
	 */
	@Override
	public String toString() {
		return "City [name=" + name + ", id=" + id +", pressure="
				+ pressure + ", longi=" + lon + ", lat=" + lat + "]";
	}
	
	/**
	 * Questo metodo può tornare utile nel momento in cui si va ad utilizzare
	 * il debugger
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, lat, lon, name, pressure);
	}
	
	/**
	 * Override del metodo equals di Object per confrontare due oggetti
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return  id == other.id && lat == other.lat && lon == other.lon
				&& Objects.equals(name, other.name) && Objects.equals(pressure, other.pressure);
	}
}
