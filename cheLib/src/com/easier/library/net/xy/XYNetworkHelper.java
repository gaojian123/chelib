package com.easier.library.net.xy;

import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.easier.library.net.base.NetworkHelper;
import com.easier.library.net.base.ResponseError;
import com.easier.library.net.base.SystemParams;
import com.easier.library.util.JsonUtils;

import android.content.Context;

public class XYNetworkHelper extends NetworkHelper<XYResponseBean>{
    private Context mContext;
    public XYNetworkHelper(Context context){
        super(context);
        this.mContext=context;
    }

	@Override
	protected void disposeVolleyError(VolleyError error,int requestCode,boolean isMore,Object tag) {
		ResponseError responseError = new ResponseError();
		responseError.setMore(isMore);
		responseError.setTag(tag);
		if (error instanceof NoConnectionError) {
			responseError.setErrorCode(SystemParams.NO_NETWORK);
			responseError.setErrorMsg("当前无网络连接");
		} else if (error instanceof ServerError) {
			responseError.setErrorCode(SystemParams.SERVER_ERROR);
			responseError.setErrorMsg("服务器繁忙");
		} else if (error instanceof TimeoutError) {
			responseError.setErrorCode(SystemParams.REQUEST_TIMEOUT);
			responseError.setErrorMsg("请求超时");
		} else {
			responseError.setErrorCode(SystemParams.NETWORK_ERROR);
			responseError.setErrorMsg("网络错误");
		}
		notifyErrorHappened(responseError, requestCode);
	}

    @Override
    protected void disposeResponse(String response,int requestCode,boolean isMore,Object tag){
        XYResponseBean bean = null;
        ResponseError error=new ResponseError();
        if(response != null){
            try{
            	bean=JsonUtils.resultData(response,XYResponseBean.class );
            	bean.setMore(isMore);
            	bean.setTag(tag);
                if("200".equals(bean.getCode())){
                    notifyDataChanged(bean,requestCode);
                }else{
                	if (null!=bean&&null!=bean.getMsg()) {
                		error.setErrorMsg(bean.getMsg());
					}
                	error.setBean(bean);
                    notifyErrorHappened(error,requestCode);
                }
            }catch(Exception e){
            	error.setErrorCode(SystemParams.RESPONSE_FORMAT_ERROR);
            	error.setErrorMsg("解析数据失败");
            	error.setMore(isMore);
            	error.setTag(tag);
                notifyErrorHappened(error ,requestCode);
            }
        }else{
        	error.setErrorCode(SystemParams.RESPONSE_IS_NULL);
        	error.setErrorMsg("数据为空");
        	error.setMore(isMore);
        	error.setTag(tag);
            notifyErrorHappened(error, requestCode);
        }
    }
}