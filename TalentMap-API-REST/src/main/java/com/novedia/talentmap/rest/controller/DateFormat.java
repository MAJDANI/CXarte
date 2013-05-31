package com.novedia.talentmap.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	
	
	 public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			    "yyyy-MM-dd");
	
	public static Date parseStringToDate(String dateToParse){
		Date date = null;
		try {
			date = dateFormat.parse(dateToParse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	 
	 
}
