package com.demo.yetote.httprequestdemo;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * com.demo.yetote.httprequestdemo
 *
 * @author Swg
 * @date 2018/4/1 21:41
 */
public class JsonHttpService implements IHttpService {
    String url;
    private byte[] requsetData;
    private IHttpListener iHttpListener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requsetData = requestData;
    }

    @Override
    public void excute() {
        httpUrlConnPost();
    }

    @Override
    public void setHttpCallback(IHttpListener listener) {
        this.iHttpListener = listener;
    }

    HttpURLConnection connection = null;

    void httpUrlConnPost() {
        URL url = null;
        try {
            url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6000);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(3000);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.connect();

            OutputStream out = connection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            if (requsetData != null) {
                bos.write(requsetData);
            }
            bos.flush();
            out.close();
            bos.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                iHttpListener.onSuccess(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
            iHttpListener.onFailurl();
        } finally {
            connection.disconnect();
        }

    }

}
