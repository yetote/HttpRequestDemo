package com.demo.yetote.httprequestdemo;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * com.demo.yetote.httprequestdemo
 *
 * @author Swg
 * @date 2018/4/1 21:56
 */
public class My<T, M> {
    public static <T, M> void sendJsonRequest(T requestInfo, String url, Class<M> requestClass, IDataListener listener) {
        IHttpListener httpListener = new JsonHttpListener<>(listener, requestClass);
        IHttpService httpService = new JsonHttpService();
        HttpTask<T> httpTask = new HttpTask<>(httpService, httpListener, url, requestInfo);
        ThreadPoolManager.getInstance().excute(httpTask);
    }

}
