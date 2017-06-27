package com.dayuan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = formatter.format(currentTime);
		return currentDate;
	}

	public static String getDayStartTime(String date) {
		String time = date.substring(0, 10) + " 00:00:00";
		return time;
	}

	public static String getDayEndTime(String date) {
		String time = date.substring(0, 10) + " 23:59:59";
		return time;
	}
}
