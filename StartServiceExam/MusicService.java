package com.example.startserviceexam;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ServiceConfigurationError;

public class MusicService extends Service {
    MediaPlayer mp;

    @Override
    public void onCreate(){
        android.util.Log.i("ServiceTest", "service - onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("ServiceTest", "service - onStartCommand()");

        mp = MediaPlayer.create(this, R.raw.song2);
        mp.setLooping(true);
        mp.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("ServiceTest", "service - onDestroy()");
        mp.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
