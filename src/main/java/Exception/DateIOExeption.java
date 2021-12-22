package Exception;

/**
 * Classe per l'eccezione personalizzata che estende Exception. Viene lanciata nel momento
 * in cui vengono inserite date errate
 * @author Giansimone&Walter
 *
 */
public class DateIOExeption extends Exception{
	
	public DateIOExeption() {
		super();
	}
	
	public DateIOExeption(String msg) {
		super(msg);
	}
}
