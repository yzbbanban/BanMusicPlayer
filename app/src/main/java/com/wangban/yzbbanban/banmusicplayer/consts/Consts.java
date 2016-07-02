package com.wangban.yzbbanban.banmusicplayer.consts;

import android.os.Environment;

/**
 * Created by YZBbanban on 16/6/5.
 */
public interface Consts {
    int HANDLER_PICDAL_SUCCESS = 1;


    String YOUNG = "http://m.xxxiao.com/cat/shaonv";

    String BEAUTY_PATH = Environment.getExternalStorageDirectory() + "/DCIM/";


    int MAX_SIZE = 250;

    String TAG = "supergirl";
    String TAG2 = "superman";

    int NEW = 100;
    int HOT = 200;
    int BILLBOARD = 300;
    int KTV = 400;

    String PUT_MUSIC = "put_music";
    /**
     * 广播注册
     */
    String ACTION_START_PLAY = "ACTION_START_PLAY";

    String ACTION_UPDATE_PROGRESS = "ACTION_UPDATE_PROGRESS";


    int HANDLER_LOAD_BITMAP_SUCCESSS = 1;
    int HANDLER_SAVE_IMGER_TO_SD = 3;
    int HANDLER_SAVE_START = 6;
    int HANDLER_SET_WALLPAPER = 8;
    int REFRESH_COMPLETE = 2;
    int TOUCH_STOP = 50;
    int TOUCH_MOVE = 150;
    int TOUCH_FINISH = 150;
    int TOUCH_MOVE_X = 70;
    int TOUCH_MOVE_Y = 70;
    int MOVE_TO_RIGHT = 4;
    int MOVE_TO_LEFT = 5;
    int FINISH_IMG = 7;

    String EXTRA_PATH = "path";
    String EXTRA_IMAGE_PATH = "image_path";
    String EXTRA_IMAGE_POSITION = "image_position";
    // String EXTRA_IMAGES="images";

}
