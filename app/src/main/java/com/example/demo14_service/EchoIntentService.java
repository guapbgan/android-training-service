package com.example.demo14_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class EchoIntentService extends IntentService {
    private static final String TAG = "EchoIntentService";
    public EchoIntentService() {
        super(EchoIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int counter = 0;
        String message = "";
        if(intent != null){
            message = intent.getStringExtra("message");
        }
        while (counter<10){
            Log.v(TAG,String.format("[%s]start process part:[%d]", message, counter));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }
}
