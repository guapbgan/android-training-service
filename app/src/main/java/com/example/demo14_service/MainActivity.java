package com.example.demo14_service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(view -> {
            Intent intent = new Intent(this, EchoIntentService.class);
            intent.putExtra("message", "" + System.currentTimeMillis());
            startService(intent);
        });
        findViewById(R.id.button4).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("message", "" + System.currentTimeMillis());
            EchoJobIntentService.enqueueWork(getApplicationContext(), intent); //getApplicationContext() 取得的context是application的，並非activity的context
        });
        findViewById(R.id.button5).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("message", "" + System.currentTimeMillis());
            FirebaseJobDispatcher dispatcher =
                    new FirebaseJobDispatcher(new GooglePlayDriver(this));
            Job myjob = dispatcher.newJobBuilder().setService(EchoFirebaseService.class).setTag("my-first-job-service").setExtras(bundle).build();
            dispatcher.mustSchedule(myjob);
        });

        Intent serviceIntent = new Intent(this, RandomService.class);
        bindService(serviceIntent, this, BIND_AUTO_CREATE);
        findViewById(R.id.button6).setOnClickListener(view -> {
            TextView textView = findViewById(R.id.textView);
            try {
                Double result = service.MyRandom(1234);
                textView.setText("result = " + result);
            } catch (RemoteException e) {
                e.printStackTrace();
                textView.setText("error=" + e.getMessage());
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, EchoService.class);
        switch (view.getId()){
            case R.id.button1:
                intent.putExtra("message", "" + System.currentTimeMillis());
                startService(intent);
                break;
            case  R.id.button2:
                stopService(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    IRandomService service = null;
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = IRandomService.Stub.asInterface(iBinder);
    }



    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        service = null; //解除連結
    }
}