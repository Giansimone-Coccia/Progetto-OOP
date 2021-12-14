package com.Pressure.model;

import java.util.Objects;

/**
 * Classe che verrà estesa da City, e che identificherà la nazione in cui è situata
 * la città. Definisce una classe Country generica con attributi il suo nome
 * @author Giansimone&Walter
 *
 */
public class Country {
	
	/**
	 * The Country's name
	 */
	private String nameC;
	
	/**
	 * Constructor
	 * @param nameC The country's name
	 */
	public Country(String nameC) {
		this.nameC = nameC;
	}
	
	/**
	 * Default constructor
	 */
	public Country() {
		
	}
	
	/**
	 * 
	 * @return The country's name
	 */
	public String getNameC() {
		return nameC;
	}
	
	/**
	 * 
	 * @param nameC The country's name to set
	 */
	public void setNameC(String nameC) {
		this.nameC = nameC;
	}
	
	/**
	 * Overrdie del metodo hashCode utile in caso di utilizzo del debugger
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nameC);
	}
	
	/**
	 * Override del metodo equals, per controntare oggetti
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(nameC, other.nameC);
	}
	
	/**
	 * Override del metodo toString
	 */
	@Override
	public String toString() {
		return "Country [nameC=" + nameC + "]";
	}
	
}
