package com.example.administrator.myservicetest2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getService(){
            Log.i("brad", "getService()");
            return MyService.this;
        }
    }

    public MyService() {
        Log.i("brad", "MyService()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("brad", "onBind()");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("brad", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("brad", "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("brad", "onDestroy()");
    }
}
