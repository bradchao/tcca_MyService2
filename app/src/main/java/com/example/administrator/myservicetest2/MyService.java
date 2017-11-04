package com.example.administrator.myservicetest2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Contacts;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private UIHander hander = null;
    private MainActivity.MyHandler handler = null;

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

    void doSomething(String name){
        Log.i("brad", "Hello, " + name);

        Message mesg = new Message();
        Bundle b = new Bundle();
        b.putString("what", "Hello, " + name);
        mesg.setData(b);

        //handler.sendMessage(mesg);

        hander.sendEmptyMessage(0);


    }

    void setHandler(MainActivity.MyHandler handler){
        this.handler = handler;
    }

    void setMainActivity(MainActivity mainActivity){
        if (hander == null){
            hander = new UIHander(mainActivity);
        }

    }

    private class UIHander extends Handler {
        private MainActivity mainActivity;
        UIHander(MainActivity mainActivity){
            this.mainActivity = mainActivity;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mainActivity.getTv().setText("Hello, World");

        }
    }

}
