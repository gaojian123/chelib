package com.easier.library.net.base;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class VolleyQueueController {
	private static VolleyQueueController mInstance;

	public static VolleyQueueController getInstance() {
		if (mInstance == null) {
			mInstance = new VolleyQueueController();
		}
		return mInstance;
	}
	private RequestQueue mRequestQueue;
	
//	private VolleyQueueController(){
//		initRequestQueue();
//	}
//
//	private void initRequestQueue() {
//		mRequestQueue = Volley.newRequestQueue(DrivingApplication.getInstance());
//		mRequestQueue.start();
//	}
	public RequestQueue getRequestQueue(Context context){
		return Volley.newRequestQueue(context);
		
	}
}
