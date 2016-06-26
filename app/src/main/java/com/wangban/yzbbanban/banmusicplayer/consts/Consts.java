package com.wangban.yzbbanban.banmusicplayer.consts;

import android.os.Environment;

/**
 * Created by YZBbanban on 16/6/5.
 */
public interface Consts {
    int HANDLER_PICDAL_SUCCESS = 1;
    String NEW_MUSIC_LIST="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=1&offset=0&size=50";
    String HOT_MUSIC_LIST="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=2&offset=0&size=50";
    String BILLBOARD_MUSIC_LIST="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=8&offset=0&size=50";
    String KTV_MUSIC_LIST="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=6&offset=0&size=50";


    String YOUNG = "http://m.xxxiao.com/cat/shaonv";

    String BEAUTY_PATH = Environment.getExternalStorageDirectory() + "/DCIM/";

    int MAX_SIZE = 250;

    String TAG = "supergirl";
    String TAG2 = "superman";

    int NEW=100;
    int HOT=200;
    int BILLBOARD=300;
    int KTV=400;

    int HANDLER_LOAD_BITMAP_SUCCESSS = 1;
    int HANDLER_SAVE_IMGER_TO_SD = 3;
    int HANDLER_SAVE_START=6;
    int HANDLER_SET_WALLPAPER=8;
    int REFRESH_COMPLETE = 2;
    int TOUCH_STOP = 50;
    int TOUCH_MOVE=150;
    int TOUCH_FINISH=150;
    int TOUCH_MOVE_X=70;
    int TOUCH_MOVE_Y=70;
    int MOVE_TO_RIGHT=4;
    int MOVE_TO_LEFT=5;
    int FINISH_IMG=7;

    String EXTRA_PATH = "path";
    String EXTRA_IMAGE_PATH = "image_path";
    String EXTRA_IMAGE_POSITION="image_position";
   // String EXTRA_IMAGES="images";

}
