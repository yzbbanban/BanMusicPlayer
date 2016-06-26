package com.wangban.yzbbanban.banmusicplayer.app;

/**
 * Created by YZBbanban on 16/6/23.
 */
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wangban.yzbbanban.banmusicplayer.entity.DetialImage;

import org.xutils.x;

import java.util.ArrayList;

public class MusicApplication extends Application{

    private ArrayList<DetialImage> images;
    private int position;
    private static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        queue= Volley.newRequestQueue(this);
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
}
