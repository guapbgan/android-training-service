package com.example.demo14_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import java.util.Random;

public class RandomService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IRandomService.Stub() {
            @Override
            public double MyRandom(long seed) throws RemoteException {
                return new Random(seed).nextDouble();
            }
        };
    }
}
