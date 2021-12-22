package Exception;

/**
 * Classe per l'eccezione personalizzata che viene in caso richiamata nel metodo compare di 
<<<<<<< HEAD
 * cityCompare quando l'istante iniziale è posticipato rispetto all'istante finale
 * @author Giansimone&Walter
=======
 * cityCompare nel caso in cui l'istante iniziale è posticipato rispetto all'istante finale
 *@author Giansimone&Walter
>>>>>>> e004a35eb6cd7ded5795d9ad1351757e30d077bf
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
