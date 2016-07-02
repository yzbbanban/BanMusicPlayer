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
 * MusicService 的绑定
 */
public class MusicSevice extends Service implements Consts {
    private boolean isLoop = true;
    private MediaPlayer player;
    @Override
    public void onCreate() {
        super.onCreate();
        player = MusicApplication.getContext().getPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                Intent i = new Intent(ACTION_START_PLAY);
                sendBroadcast(i);
            }
        });
        //启动工作线程  每1秒给Activity发一次广播
        new WorkThread().start();

    }

    //每1秒给Activity发一次广播
    class WorkThread extends Thread {
        @Override
        public void run() {
            while (isLoop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //发送广播
                if (player.isPlaying()) {
                    Intent i = new Intent(ACTION_UPDATE_PROGRESS);
                    i.putExtra("current", player.getCurrentPosition());
                    i.putExtra("total", player.getDuration());
                    sendBroadcast(i);
                }
            }
        }
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
            if (MusicApplication.getContext().getPlayer().isPlaying()) {
                MusicApplication.getContext().getPlayer().pause();
            } else {
                MusicApplication.getContext().getPlayer().start();
            }
        }

        public void playMusic(String url) {
            try {
                //player = new MediaPlayer();
                Log.i(TAG, "playMusic: " + url);
                MusicApplication.getContext().getPlayer().reset();
                MusicApplication.getContext().getPlayer().setDataSource(url);
                MusicApplication.getContext().getPlayer().prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
