package Exception;

/**
 * Classe per l'eccezione personalizzata che viene in caso richiamata nel metodo compare di 
 * cityCompare nel caso in cui l'istante iniziale è posticipato rispetto all'istante finale
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
