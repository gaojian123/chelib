package com.easier.library.net.base;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.easier.library.util.LogUtil;

import android.content.Context;

public abstract class NetworkHelper<T> {
	private Context context;

	public NetworkHelper(Context context) {
		this.context = context;
	}

	protected Context getContext() {
		return context;
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 * @param params
	 * @param requestCode
	 *            接口验证�?
	 * @param tag
	 * @return
	 */
	protected NetworkRequest getRequestForGet(String url, List<NameValuePair> params, final int requestCode,
			 final boolean isMore,final Object tag) {
		NetworkRequest networkRequest;
		if (params == null) {
			networkRequest = new NetworkRequest(url, new Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					disposeResponse(response, requestCode, isMore,tag);
					LogUtil.d("Amuro", response.toString());
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					disposeVolleyError(error, requestCode, isMore,tag);
				}
			});
		} else {
			networkRequest = new NetworkRequest(url, params, new Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					disposeResponse(response, requestCode,  isMore,tag);
					LogUtil.d("Amuro", response.toString());
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
					disposeVolleyError(error, requestCode, isMore,tag);
				}
			});
		}

		return networkRequest;
	}

	protected NetworkRequest getRequestForPost(String url, Map<String, String> params, final int requestCode,
			 final boolean isMore,final Object tag) {
		NetworkRequest networkRequest = new NetworkRequest(Method.POST, url, params, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				disposeResponse(response, requestCode,  isMore,tag);
				LogUtil.d("Amuro", response.toString());
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				disposeVolleyError(error, requestCode, isMore,tag);
			}
		});
		return networkRequest;

	}

	public void sendGETRequest(String url, List<NameValuePair> params, int requestCode) {
		VolleyQueueController.getInstance().getRequestQueue(getContext())
				.add(getRequestForGet(url, params, requestCode, false,""));
	}

	//

	public void sendGETRequest(String url, List<NameValuePair> params, boolean isMore, int requestCode) {
		VolleyQueueController.getInstance().getRequestQueue(getContext())
				.add(getRequestForGet(url, params, requestCode, isMore,""));
	}

	public void sendGETRequest(String url, List<NameValuePair> params, int requestCode, Object tag) {
		VolleyQueueController.getInstance().getRequestQueue(getContext())
				.add(getRequestForGet(url, params, requestCode, false,tag));
	}

	public void sendGETRequest(String url, List<NameValuePair> params, int requestCode, boolean isMore, Object tag) {
		VolleyQueueController.getInstance().getRequestQueue(getContext())
				.add(getRequestForGet(url, params, requestCode,  isMore,tag));
	}

	public void sendPostRequest(String url, Map<String, String> params, int requestCode) {
		VolleyQueueController.getInstance().getRequestQueue(context)
				.add(getRequestForPost(url, params, requestCode, false, ""));
	}

	public void sendPostRequest(String url, Map<String, String> params, boolean isMore ,int requestCode) {
		VolleyQueueController.getInstance().getRequestQueue(context)
				.add(getRequestForPost(url, params, requestCode, isMore, ""));
	}

	
	public void sendPostRequest(String url, Map<String, String> params, int requestCode, Object tag) {
		VolleyQueueController.getInstance().getRequestQueue(context)
				.add(getRequestForPost(url, params, requestCode, false,tag));
	}

	
	public void sendPostRequest(String url, Map<String, String> params, int requestCode, Object tag, boolean isMore) {
		VolleyQueueController.getInstance().getRequestQueue(context)
				.add(getRequestForPost(url, params, requestCode,  isMore,tag));
	}

	// @Override
	// public void onErrorResponse(VolleyError error){
	//// LogUtil.d("Amuro", error.getMessage());
	//
	// }
	//
	// @Override
	// public void onResponse(JSONObject response){
	//
	//
	// }

	protected abstract void disposeVolleyError(VolleyError error, int requestCode, boolean isMore,Object tag);

	protected abstract void disposeResponse(JSONObject response, int requestCode, boolean isMore, Object tag);

	private UIDataListener<T> uiDataListener;

	public void setUiDataListener(UIDataListener<T> uiDataListener) {
		this.uiDataListener = uiDataListener;
	}

	protected void notifyDataChanged(T data, int requestCode) {
		if (uiDataListener != null) {
			uiDataListener.onSuccess(data, requestCode);
		}
	}

	protected void notifyErrorHappened(ResponseError error, int requestCode) {
		if (uiDataListener != null) {
			uiDataListener.onError(error, requestCode);
		}
	}
}