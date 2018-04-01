package com.demo.yetote.httprequestdemo;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * com.demo.yetote.httprequestdemo
 *
 * @author Swg
 * @date 2018/4/1 21:30
 */
public class HttpTask<T> implements Runnable {
    private IHttpService service;
    private IHttpListener listener;

    protected HttpTask(IHttpService service, IHttpListener listener, String url, T requestInfo) {
        this.service = service;
        this.listener = listener;
        service.setUrl(url);
        service.setHttpCallback(listener);
        if (requestInfo != null) {
            //把请求参数转换成json
            String requestCode = JSON.toJSONString(requestInfo);
            try {
                service.setRequestData(requestCode.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        service.excute();
    }
}
