package com.wangban.yzbbanban.banmusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;


import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;

/**
 * Created by YZBbanban on 16/6/27.
 * MusicService 的绑定
 */
public class MusicSevice extends Service implements Consts {
    private boolean isLoop = true;
    private MediaPlayer player;
    private Thread thread;
//    int j;

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
//        LogUtil.logInfo(TAG, "bindservice!!!!!!!!!!!!!");
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

//                    LogUtil.logInfo(TAG, "hello" + (j));
//                    j++;
                    Intent i = new Intent(ACTION_UPDATE_PROGRESS);
                    i.putExtra("current", player.getCurrentPosition());
                    i.putExtra("total", player.getDuration());
//                    LogUtil.logInfo(TAG, "hello:current " + player.getCurrentPosition() + "duration: " + player.getDuration());
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
        try {
            thread = null;
            player.release();
            player = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        private static MediaPlayer player = MusicApplication.getContext().getPlayer();

        //暂停音乐
        public static void playOrPause() {
            if (player.isPlaying()) {
                player.pause();
            } else {
                player.start();
            }
        }

        public static void pausePlayer() {
            player.pause();

        }

        //播放音乐
        public static void playMusic(String url) {
            try {
                //LogUtil.logInfo(TAG, "playMusicService: " + url);


                player.reset();
                player.setDataSource(url);
                if (MusicApplication.getMusicPlayer().getMusicListType() != LOCAL) {
//                    LogUtil.logInfo(TAG, "prepare: net ");
                    player.prepareAsync();
                } else {
//                    LogUtil.logInfo(TAG, "prepare: Local ");
                    player.prepare();
                    player.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
