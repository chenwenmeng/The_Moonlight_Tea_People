package com.bwie.util;


import com.lidroid.xutils.HttpUtils;



import com.lidroid.xutils.exception.HttpException;


import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * 类描述:解析JSON
 * 作者：陈文梦
 * 时间:2017/2/8 21:33
 * 邮箱:18310832074@163.com
 */

public abstract class Analysis_Json_Util {

    //请求数据
    public void getdate(String path){

        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                String result = responseInfo.result;
                analysis_Json(result);
            }
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }
    //创建抽象方法传递数据
    public abstract void analysis_Json(String s);

}
