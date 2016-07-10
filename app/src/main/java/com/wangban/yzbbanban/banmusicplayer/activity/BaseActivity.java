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
 * 用于需要绑定 service 的 activity
 */
public class BaseActivity extends AppCompatActivity  {
    public MusicSevice.MusicBinder musicBinder;
    private Intent intent;
    private ServiceConnection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindService();
    }

    /**
     * 绑定 service
     */
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

    /**
     * 销毁 activity时解除绑定
     */
    @Override
    protected void onDestroy() {
        unbindService(conn);

        super.onDestroy();

    }

}
