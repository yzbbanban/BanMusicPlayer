package com.wangban.yzbbanban.banmusicplayer.util;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageButton;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;

/**
 * Created by YZBbanban on 16/7/10.
 * 用于封装动画的方法
 */
public class FramAnimationUtil {

    public static void framAnimationContrl(ImageButton ibtn){
        AnimationDrawable animation = null;
        if (MusicApplication.getContext().getPlayer() != null && MusicApplication.getContext().getPlayer().isPlaying()) {
            animation = (AnimationDrawable) ibtn.getBackground();
            animation.setOneShot(false);
            animation.start();
        } else {
            animation = (AnimationDrawable) ibtn.getBackground();
            animation.setOneShot(true);
            animation.start();
            ibtn.clearAnimation();
        }
    }

}
