package com.easier.library.util;

import android.util.Log;

/**
 * 日志输出工具（�?�过Constants.isLibDebug控制是否输出日志�?
 * @author gaojian
 *
 */
public class LogUtil {
	
	public static void i(String tag,String msg){
		if (Constants.isLibDebug) {
			Log.i(tag, msg);
		}
	}
	public static void i(String tag,String msg,Throwable tr){
		if (Constants.isLibDebug) {
			Log.i(tag, msg, tr);
		}
	}
	
	public static void e(String tag,String msg){
		if (Constants.isLibDebug) {
			Log.e(tag, msg);
		}
	}
	public static void e(String tag,String msg,Throwable tr){
		if (Constants.isLibDebug) {
			Log.e(tag, msg, tr);
		}
	}
	
	public static void v(String tag,String msg){
		if (Constants.isLibDebug) {
			Log.v(tag, msg);
		}
	}
	public static void v(String tag,String msg,Throwable tr){
		if (Constants.isLibDebug) {
			Log.v(tag, msg, tr);
		}
	}
	
	public static void w(String tag,String msg){
		if (Constants.isLibDebug) {
			Log.w(tag, msg);
		}
	}
	public static void w(String tag,String msg,Throwable tr){
		if (Constants.isLibDebug) {
			Log.w(tag, msg, tr);
		}
	}
	
	public static void d(String tag,String msg){
		if (Constants.isLibDebug) {
			Log.d(tag, msg);
		}
	}
	public static void d(String tag,String msg,Throwable tr){
		if (Constants.isLibDebug) {
			Log.d(tag, msg, tr);
		}
	}

}
