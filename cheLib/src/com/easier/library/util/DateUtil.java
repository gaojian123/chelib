package com.easier.library.util;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	// private static final String[] WeekDays = { "周日", "周一", "周二", "周三", "周四",
	// "周五", "周六" };
	//
	// @SuppressLint("SimpleDateFormat")
	// public static String getWeekDayByDate(String dateStr ,String pattern){
	// SimpleDateFormat format=new SimpleDateFormat(pattern);
	// Date date = null;
	// try {
	// date = format.parse(dateStr);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return "";
	// }
	// return WeekDays[date.getDay()];
	// }

	public static final String DEFAULT_PATTERN="yyyy-MM-dd";
	
	/**
	 * 根据毫秒, 返回 分钟:秒 的格式
	 * 
	 * @param ms
	 * @return
	 */
	public static String changeTimeFormat(int ms) {
		// 转换成秒数
		int s = ms / 1000;
		// 分钟
		int min = s / 60;
		// 取得剩余的秒数
		s = s % 60;

		StringBuilder builder = new StringBuilder();
		if (min < 10) {
			builder.append("0");
		}
		builder.append(min);
		builder.append(":");
		if (s < 10) {
			builder.append("0");
		}
		builder.append(s);

		return builder.toString();
	}

	public static long getTodayBaseTime() {
		long time = System.currentTimeMillis();
//		Date date = new Date(time);
//		long a = time - date.getHours() * 60 * 1000 * 60 - date.getMinutes() * 60 * 1000 - date.getSeconds() * 1000;
		long a=formatTimeToLong(formatTimeToString(time, DEFAULT_PATTERN), DEFAULT_PATTERN);
		return a;
	}

	private final static int minute = 60 * 1000;
	private final static int hour = 60 * minute;
	private final static long day = 24 * hour;

	public static String calculateTime(long time) {
		long TodayBaseTime = getTodayBaseTime();
		String str = "";
		// strs[0]=DateFormat.format("kk:mm", createTime).toString();
		long during = TodayBaseTime - time;

		if (System.currentTimeMillis() - time <= 2 * 60 * 1000) {
			// 两分钟
			str = "刚刚";
		} else if (System.currentTimeMillis() - time <= hour) {
			// 一小时
			str = (System.currentTimeMillis() - time) / minute + "分钟前";
		} else if (time - TodayBaseTime >= 0) {
			// 今天
			str = "今天" + DateFormat.format("kk:mm", time).toString();
		} else if (during > 0 && during <= 7 * day) {
			// 七天内
			str = (during / day + 1) + "天前";
		} else if (during > 0 && during <= 365 * day) {
			// 今年内
			str = DateFormat.format("MM-dd", time).toString();
		} else {
			str = DateFormat.format("yyyy-MM-dd", time).toString();
		}
		return str;
	}

	public static String formatTimeToString(long time, String type) {
		return DateFormat.format(type, time).toString();
	}

	public static long formatTimeToLong(String time, String type) {
		long day = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(type);
		try {
			Date startDate = myFormatter.parse(time);
			day = startDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 格式化倒计时
	 * 
	 * @param countDownTime
	 * @return
	 */
	public static String formatCountDownTime(long countDownTime) {
		String result = "";
		long minute = 60 * 1000;
		long hour = 60 * minute;
		long day = 24 * hour;
		int dayCount = (int) (countDownTime / day);
		int hourCount = (int) ((countDownTime % day) / hour);
		int minuteCount = (int) ((countDownTime % hour) / minute);
		int secondCount = (int) ((countDownTime % minute) / 1000);
		if (countDownTime > day) {
			result = dayCount + "天" + hourCount + "小时" + minuteCount + "分";
		} else if (hourCount > 0) {
			result = hourCount + "小时" + minuteCount + "分" + secondCount + "秒";
		} else if (minuteCount > 0) {
			result = minuteCount + "分" + secondCount + "秒";
		} else {
			result = secondCount + "秒";
		}
		return result;
	}

	public static String calcDaysFromToday(long timeValue) {
		long nowtime = System.currentTimeMillis();
		long offset = timeValue - nowtime;
		long dayValue = 1000 * 60 * 60 * 24;
		long hourValue = 1000 * 60 * 60;
		long minuteValue = 1000 * 60;
		int days = (int) (offset / dayValue);
		int hours = (int) ((offset % dayValue) / hourValue);
		int minutes = (int) (((offset % dayValue) % hourValue) / minuteValue);
		StringBuilder str = new StringBuilder();
		if (days > 0 || hours > 0 || minutes > 0) {
			if (days > 0) {
				str.append(days + "天");
			}

			if (hours > 0) {
				str.append(hours + "小时");
			}
			if (minutes > 0) {
				str.append(minutes + "分");
			}
		} else {
			str.append("0天0小时0分");
		}

		return str.toString();
	}

	/**
	 * 通过时间戳获取年月日
	 * 
	 * @param time
	 * @return
	 */
	public static int[] getYMD(long time) {
		int[] dateArr = new int[3];
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(time);
		calendar.setTime(date);
		dateArr[0] = calendar.get(Calendar.YEAR);
		dateArr[1] = calendar.get(Calendar.MONTH) + 1;
		dateArr[2] = calendar.get(Calendar.DATE);
		return dateArr;
	}

	public static final String[] weekdays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	/**
	 * 通过时间戳获取星期
	 * 
	 * @param time
	 * @return
	 */
	public static String getWeekdayByLong(long time) {
		Date date = new Date(time);
		return weekdays[date.getDay()];
	}

	/**
	 * 通过字符串日期获取星期
	 * @param time
	 * @param pattern 格式化模版
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getWeekdayByString(String time, String pattern) {
		SimpleDateFormat format ;
		format= new SimpleDateFormat(pattern);
		// 设置日期和周
		try {
			Date date = format.parse(time);
			return weekdays[date.getDay()];
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

}
