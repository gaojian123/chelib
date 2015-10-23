package com.easier.library.net.base;

public class ResponseError {
	private String errorCode;
	private String errorMsg;
	private ResponseBean bean;
	private boolean isMore;
	private Object tag;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public ResponseBean getBean() {
		return bean;
	}
	public void setBean(ResponseBean bean) {
		this.bean = bean;
	}
	public boolean isMore() {
		return isMore;
	}
	public void setMore(boolean isMore) {
		this.isMore = isMore;
	}
	public Object getTag() {
		return tag;
	}
	public void setTag(Object tag) {
		this.tag = tag;
	}
	
	

}
