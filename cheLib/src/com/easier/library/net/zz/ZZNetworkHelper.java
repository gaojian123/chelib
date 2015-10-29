package com.easier.library.net.zz;

import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.easier.library.net.base.NetworkHelper;
import com.easier.library.net.base.ResponseError;
import com.easier.library.net.base.SystemParams;
import com.easier.library.util.JsonUtils;

import android.content.Context;

public class ZZNetworkHelper extends NetworkHelper<ZZResponseBean>{
    
    public ZZNetworkHelper(Context context){
        super(context);
    }

	@Override
	protected void disposeVolleyError(VolleyError error,int requestCode,boolean isMore,Object tag) {
		ResponseError responseError=new ResponseError();
		responseError.setMore(isMore);
		responseError.setTag(tag);
		if (error instanceof NoConnectionError) {
			responseError.setErrorCode(SystemParams.NO_NETWORK);
			responseError.setErrorMsg("当前无网络连接");
		}else if(error instanceof ServerError){
			responseError.setErrorCode(SystemParams.SERVER_ERROR);
			responseError.setErrorMsg("服务器繁忙");
		}else if (error instanceof TimeoutError) {
			responseError.setErrorCode(SystemParams.REQUEST_TIMEOUT);
			responseError.setErrorMsg("请求超时");
		}else {
			responseError.setErrorCode(SystemParams.NETWORK_ERROR);
			responseError.setErrorMsg("网络错误");
		}
		notifyErrorHappened(responseError,requestCode);
	}

    @Override
    protected void disposeResponse(String response,int requestCode,boolean isMore,Object tag){
        ZZResponseBean bean = null;
        ResponseError error=new ResponseError();
        if(response != null){
            try{
//                String code = response.getString("code");
//                String msg = response.getString("msg");
//                String data = response.getString("data");
//                bean = new ResponseBean();
            	bean=JsonUtils.resultData(response,ZZResponseBean.class );
            	bean.setTag(tag);
            	bean.setMore(isMore);
                if("0".equals(bean.getResCode())){
                	
                    notifyDataChanged(bean,requestCode);
                }else{
                	error.setBean(bean);
                    notifyErrorHappened(error,requestCode);
                }
            }catch(Exception e){
            	error.setErrorCode(SystemParams.RESPONSE_FORMAT_ERROR);
            	error.setErrorMsg("解析数据失败");
            	error.setTag(tag);
            	error.setMore(isMore);
                notifyErrorHappened(error ,requestCode);
            }
        }else{
        	error.setErrorCode(SystemParams.RESPONSE_IS_NULL);
        	error.setErrorMsg("数据为空");
        	error.setTag(tag);
        	error.setMore(isMore);
            notifyErrorHappened(error, requestCode);
        }
    }
}