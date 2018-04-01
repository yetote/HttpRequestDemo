package com.demo.yetote.httprequestdemo;

/**
 * com.demo.yetote.httprequestdemo
 *请求
 * @author Swg
 * @date 2018/4/1 21:25
 */
public interface IHttpService {
    void setUrl(String url);

    void setRequestData(byte[] requestData);

    void excute();//wang luo cao zuo

    void setHttpCallback(IHttpListener listener);
}
