package com.easier.library.net.zz;


import com.easier.library.net.base.ResponseBean;

/**
 * 服务器返回zhaozhe的数据对象
 * @author gj
 *
 */
public class ZZResponseBean extends ResponseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8912296114666292095L;
	private String resMsg = "";
	private String resCode = "";
	private String repBody="";

	public int getError() {
		return error;
	}
	public void setError(int error_code) {
		this.error = error_code;
	}
	
	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getRepBody() {
		return repBody;
	}

	public void setRepBody(String repBody) {
		this.repBody = repBody;
	}
}
