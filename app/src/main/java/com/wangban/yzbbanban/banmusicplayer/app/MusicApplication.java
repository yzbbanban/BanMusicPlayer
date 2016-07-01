package com.wangban.yzbbanban.banmusicplayer.app;

/**
 * Created by YZBbanban on 16/6/23.
 */
import android.app.Application;
import android.media.MediaPlayer;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wangban.yzbbanban.banmusicplayer.entity.DetialImage;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;

import org.xutils.x;

import java.util.ArrayList;
import java.util.*;
public class MusicApplication extends Application{
    /**
     * 创建 MusicPlayer
     *
     */
    private static MusicApplication context;

    private MediaPlayer player;
    private static MusicPlayer musicPlayer;

    private ArrayList<DetialImage> images;
    private int position;
    private static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        context=this;
        queue= Volley.newRequestQueue(this);
        musicPlayer=new MusicPlayer();
        player=new MediaPlayer();
    }

    public  MediaPlayer getPlayer() {
        return player;
    }

    public  void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public static RequestQueue getQueue(){
        return queue;
    }
    public ArrayList<DetialImage> getImageData() {
        return images;
    }

    public void setImageData(ArrayList<DetialImage> images) {
        this.images = images;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * 直接获取 musicplayer
     * @return
     */
    public static MusicPlayer getMusicPlayer(){
        return musicPlayer;
    }
    public static MusicApplication getContext(){
        return context;
    }
}
