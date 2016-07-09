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
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;

/**
 * Created by YZBbanban on 16/6/27.
 * MusicService 的绑定
 */
public class MusicSevice extends Service implements Consts {
    private boolean isLoop = true;
    private MediaPlayer player;
    private Thread thread;


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
        thread = new WorkThread();
        thread.start();
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
                if (player != null && player.isPlaying()) {
                    Intent i = new Intent(ACTION_UPDATE_PROGRESS);
                    i.putExtra("current", player.getCurrentPosition());
                    i.putExtra("total", player.getDuration());
                    sendBroadcast(i);
                }


            }
        }
    }

    /**
     * 销毁 service 时执行的操作
     */
    @Override
    public void onDestroy() {
        player.release();
        player = null;
        thread = null;
        super.onDestroy();
    }

    /**
     * 绑定 service 时执行的操作
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    /**
     * 绑定 service 的 Binder，替他类通过访问此方法，调用 MediaPlayer 的方法
     */
    public static class MusicBinder extends Binder {
        //暂停音乐
        public static void playOrPause() {
            if (MusicApplication.getContext().getPlayer().isPlaying()) {
                MusicApplication.getContext().getPlayer().pause();
            } else {
                MusicApplication.getContext().getPlayer().start();
            }
        }

        //播放音乐
        public static void playMusic(String url) {
            try {
                //LogUtil.logInfo(TAG, "playMusicService: " + url);
                MusicApplication.getContext().getPlayer().reset();
                MusicApplication.getContext().getPlayer().setDataSource(url);
                MusicApplication.getContext().getPlayer().prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
