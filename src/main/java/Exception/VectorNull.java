package Exception;

/**
 * Classe che estende la classe Exception. Essa descrive una eccezione personalizzata che viene
 * lanciata nel momento in cui il vettore che si sta considerando è vuoto
 * @author Giansimone&Walter
 *
 */
public class VectorNull extends Exception{
	
	public VectorNull() {
		super();
	}
	
	public VectorNull(String msg) {
		super(msg);
	}
}
