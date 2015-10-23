package com.easier.library.net.xy;


import com.easier.library.net.base.ResponseBean;

/**
 * 服务器返回xiaoyi的数据对象
 * @author gj
 *
 */
public class XYResponseBean extends ResponseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2010160010850889314L;
	private String msg = "";
	private String code = "";
	private String data="";


	
	public String getMsg() {
		
		return msg;
	}
	public void setMsg(String message) {
		this.msg = message;
	}
	public String getCode() {
		return code.toString();
	}
	public void setCode(String result) {
		this.code = result;
	}

    public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	

	
}
