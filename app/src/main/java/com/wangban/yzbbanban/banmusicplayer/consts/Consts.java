package com.wangban.yzbbanban.banmusicplayer.consts;

import android.os.Environment;

/**
 * Created by YZBbanban on 16/6/5.
 */
public interface Consts {

    int HANDLER_PICDAL_SUCCESS = 1;

    String YOUNG = "http://m.xxxiao.com/cat/shaonv";

    String BEAUTY_PATH = Environment.getExternalStorageDirectory() + "/DCIM/";

    String MY_APP = "http://a.app.qq.com/o/simple.jsp?pkgname=com.wangban.yzbbanban.banmusicplayer";
    int MAX_SIZE = 250;

    String TAG = "supergirl";
    String TAG2 = "superman";

    int NEW = 100;
    int HOT = 200;
    int BILLBOARD = 300;
    int KTV = 400;
    int SEARCH = 500;
    int LOCAL = 600;

    int REPEAT = 1000;
    int RANDOM = 2000;
    int RECYCLE = 3000;

    /**
     * 保存，设置图片状态
     */
    int SAVE_BITMAP = 101;
    int SET_WALLPAPER = 102;
    int SET_BITMAP = 103;

    /**
     * 下载相关
     */
    int DOWNLOAD_FAILURE = 120;
    int DOWNLOAD = 121;
    int NOTIFICATION_ID = 100;

    String PUT_MUSIC = "put_music";
    /**
     * 广播注册
     */
    String ACTION_START_PLAY = "ACTION_START_PLAY";

    String ACTION_UPDATE_PROGRESS = "ACTION_UPDATE_PROGRESS";

    /**
     * 实体类 intent 传值（TechNews）
     */
    String TECHNEWS = "techNews";
    String IMAGE = "image";

    int REFRESH_COMPLETE = 2;

    String EXTRA_PATH = "path";
    String EXTRA_IMAGE_PATH = "image_path";
    String EXTRA_IMAGE_POSITION = "image_position";

}
