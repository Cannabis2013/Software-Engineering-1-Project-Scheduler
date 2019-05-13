/*
 * Peter Bloch
 */

package static_Domain;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateFormatizer {
	public static LocalDate dateFromString(String date) throws DateTimeParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(date, formatter);
	}
	
	public static String dateToString(LocalDate date) throws DateTimeException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return date.format(formatter);
	}
	
	public static int getWeekOfDate(LocalDate date) throws DateTimeException
	{
		TemporalField tempField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    	return date.get(tempField);
	}
	
	public static int getWeekOfFormattedString(String formattedString) throws Exception
	{
		LocalDate date = dateFromString(formattedString);
		TemporalField tempField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    	return date.get(tempField);
	}
}
