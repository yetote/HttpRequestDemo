package com.demo.yetote.httprequestdemo;

import java.io.InputStream;

/**
 * com.demo.yetote.httprequestdemo
 * 响应
 *
 * @author Swg
 * @date 2018/4/1 21:25
 */
public interface IHttpListener {

    void onSuccess(InputStream stream);
    void onFailurl();

}
