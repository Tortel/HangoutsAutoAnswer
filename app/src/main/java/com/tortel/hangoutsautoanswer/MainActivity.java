package com.tortel.hangoutsautoanswer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bindService(new Intent(this, NotificationListener.class), new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName componentName) {
//
//            }
//        }, 0);
        startService(new Intent(this, NotificationListenerService.class));
    }
}
