package GUI;

import java.util.Scanner;

/**
 * Classe che gestisce l'interazione iniziale con l'utente sulla scleta del range orario per il
 * calcolo degli stats
 * @author Giansimone&Walter
 *
 */
public class MenuInit {
	
	Scanner input = new Scanner(System.in);
	
	/**
	 * Prima città
	 */
	private String city1;
	
	/**
	 * Seconda città
	 */
	private String city2;
	
	/**
	 * Tempo iniziale
	 */
	private String init;
	
	/**
	 * Tempo finale
	 */
	private String last;
	
	/**
	 * Constructor
	 * @param city1 The first city
	 * @param city2 The second city
	 * @param init The initial time instant
	 * @param last The final time instant
	 */
	public MenuInit(String city1, String city2, String init, String last) {
		this.city1 = city1;
		this.city2 = city2;
		this.init = init;
		this.last = last;
	}
	
	/**
	 * This method is used to receive the gueet's preferences for the time range in order to calculate 
	 * the stats
	 */
	public void choice() {
		System.out.println("Please insert the two following cities You want compare: ");
		String c1 = input.next();
		String c2 = input.next();
		
		System.out.println("Please insert the initial instant and the final instant time used for calculating the stats");
		String i = input.next();
		String f = input.next();
		
		MenuInit menu = new MenuInit(c1, c2, i, f);
	}
}
