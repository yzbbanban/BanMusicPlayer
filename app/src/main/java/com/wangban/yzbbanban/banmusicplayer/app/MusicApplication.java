package com.wangban.yzbbanban.banmusicplayer.app;

/**
 * Created by YZBbanban on 16/6/23.
 */

import android.app.Application;
import android.media.MediaPlayer;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.wangban.yzbbanban.banmusicplayer.entity.DetialImage;
import com.wangban.yzbbanban.banmusicplayer.entity.ImageInfo;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.entity.TechMessage;
import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;

import org.xutils.x;

import java.util.ArrayList;
import java.util.*;

public class MusicApplication extends Application {
    /**
     * 创建 MusicPlayer
     */
    private static MusicApplication context;

    private MediaPlayer player;
    private static MusicPlayer musicPlayer;
    //  private static Music music;
    private ArrayList<DetialImage> images;
    private static RequestQueue queue;
    private static TechMessage message;
    private static ImageInfo imageInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        context = this;
        queue = Volley.newRequestQueue(this);
        musicPlayer = new MusicPlayer();
        player = new MediaPlayer();
        message = new TechMessage();
        imageInfo = new ImageInfo();

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);

    }


//    /**
//     * 用于设置当前音乐数据，用过播放取出数据，交给展示界面
//     * @return
//     */
//    public static Music getMusic() {
//        return music;
//    }
//
//    public static void setMusic(Music music) {
//        MusicApplication.music = music;
//    }

    /**
     * 创建唯一的 MediaPlayer
     *
     * @return
     */
    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    /**
     * 创建 volley 队列
     *
     * @return
     */
    public static RequestQueue getQueue() {
        return queue;
    }

    /**
     * 图片数据源
     *
     * @return
     */
    public ArrayList<DetialImage> getImageData() {
        return images;
    }

    public void setImageData(ArrayList<DetialImage> images) {
        this.images = images;
    }

    /**
     * 直接获取 musicplayer用于操作记录的音乐数据，以及列表中的位置
     *
     * @return
     */
    public static MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public static TechMessage getMessage() {
        return message;
    }


    public static MusicApplication getContext() {
        return context;
    }

    public static ImageInfo getImageInfo() {
        return imageInfo;
    }

    public static void setImageInfo(ImageInfo imageInfo) {
        MusicApplication.imageInfo = imageInfo;
    }
}
