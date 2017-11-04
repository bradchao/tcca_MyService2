package com.example.administrator.myservicetest2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyService myService;
    private MyHandler handler;
    private TextView tv;
    private boolean isBound;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder)service;
            myService = binder.getService();
            isBound = true;
            myService.setHandler(handler);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound= false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);
        handler = new MyHandler();

    }
    public void test1(View view){
        myService.doSomething("Brad");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent it = new Intent(this, MyService.class);
        bindService(it, mConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isBound){
            unbindService(mConnection);
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String mesg = msg.getData().getString("what", "no data");
            tv.setText(mesg);


        }
    }


}
