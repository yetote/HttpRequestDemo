package com.demo.yetote.httprequestdemo;

/**
 * com.demo.yetote.httprequestdemo
 *
 * @author Swg
 * @date 2018/4/1 21:47
 */
public interface IDataListener<M> {
    void onSuccess(M m);
    void onFailure();
}
