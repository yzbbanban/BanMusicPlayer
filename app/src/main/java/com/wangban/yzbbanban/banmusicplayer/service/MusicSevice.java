package com.wangban.yzbbanban.banmusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by YZBbanban on 16/6/27.
 */
public class MusicSevice extends Service {
    private MediaPlayer player;


    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    private class MusicBinder extends Binder {
        public void playOrPause() {
            if (player.isPlaying()) {
                player.pause();
            } else {
                player.start();
            }
        }

        public void playMusic(String url) {
            try {
                player.reset();
                player.setDataSource(url);
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
