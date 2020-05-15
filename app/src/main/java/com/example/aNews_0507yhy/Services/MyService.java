package com.example.aNews_0507yhy.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.aNews_0507yhy.utils.HttpUtil;
import com.example.aNews_0507yhy.utils.bean.MyJson;

import org.json.JSONException;

import java.io.IOException;

import androidx.annotation.Nullable;

public class MyService extends Service {
    HttpUtil httpUtil;
    String type;
    public static final String ACTION ="com.example.aNews_0507yhy.Services.MyService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        httpUtil = new HttpUtil();
        type = intent.getStringExtra("type");

        getDataFromService();
        return super.onStartCommand(intent, flags, startId);
    }

    private void getDataFromService() {
        new Thread(){
            @Override
            public void run() {
                try {
                    MyJson newsData = httpUtil.getNewsData(type);
                    Intent intent = new Intent();
                    intent.setAction(ACTION);
                    intent.putExtra("top",newsData);
                    sendBroadcast(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }
}
