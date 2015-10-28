package com.easier.library.net.zz;

import org.json.JSONObject;

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
		responseError.setErrorCode(SystemParams.VOLLEY_ERROR_CODE);
		responseError.setMore(isMore);
		responseError.setTag(tag);
		if (null==error) {
			responseError.setErrorMsg("null");
		}else {
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
            	error.setErrorMsg("Response format error");
            	error.setTag(tag);
            	error.setMore(isMore);
                notifyErrorHappened(error ,requestCode);
            }
        }else{
        	error.setErrorCode(SystemParams.RESPONSE_IS_NULL);
        	error.setErrorMsg("Response is null!");
        	error.setTag(tag);
        	error.setMore(isMore);
            notifyErrorHappened(error, requestCode);
        }
    }
}