package com.easier.library.net.base;


import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 服务器返回赵哲的数据对象
 * @author gj
 *
 */
public class ResponseBean implements Serializable{
	protected int error = 0;
    /**用于区分同一个请求执行不同的操作时所�?*/
	protected Object tag;
    /**当前的请求是否执行加载更多操�?*/
	protected boolean isMore;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public Object getTag() {
		return tag;
	}
	public void setTag(Object tag) {
		this.tag = tag;
	}
	public boolean isMore() {
		return isMore;
	}
	public void setMore(boolean isMore) {
		this.isMore = isMore;
	}
	




	
}
