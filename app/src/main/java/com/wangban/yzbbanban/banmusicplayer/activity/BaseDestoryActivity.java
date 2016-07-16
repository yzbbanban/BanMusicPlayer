package com.wangban.yzbbanban.banmusicplayer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class BaseDestoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            MusicApplication app = (MusicApplication) getApplication();
            app.activities.add(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        try {
            MusicApplication app = (MusicApplication) getApplication();
            app.activities.remove(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
