package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUnit {
	
	public static Date DateFromString(String stringDate)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		try {
			return dateFormat.parse(stringDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String DateToString(Date date)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		return dateFormat.format(date);
	}
	
}
