/**
 * 
 */
package com.enuminfo.school.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kumar
 */
public class DateTimeUtil {

	public static String convertTimestamp2Date(Timestamp timestamp) {
		String dateStr = null;
		if (timestamp != null) {
			Date date = new Date(timestamp.getTime());
		    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    dateStr = formatter.format(date);
		}
		return dateStr;
	}
	
	public static java.sql.Date convertString2SqlDate(String strDate) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date convertedDate = null;
		try {
			convertedDate = (java.sql.Date) formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return convertedDate;
	}
	
	public static java.util.Date convertString2UtilDate(String strDate) {
		DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        java.util.Date convertedDate = null;
		try {
			convertedDate = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return convertedDate;
	}
	
	public static java.sql.Time convertString2Time(String strTime) {
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		java.sql.Time convertedTime = null;
		try {
			convertedTime = new java.sql.Time(formatter.parse(strTime).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedTime;
	}
	
	public static java.sql.Timestamp convertUtilDate2Timestamp() {
		java.util.Date utilDate = new java.util.Date(); 
		Timestamp timestamp = new Timestamp(utilDate.getTime());
		return timestamp;
	}
}
