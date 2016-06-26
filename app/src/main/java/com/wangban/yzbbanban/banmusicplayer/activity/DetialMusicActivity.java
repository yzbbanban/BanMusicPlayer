package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.MusicListAdapter;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetDetialImpl;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class DetialMusicActivity extends AppCompatActivity implements View.OnClickListener, IViewNetDetial {
    @ViewInject(R.id.lv_detial_music)
    private ListView listView;
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.btn_music_list_back)
    private Button btnMusciListBack;
    private IPresenterNetDetial presenterNetDetial;
    private MusicListAdapter musicListAdapter;
    private List<Music> musics;
    private int type;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_music);
        x.view().inject(this);
        setSupportActionBar(toolbar);

        setData();
        setListeners();

    }

    private void setData() {
        intent =getIntent();
        type=intent.getIntExtra("type",1);
        presenterNetDetial=new PresenterNetDetialImpl(this,type);
        presenterNetDetial.loadAllMusics();
    }

    private void setListeners() {
        btnMusciListBack.setOnClickListener(this);

    }

    @Override
    public void setMusicData(List<Music> musics) {
       this.musics=musics;
    }

    @Override
    public void showMusicData() {
        musicListAdapter=new MusicListAdapter(this, (ArrayList<Music>) musics);
        listView.setAdapter(musicListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_music_list_back:
                finish();
                overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;

        }
    }
}
