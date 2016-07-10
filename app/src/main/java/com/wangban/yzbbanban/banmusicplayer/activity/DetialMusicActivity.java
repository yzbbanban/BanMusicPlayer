package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.MusicListAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetDetialImpl;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class DetialMusicActivity extends BaseActivity implements Consts, View.OnClickListener, IViewNetDetial {
    @ViewInject(R.id.lv_detial_music)
    private ListView listView;
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.btn_music_list_back)
    private Button btnMusciListBack;
    private IPresenterNetDetial presenterNetDetial;
    private MusicListAdapter musicListAdapter;
    private List<Music> musics;
    private Music music;
    private int musicListType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_music);
        x.view().inject(this);
        setSupportActionBar(toolbar);
        setData();
        setListeners();
    }

    @Override
    protected void onResume() {
        setData();
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        setData();
        super.onNewIntent(intent);
    }

    private void setData() {
        MusicApplication.getMusicPlayer().getNewLists();

        int type = MusicApplication.getMusicPlayer().getMusicListType();

//        Log.i(TAG, "setdata: 执行" + type);
        presenterNetDetial = new PresenterNetDetialImpl(this, type);
        presenterNetDetial.loadAllMusics();
    }

    private void setListeners() {
        btnMusciListBack.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(DetialMusicActivity.this,""+musics.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                //设置 song_id
                MusicApplication.getMusicPlayer().setPosition(position);
                Intent intent = new Intent(DetialMusicActivity.this, PlayActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                //Log.i(TAG, "onItemClick: " + "歌曲地址: " + musics.get(position).getSong_id());
                //presenterNetDetial.setSong(musics.get(position).getSong_id());


            }
        });

    }

    @Override
    protected void onDestroy() {
        //unbindService(conn);
        super.onDestroy();
    }

    @Override
    public void setMusicData(List<Music> musics) {
        if (musics != null) {
            this.musics = musics;
        } else {
            return;
        }
    }

    @Override
    public void showMusicData() {
        musicListAdapter = new MusicListAdapter(this, (ArrayList<Music>) musics);
        listView.setAdapter(musicListAdapter);
    }

    @Override
    public void playMusic(Object data1, Object data2) {
//        // Log.i(TAG, "playMusic: 执行2");
//        String musicPath=((List<Url>)data1).get(0).getFile_link();
//        MusicSevice.MusicBinder.playMusic(musicPath);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_music_list_back:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Fragment", -2);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;

        }
    }

}
