package com.demo.yetote.httprequestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My.sendJsonRequest(null, "http://wthrcdn.etouch.cn/weather_mini?city=北京", Weather.class, new IDataListener<Weather>() {
                    @Override
                    public void onSuccess(Weather weather) {
                        Toast.makeText(MainActivity.this, "城市"+weather.getResult().getHeWeather5().get(0).getBasic().getCity(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure() {
                        Log.e(TAG, "onFailure: " );
                    }
                });
            }
        });
    }
}
