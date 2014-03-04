package com.mins5.share.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoutian
 * @since 2014-2-28
 */
public abstract class DateUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String TIME_FORMAT = "HH:mm:dd";
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:dd";
	
	public static Date parseDate(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(dateStr);
			return date;
		} catch(Exception e) {
			
		}
		return null;
	}
	
	public static String formatDate(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String dateStr = sdf.format(date);
			return dateStr;
		} catch(Exception e) {
			
		}
		return null;
	}
	
}
