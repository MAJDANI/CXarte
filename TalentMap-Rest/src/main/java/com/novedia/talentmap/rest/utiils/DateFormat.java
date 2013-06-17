package com.novedia.talentmap.rest.utiils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	
	
	 public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			    "dd-MM-yyyy");
	 
	public static Date parseStringToDate(String dateToParse) throws ParseException {
		Date date = null;
		dateFormat.setLenient(false);
		date = dateFormat.parse(dateToParse);
		return date;
	}
	 
	 
}
