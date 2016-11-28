package com.example.user.brad18;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    private MediaPlayer mp;

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

    }
}
