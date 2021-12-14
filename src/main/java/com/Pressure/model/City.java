package com.Pressure.model;

import java.util.Objects;

import java.util.Vector;

/**
 * Classe che descrive una generica città attraverso i suoi attributi
 * @author Giasimone&Walter
 *
 */
<<<<<<< HEAD
public class City extends Country{
=======
public class City {
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
	/**
	 * Nome città
	 */
	private String name;
	/**
<<<<<<< HEAD
=======
	 * Nome Stato
	 */
	private String country;
	/**
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
	 * id città
	 */
	private Long id;
	/**
	 * Oggetto che descrive la pressione della città.
	 */
<<<<<<< HEAD
	private Pressure pressure = new Pressure();
=======
	private Pressure pressure=new Pressure();
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
	
	/**
	 * Longitudine della città
	 */
	private Double lon;
	
	/**
	 * Latitudine della città
	 */
	private Double lat;
<<<<<<< HEAD
=======
	/**
	 * Costruttore di default
	 */
	public City() {
		super();
	}
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
	
	/**
	 * Costruttore
	 * @param name Il nome della città
	 * @param id L'identificativo della città
	 * @param code Il codice della città
	 * @param timezone Fuso orario della città
	 * @param pressure Valore pressione nella città
	 */
<<<<<<< HEAD
	public City(String nameC, String name, Long id, Pressure pressure, Double lon, Double lat) {
		super(nameC);
		this.name = name;
=======
	
	public City(String name,String country, Long id, Pressure pressure, Double lon, Double lat) {
		super();
		this.name = name;
		this.country=country;
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
		this.id = id;
		this.pressure = pressure;
		this.lon = lon;
		this.lat = lat;
	}
	
	/**
<<<<<<< HEAD
	 * Default constructor
	 */
	public City() {
		super();
	}
	
	/**
=======
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
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
<<<<<<< HEAD
=======
	 * @return The coutry's name
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param name The coutry?s name to set for the city
	 */

	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * 
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
	 * @return City's id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id City's id to set for
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return The object pressure
	 */
	public Pressure getPressure() {
		return pressure;
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
