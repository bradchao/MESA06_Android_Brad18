package com.example.user.brad18;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekbar;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = (SeekBar)findViewById(R.id.seekbar);
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter("brad"));
    }

    @Override
    public void finish() {
        Intent it = new Intent(this, MyService.class);
        stopService(it);

        unregisterReceiver(myReceiver);

        super.finish();
    }

    public void start(View v){
        Intent it = new Intent(this, MyService.class);
        startService(it);
    }
    public void pause(View v){
        Intent it = new Intent(this, MyService.class);
        it.putExtra("isPause", true);
        startService(it);
    }
    public void stop(View v){
        Intent it = new Intent(this, MyService.class);
        stopService(it);

    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int len = intent.getIntExtra("len", -1);
            int now = intent.getIntExtra("now", -1);
            if (len>0){
                seekbar.setMax(len);
            }
            if (now>0){
                seekbar.setProgress(now);
            }
        }
    }
}
