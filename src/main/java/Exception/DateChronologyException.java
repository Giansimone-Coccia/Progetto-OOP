package Exception;

/**
 * Classe per l'eccezione personalizzata che viene in caso richiamata nel metodo compare di 
 * cityCompare quando l'istante iniziale è posticipato rispetto all'istante finale
 * @author Giansimone&Walter
 *
 */
public class DateChronologyException extends Exception{
	
	public DateChronologyException() {
		super();
	}
	
	public DateChronologyException(String msg) {
		super(msg);
	}
}
