package Utilities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {

	public DateConverter() {
		super();
	}

	public Long dateToSeconds(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
		try {
			java.util.Date dateObj = sdf.parse(date);
			long seconds = dateObj.getTime()/1000;

			return seconds;
		} catch (ParseException ParseE) {
			System.out.println("Errore di Parsing");
			System.out.println(ParseE);
		} catch (Exception e) {
			System.out.println("Errore generale");
			System.out.println(e);
		}
		return null;	
	}

}
