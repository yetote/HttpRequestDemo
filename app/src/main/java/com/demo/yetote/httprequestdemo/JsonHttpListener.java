package com.demo.yetote.httprequestdemo;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * com.demo.yetote.httprequestdemo
 *
 * @author Swg
 * @date 2018/4/1 21:45
 */
public class JsonHttpListener<M> implements IHttpListener {
    IDataListener<M> listener;
    Class<M> responseClass;
    Handler handler = new android.os.Handler(Looper.myLooper());
    private static final String TAG = "JsonHttpListener";

    public JsonHttpListener(IDataListener<M> listener, Class<M> responseClass) {
        this.listener = listener;
        this.responseClass = responseClass;
    }

    @Override
    public void onSuccess(InputStream stream) {
        final String content = getContent(stream);
        final M response = JSON.parseObject(content, responseClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }

    @Override
    public void onFailurl() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.onFailure();
                }
            }
        });
    }

    private String getContent(InputStream in) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "getContent: " + e);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
