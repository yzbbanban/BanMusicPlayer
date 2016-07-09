package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class LocalMusicActivity extends AppCompatActivity implements Consts, View.OnClickListener {
    @ViewInject(R.id.btn_my_player_list_back)
    private Button btnPlayetListBack;
    @ViewInject(R.id.ibtn_my_player_local_music)
    private Button btnPlayetLocalMusic;



    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        x.view().inject(this);
        setListeners();
    }

    private void setListeners() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_my_player_list_back:

                break;
            case R.id.ibtn_my_player_local_music:
                MusicApplication.getMusicPlayer().setMusicListType(LOCAL);
                intent = new Intent(this,PlayActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;

        }
    }
}
