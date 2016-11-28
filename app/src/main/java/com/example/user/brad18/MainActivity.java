package com.example.user.brad18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = (SeekBar)findViewById(R.id.seekbar);
    }

    @Override
    public void finish() {
        Intent it = new Intent(this, MyService.class);
        stopService(it);
        super.finish();
    }

    public void start(View v){
        Intent it = new Intent(this, MyService.class);
        startService(it);
    }
    public void pause(View v){

    }
    public void stop(View v){
        Intent it = new Intent(this, MyService.class);
        stopService(it);
    }
}
