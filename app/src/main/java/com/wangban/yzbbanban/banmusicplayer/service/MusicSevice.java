package com.wangban.yzbbanban.banmusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import android.app.Activity;
import android.util.Log;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;

/**
 * Created by YZBbanban on 16/6/27.
 */
public class MusicSevice extends Service implements Consts{
    @Override
    public void onCreate() {
        super.onCreate();
        MusicApplication.getContext().getPlayer().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }




    @Override
    public void onDestroy() {
        MusicApplication.getContext().getPlayer().release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    public static class MusicBinder extends Binder {
        public static void playOrPause() {
            if ( MusicApplication.getContext().getPlayer().isPlaying()) {
                MusicApplication.getContext().getPlayer().pause();
            } else {
                MusicApplication.getContext().getPlayer().start();
            }
        }

        public void playMusic(String url) {
            try {
                //player = new MediaPlayer();
                Log.i(TAG, "playMusic: "+url);
                MusicApplication
                        .getContext()
                        .getPlayer()
                        .reset();
                MusicApplication.getContext().getPlayer().setDataSource(url);
                MusicApplication.getContext().getPlayer().prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
