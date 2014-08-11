package org.ulibsoft.util.datatype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static String getYear(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}
	public static Date parse(String dateStr) throws ParseException
	{
		if ( dateStr == null || dateStr.length() == 0)
			return null;
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy");
		return sf.parse(dateStr);
	}
	public static String format(Date date) 
	{
		if ( date == null )
			return null;
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy");
		return sf.format(date);
	}
	public static String formatTime(Date date) 
	{
		if ( date == null )
			return null;
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		return sf.format(date);
	}
	public static Date getCurrentDate()
	{
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	public static String getCurrentDateString()
	{
		return format(getCurrentDate());
	}
	public static String getCurrentTimeString()
	{
		return formatTime(getCurrentDate());
	}
	public static Date addDaysToCurrent(int noDays)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, noDays);
		return cal.getTime();
	}	
	public static String addDaysToCurrentString(int noDays)
	{
		return format(addDaysToCurrent(noDays));
	}
	public static Date addDaysToDate(Date date,int noDays)
	{		
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		calDate.add(Calendar.DAY_OF_MONTH, noDays);
		return calDate.getTime();
	}
	
	public static int differenceFromCurrent(Date from)
	{
		Calendar toDate = Calendar.getInstance();
		int diffDays = 0;
		Calendar fromDate = Calendar.getInstance();
		fromDate.setTime(from);
		while( fromDate.before(toDate))
		{
			fromDate.add(Calendar.DAY_OF_MONTH, 1);
			if(! ( fromDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || fromDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ) )
				diffDays++;
				
		}
		return diffDays;
		
	}
	public static int differenceFromCurrent(String date) throws ParseException
	{
		return differenceFromCurrent(parse(date));
	}
	public static int compareDates(Date d1,Date d2)
	{
		if ( d1 == null || d2 == null )
			return 0;
		return d1.compareTo(d2);
	}
	public static java.sql.Date getSqlDate(Date utilDate)
	{
		if ( utilDate == null )
			return null;
		return new java.sql.Date(utilDate.getTime());
	}
}
