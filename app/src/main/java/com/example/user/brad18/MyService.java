package com.example.user.brad18;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private MediaPlayer mp;
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        mp = MediaPlayer.create(this, R.raw.te);
        int len = mp.getDuration();
        Intent it = new Intent("brad");
        it.putExtra("len", len);
        sendBroadcast(it);

        timer = new Timer();
        timer.schedule(new MyTask(),0,500);

    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            if (mp !=null && mp.isPlaying()) {
                Intent it = new Intent("brad");
                it.putExtra("now", mp.getCurrentPosition());
                sendBroadcast(it);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mp != null && !mp.isPlaying()){
            mp.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp!=null){
            if (mp.isPlaying()){
                mp.stop();
            }
            mp.release();
            mp = null;
        }
        if (timer != null){
            timer.purge();
            timer.cancel();
            timer = null;
        }

    }

}
