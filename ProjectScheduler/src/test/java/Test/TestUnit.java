package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class TestUnit {
	
	public static LocalDate DateFromString(String stringDate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			return LocalDate.parse(stringDate, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	public static String DateToString(LocalDate date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return date.format(formatter);
	}
	
}
