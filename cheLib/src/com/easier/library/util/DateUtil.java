package com.easier.library.util;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String[] WeekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	@SuppressLint("SimpleDateFormat")
	public static String getWeekDayByDate(String dateStr ,String pattern){
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return WeekDays[date.getDay()];
	}
}
