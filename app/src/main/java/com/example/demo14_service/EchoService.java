package com.example.demo14_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class EchoService extends Service {
    private static final String TAG = "ECHO_SERVICE";
    private boolean isRunning = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        Log.v(TAG,"one term startup");
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "one term destroy");
        isRunning = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"start with id="+startId);
        doEcho(intent.getStringExtra("message"));
        return super.onStartCommand(intent, flags, startId);
    }

    private void doEcho(String message) {
        new Thread(()->{
            int counter = 0;
            while (isRunning && counter < 10){
                Log.v(TAG,String.format("[%s]start process part:[%d]", message, counter));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        }).start();

    }
}