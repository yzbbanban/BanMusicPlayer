package com.wangban.yzbbanban.banmusicplayer.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;


/**
 * Created by YZBbanban on 16/6/30.
 */
public class BaseActivity extends AppCompatActivity  {
    private MusicSevice.MusicBinder musicBinder;
    private Intent intent;
    private ServiceConnection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void bindService() {
        Intent service = new Intent(this, MusicSevice.class);
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                musicBinder = (MusicSevice.MusicBinder) service;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(service, conn, Service.BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);

    }

}
