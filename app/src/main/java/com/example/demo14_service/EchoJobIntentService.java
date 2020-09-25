package com.example.demo14_service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class EchoJobIntentService extends JobIntentService {
    private static final String TAG = "EchoJobIntentService";
    private static final int JOB_ID = 4321;
    static void enqueueWork(Context context, Intent work){
        enqueueWork(context, EchoJobIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String message = "";
        if(intent != null){
            message = intent.getStringExtra("message");
        }
        int counter = 0;
        while (counter < 10){
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
