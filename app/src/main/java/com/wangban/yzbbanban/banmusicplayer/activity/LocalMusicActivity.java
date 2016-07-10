package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.MyLocalMusicAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Song;
import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;
import com.wangban.yzbbanban.banmusicplayer.util.FramAnimationUtil;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

public class LocalMusicActivity extends BaseActivity implements Consts, View.OnClickListener, AdapterView.OnItemClickListener {
    @ViewInject(R.id.btn_my_player_list_back)
    private Button btnPlayetListBack;
    @ViewInject(R.id.ibtn_my_player_local_music)
    private ImageButton ibtnPlayetLocalMusic;
    @ViewInject(R.id.lv_my_player_local_music_list)
    private ListView lvPlayerLocalMusic;


    private MyLocalMusicAdapter adapter;
    //    private MediaPlayer player = MusicApplication.getContext().getPlayer();
    private Intent intent;
    private List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        x.view().inject(this);
        setListeners();
    }

    @Override
    protected void onResume() {
        setView();
        super.onResume();
        FramAnimationUtil.framAnimationContrl(ibtnPlayetLocalMusic);
    }

    private void setView() {
//        MusicApplication.getMusicPlayer().setMusicListType(LOCAL);
        songs = MusicApplication.getMusicPlayer().getLocalSongs();
//        LogUtil.logInfo(TAG, "Local_songs: " + songs.get(0).getTitle());
        adapter = new MyLocalMusicAdapter(this, (ArrayList<Song>) songs);
        lvPlayerLocalMusic.setAdapter(adapter);
    }

    private void setListeners() {
        ibtnPlayetLocalMusic.setOnClickListener(this);
        btnPlayetListBack.setOnClickListener(this);
        lvPlayerLocalMusic.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_my_player_list_back:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Fragment", -2);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;
            case R.id.ibtn_my_player_local_music:
                intent = new Intent(this, PlayActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        LogUtil.logInfo(TAG, "onItem: " + position);
        MusicApplication.getMusicPlayer().setPosition(position);
//        LogUtil.logInfo(TAG, "onItem: " + songs.get(position).getPath());
        MusicApplication.getMusicPlayer().setMusicListType(LOCAL);
        MusicSevice.MusicBinder.playMusic(songs.get(position).getPath());
        intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
    }
}
