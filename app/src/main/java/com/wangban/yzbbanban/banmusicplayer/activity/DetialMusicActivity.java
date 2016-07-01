package com.wangban.yzbbanban.banmusicplayer.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.MusicListAdapter;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetDetialImpl;
import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import static com.wangban.yzbbanban.banmusicplayer.service.MusicSevice.MusicBinder;
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
    private int type;
    private Intent intent;
    private MusicSevice.MusicBinder musicBinder;
//    private ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_music);
        x.view().inject(this);
        setSupportActionBar(toolbar);
        musicBinder=new MusicBinder();
       // bindService();

        setData();
        setListeners();
    }

//    private void bindService() {
//        Intent service = new Intent(this, MusicSevice.class);
//        conn = new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                musicBinder = (MusicSevice.MusicBinder) service;
//
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        };
//        bindService(service, conn, Service.BIND_AUTO_CREATE);
//    }

    private void setData() {
        intent = getIntent();
        type = intent.getIntExtra("type", 1);
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
                Log.i(TAG, "onItemClick: " + "歌曲地址: " + musics.get(position).getSong_id());
                presenterNetDetial.setSongUrl(musics.get(position).getSong_id());


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
        this.musics = musics;
    }

    @Override
    public void showMusicData() {
        musicListAdapter = new MusicListAdapter(this, (ArrayList<Music>) musics);
        listView.setAdapter(musicListAdapter);
    }

    @Override
    public void playMusic(String musicPath) {

        Log.i(TAG, "playMusic: 执行2");
        musicBinder.playMusic(musicPath);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_music_list_back:
                finish();
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;

        }
    }

}
