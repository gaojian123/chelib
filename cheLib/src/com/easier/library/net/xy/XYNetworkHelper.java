package com.easier.library.net.xy;

import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.easier.library.net.base.NetworkHelper;
import com.easier.library.net.base.ResponseError;
import com.easier.library.net.base.SystemParams;
import com.easier.library.util.JsonUtils;

import android.content.Context;

public class XYNetworkHelper extends NetworkHelper<XYResponseBean>{
    
    public XYNetworkHelper(Context context){
        super(context);
    }

	@Override
	protected void disposeVolleyError(VolleyError error,int requestCode,boolean isMore,Object tag) {
		ResponseError responseError=new ResponseError();
		responseError.setMore(isMore);
		responseError.setTag(tag);
		responseError.setErrorCode(SystemParams.VOLLEY_ERROR_CODE);
		if (null==error) {
			responseError.setErrorMsg("null");
		}else {
			responseError.setErrorMsg("网络错误");
		}
		notifyErrorHappened(responseError,requestCode);
	}

    @Override
    protected void disposeResponse(String response,int requestCode,boolean isMore,Object tag){
        XYResponseBean bean = null;
        ResponseError error=new ResponseError();
        if(response != null){
            try{
//                String code = response.getString("code");
//                String msg = response.getString("msg");
//                String data = response.getString("data");
//                bean = new ResponseBean();
            	bean=JsonUtils.resultData(response,XYResponseBean.class );
            	bean.setMore(isMore);
            	bean.setTag(tag);
                if("200".equals(bean.getCode())){
                	
                    notifyDataChanged(bean,requestCode);
                }else{
                	error.setBean(bean);
                    notifyErrorHappened(error,requestCode);
                }
            }catch(Exception e){
            	error.setErrorCode(SystemParams.RESPONSE_FORMAT_ERROR);
            	error.setErrorMsg("Response format error");
            	error.setMore(isMore);
            	error.setTag(tag);
                notifyErrorHappened(error ,requestCode);
            }
        }else{
        	error.setErrorCode(SystemParams.RESPONSE_IS_NULL);
        	error.setErrorMsg("Response is null!");
        	error.setMore(isMore);
        	error.setTag(tag);
            notifyErrorHappened(error, requestCode);
        }
    }
}