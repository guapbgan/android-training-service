package com.example.demo14_service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class EchoFirebaseService extends JobService {
    private static final String TAG = "EchoFirebaseService";

    @Override
    public boolean onStopJob(@NonNull JobParameters job) {
        return false;

    }

    @Override
    public boolean onStartJob(@NonNull JobParameters job) {
        String message = job.getExtras().getString("message");
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
        return false;
    }
}
