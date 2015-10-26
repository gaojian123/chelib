package com.easier.library.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

import com.easier.library.util.LogUtil;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author
 *
 *	公用方法类库
 *
 */
public class PublicMethod {

	private static int CMNET = 3;
	private static int CMWAP = 2;
	private static int WIFI = 1;

	
	
	/**
	 * 验证手机号
	 * @param num
	 * @return
	 */
	public static boolean verifyMobileNumber(String num){
		if(!TextUtils.isEmpty(num)){
			String regExp = "^13[0-9]{1}[0-9]{8}$|15[0125689]{1}[0-9]{8}$|18[0-3,5-9]{1}[0-9]{8}$|17[0-9]{1}[0-9]{8}";
			return Pattern.compile(regExp).matcher(num).matches();
		}
		return false;
	}

	
	
	

	/**
	 * 将dip转换成px
	 * 
	 * @param dip
	 * @return
	 */
	public static int getPxInt(float dip, Context context) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context
				.getResources().getDisplayMetrics());
	}

	/** 
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
	 */  
	public static int getDiptopx(Context context, float dpValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (dpValue * scale + 0.5f);  
	}  


	/**
	 * 获取屏幕密度 默认为1
	 * 
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context) {
		if (context instanceof Activity) {
			Activity activity = (Activity) context;
			DisplayMetrics metric = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
			return metric.density;
		}
		return 1;
	}

	public static void outMM(String msg){
			float total = (float)(Math.round(Runtime.getRuntime().totalMemory()/1024/1024*100)/100.0);
			float free = (float)(Math.round(Runtime.getRuntime().freeMemory()/1024/1024*100)/100.0);
			LogUtil.d("mm",msg + "---------总共内存："+total+" | 使用内存："+(total-free)+"  |  剩余内存："+free);
		//		PetsayLogUtil.d("mm", "===========已使用的内存大小："+Debug.getNativeHeapAllocatedSize()+
		//				"  |  剩余的内存大小: "+ Debug.getNativeHeapFreeSize()+"  |  总的内存大小: "+Debug.getNativeHeapSize());
	}


	/**
	 * 修改tab类型button组的背景
	 * @param views 控件数组 
	 * @param index 被选中的索引
	 * @param selectedBg 被选中背景
	 * @param defaultBg 默认背景
	 */
	public static void changeBtnBgImg(View[] views,int index,int selectedBg,int defaultBg){
		for (int i = 0; i < views.length; i++) {
			if (i==index) {
				views[i].setBackgroundResource(selectedBg);
			}else {
				views[i].setBackgroundResource(defaultBg);
			}
		}
	}
	public static void changeTextViewColor(TextView[] views,int index,int selectedColor,int defaultColor){
		for (int i = 0; i < views.length; i++) {
			if (i==index) {
				views[i].setTextColor(selectedColor);
			}else {
				views[i].setTextColor(defaultColor);
			}
		}
	}

	public static void changeBtnBgImg(View[] views,int index,Drawable selectedBg,Drawable defaultBg){
		for (int i = 0; i < views.length; i++) {
			if (i==index) {
				views[i].setBackgroundDrawable(selectedBg);
			}else {
				views[i].setBackgroundDrawable(defaultBg);
			}
		}
	}

	/**
	 * 修改tab类型button组的背景
	 * @param views 控件数组 
	 * @param index 被选中的索引
	 * @param selectedColor 被选中背景
	 * @param defaultColor 默认背景
	 */
	public static void changeBtnBgColor(View[] views,int index,int selectedColor,int defaultColor){
		for (int i = 0; i < views.length; i++) {
			if (i==index) {
				views[i].setBackgroundColor(selectedColor);
			}else {
				views[i].setBackgroundColor(defaultColor);
			}
		}
	}

	/**
	 * 获取手机屏幕的高度
	 * @param context
	 * @return
	 */
	public static int getDisplayHeight(Context context){
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();//new DisplayMetrics();
		return dm.heightPixels;
	}

	/**
	 * 获取手机屏幕的宽度
	 * @param context
	 * @return
	 */
	public static int getDisplayWidth(Context context){
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();//new DisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * byte[] 转为file
	 * @param b
	 * @param outputFile 输出文件路径
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile){
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null){
				try {
					stream.close();
				} catch (IOException e1){
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 检查SD卡状态是否可用
	 * @return
	 */
	public static boolean checkSDCard(){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return true;
		return false;
	}


	/**
	 * gzip
	 * 
	 * @param data
	 * @return byte[]
	 */
	public static byte[] decompress2(byte[] data) {
		byte[] output = new byte[0];

		Inflater decompresser = new Inflater();
		decompresser.reset();
		decompresser.setInput(data);

		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!decompresser.finished()) {
				int i = decompresser.inflate(buf);
				o.write(buf, 0, i);
			}
			output = o.toByteArray();
		} catch (Exception e) {
			output = data;
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		decompresser.end();
		return output;
	}

	

	
	/**
	 * 判断后退栈的顶端是否为指定的Context
	 * @param context
	 * @return
	 */
	public static boolean isAppRunning(Context context) {
		String packageName = getPackageName(context);
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(100);
		for (RunningTaskInfo info : list) {
			if (info.topActivity.getPackageName().equals(packageName)
					&& info.baseActivity.getPackageName().equals(packageName)) {
				return true;
			}
		}
		return false;
	}

    /**
     * 检查App是否正在前台运行
     * @param context
     * @return
     */
    public static boolean isAppTopRuning(Context context){
        String packageName = getPackageName(context);
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(1);
        for (RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName)
                    && info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

	/**
	 * 获取包名
	 * @param context
	 * @return
	 */
	public static String getPackageName(Context context) {
		String packageName = context.getPackageName();
		return packageName;
	}

	/**
	 * 获取版本号
	 * @return 当前应用的版本号
	 */
	public static String getVersion(Context context) {
		PackageManager manager = context.getPackageManager();
		String version="";
		try {
			PackageInfo info  = manager.getPackageInfo(context.getPackageName(), 0);
			version= info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}


	

	/**
	 * short类型数组转换byte数组
	 * @param buf
	 * @return
	 */
	public static byte[] shortToByteSmall(short[] buf){

		byte[] bytes = new byte[buf.length * 2];
		for(int i = 0, j = 0; i < buf.length; i++, j+=2){
			short s = buf[i];

			byte b1 = (byte) (s & 0xff);
			byte b0 = (byte) ((s >> 8) & 0xff);

			bytes[j] = b1;
			bytes[j+1] = b0;
		}
		return bytes;

	}

	/**
	 * 根据毫秒, 返回 分钟:秒 的格式  
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

	public static long getTodayBaseTime()
	{
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		long a = time - date.getHours() * 60 * 1000 * 60 - date.getMinutes()* 60 * 1000 - date.getSeconds() * 1000;
		return a;
	}
	private final static int minute=60*1000;
	private final static int hour=60*minute;
	private final static long day=24*hour;
	public static String calculateTime(long time)
	{
		long TodayBaseTime=getTodayBaseTime();
		String str="";
		//		strs[0]=DateFormat.format("kk:mm", createTime).toString();
		long during=TodayBaseTime-time;

		if (System.currentTimeMillis()-time<=2*60*1000) {
			//两分钟
			str="刚刚";
		}else if (System.currentTimeMillis()-time<=hour) {
			//一小时
			str=(System.currentTimeMillis()-time)/minute+"分钟前";
		}else if (time-TodayBaseTime>=0) {
			//今天
			str="今天"+DateFormat.format("kk:mm", time).toString();
		}else if (during>0&&during<=7*day) {
			//七天内
			str=(during/day+1)+"天前";
		}else if (during>0&&during<=365*day) {
			//今年内
			str=DateFormat.format("MM-dd", time).toString();
		}else {
			str=DateFormat.format("yyyy-MM-dd", time).toString();
		}
		return str;
	}
	
	
	public static String formatTimeToString(long time, String type)
	{
		return DateFormat.format(type, time).toString();
	}

	public static long formatTimeToLong(String time, String type)
	{
		long day = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(type);
		try
		{
			Date startDate = myFormatter.parse(time);
			day = startDate.getTime();
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 格式化倒计时
	 * @param countDownTime
	 * @return
	 */
	public static String formatCountDownTime(long countDownTime){
		String result="";
		long minute=60*1000;
		long hour=60*minute;
		long day=24*hour;
		int dayCount=(int) (countDownTime/day);
		int hourCount=(int) ((countDownTime%day)/hour);
		int minuteCount=(int) ((countDownTime%hour)/minute);
		int secondCount=(int) ((countDownTime%minute)/1000);
		if (countDownTime>day) {
			result=dayCount+"天"+hourCount+"小时"+minuteCount+"分";
		}else if(hourCount>0){
			result=hourCount+"小时"+minuteCount+"分"+secondCount+"秒";
		}else if (minuteCount>0) {
			result=minuteCount+"分"+secondCount+"秒";
		}else {
			result=secondCount+"秒";
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


	//	
	//	/**
	//	 * 根据URL获取文件名不带扩展名
	//	 * @param url
	//	 * @return
	//	 */
	//	public static String getFileName(String url){
	//		if(TextUtils.isEmpty(url))
	//			return "";
	//		return url.substring(url.lastIndexOf("/"),url.lastIndexOf("."));
	//	}


	/**
	 * 从assets读取图片
	 * @param fileName
	 * @return
	 */
	public static Bitmap getImageFromAssetsFile(Context context,String fileName) {  
		Bitmap image = null;  
		AssetManager am = context.getResources().getAssets();  
		try  
		{  
			InputStream is = am.open(fileName);  
			image = BitmapFactory.decodeStream(is);  
			is.close();  
		}  
		catch (IOException e)  
		{  
			e.printStackTrace(); 
		}  
		return image;  

	} 


	/**
	 * 获取assets文件夹下文件的文本内容
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String getStringFromAssets(Context context, String fileName) {
		try {
			InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			String Result = "";
			while ((line = bufReader.readLine()) != null)
				Result += line;
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 显示软键盘
	 * @param editText
	 * @param index
	 */
	public static void openSoftKeyBoard(EditText editText,int index) {
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		InputMethodManager inputManager =(InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(editText, index);
	}

	/**
	 * 关闭软键盘
	 * @param context
	 */
	public static void closeSoftKeyBoard(Context context,EditText editText){
//		editText.setFocusableInTouchMode(false);
//		editText.setFocusable(false);
		InputMethodManager imm = (InputMethodManager)context. getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

		//		     InputMethodManager imm = (InputMethodManager)context. getSystemService(Context.INPUT_METHOD_SERVICE);
		//		     imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
		//		     imm.h
	}


	/**
	 * 获取软键盘显示状态
	 * @param editText
	 * @return true 显示  false 隐藏状态
	 */
	public static boolean isSoftKeyBoardShow(Context context ,EditText editText){
		InputMethodManager imm = (InputMethodManager)context. getSystemService(Activity.INPUT_METHOD_SERVICE);
		if(imm.hideSoftInputFromWindow(editText.getWindowToken(), 0))
		{  
			//软键盘已弹出  
			//imm.showSoftInput(editText,0);  
			return true;
		}  
		return false;
	}

	/**
	 * 获取一定范围随机数
	 * @param maxRange
	 * @return
	 */
	public static int getRangeRandomNum(int maxRange){
		return (int)(Math.random()*(maxRange+1));
	}

	/**
	 * 设置Matrix随机平移量
	 * @param maxWidth
	 * @param maxHeight
	 * @param bmpWidthd
	 * @param mx
	 */
	public static Matrix setRandomPosition(int maxWidth,int maxHeight,int bmpWidthd,int bmpHeight,Matrix mx){
		int mw = maxWidth - bmpWidthd;
		int mh = maxHeight - bmpHeight;
		mx.postTranslate(getRangeRandomNum(mw), getRangeRandomNum(mh));
		return mx;
	}

	

	/**
	 * 初始化列表宠物背景图的布局
	 * @param view
	 * @param width
	 */
	public static void initPetalkViewLayout(View view, int width){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		layoutParams.height = width;
		layoutParams.width = width;
		view.setLayoutParams(layoutParams);
	}

    /**
     * 初始化故事封面Layout布局
     * @param storyTitle
     * @param storyTime
     * @param imgCover
     * @param size
     */
    public static void initStoryCoverViewLayout(View storyTitle,View storyTime,View imgCover,int size){
        if(storyTitle != null){
            ViewGroup.MarginLayoutParams titleParam = (ViewGroup.MarginLayoutParams) storyTitle.getLayoutParams();
            titleParam.leftMargin = (int) (40.0f/640.0f*size);
            titleParam.topMargin =  (int) (116.0f/640.0f*size);
            titleParam.width = size - titleParam.leftMargin;
            storyTitle.setLayoutParams(titleParam);
        }
        if(storyTime != null){
            ViewGroup.MarginLayoutParams timeParam = (ViewGroup.MarginLayoutParams) storyTime.getLayoutParams();
            timeParam.leftMargin = (int) (40.0f/640.0f*size);
            timeParam.topMargin = (int) (164.0f/640.0f*size);
            storyTime.setLayoutParams(timeParam);
        }

        if(imgCover != null){
            ViewGroup.MarginLayoutParams imgParam = (ViewGroup.MarginLayoutParams) imgCover.getLayoutParams();
            imgParam.leftMargin = (int) (40.0f/640.0f*size);
            imgParam.topMargin = (int) (224.0f/640.0f*size);
            imgParam.width = (int) (560.0f/640.0f*size);
            imgParam.height = (int) (320.0f/640.0f*size);
            imgCover.setLayoutParams(imgParam);
        }
    }



	/**
	 * 动态设置ListView的高度
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) { 
		if(listView == null) return;

		ListAdapter listAdapter = listView.getAdapter(); 
		if (listAdapter == null) { 
			// pre-condition 
			return; 
		} 

		int totalHeight = 0; 
		for (int i = 0; i < listAdapter.getCount(); i++) { 
			View listItem = listAdapter.getView(i, null, listView); 
			listItem.measure(0, 0); 
			totalHeight += listItem.getMeasuredHeight(); 
		} 

		ViewGroup.LayoutParams params = listView.getLayoutParams(); 
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)); 
		listView.setLayoutParams(params); 
	}


	private static long lastClickTime;  
	/**
	 * 禁止快速连续点击
	 * @return
	 */
    public static boolean isFastDoubleClick() {  
        long time = System.currentTimeMillis();     
        if ( time - lastClickTime < 500) {     
            return true;     
        }     
        lastClickTime = time;     
        return false;     
    }
    
    /**
     * 通过时间戳获取年月日
     * @param time
     * @return
     */
    public static int[] getYMD(long time){
    	int[] dateArr=new int[3];
    	Calendar calendar=Calendar.getInstance();
    	Date date=new Date(time);
    	calendar.setTime(date);
    	dateArr[0]=calendar.get(Calendar.YEAR);
    	dateArr[1]=calendar.get(Calendar.MONTH)+1;
    	dateArr[2]=calendar.get(Calendar.DATE);
    	return dateArr;
    }

    

    /**
     * view转成bitmap
     * @param view
     * @return
     */
	public static Bitmap convertViewToBitmap(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}
	
	public static float AddFloat(float float1, float float2) {
		BigDecimal b1 = new BigDecimal(float1);
		BigDecimal b2 = new BigDecimal(float2);
		return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

	}
	public static float MinusFloat(float float1,float float2){
		BigDecimal b1 = new BigDecimal(float1);
		BigDecimal b2 = new BigDecimal(float2);
		return b1.subtract(b2).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public static float formatFloat(int decimalLenth,float value){
		BigDecimal b = new BigDecimal(value);
		return b.setScale(decimalLenth, BigDecimal.ROUND_HALF_UP).floatValue();
	}

}
