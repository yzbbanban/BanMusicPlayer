package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.LrcLine;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterLrc;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterLrcImpl;
import com.wangban.yzbbanban.banmusicplayer.ui.CircleImageView;
import com.wangban.yzbbanban.banmusicplayer.util.BitmapCache;
import com.wangban.yzbbanban.banmusicplayer.util.DateFormatUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLrc;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class PlayActivity extends AppCompatActivity implements IViewLrc, View.OnClickListener, Consts {

    @ViewInject(R.id.ibtn_player_back_main)
    private ImageButton ibtnBackMain;
    @ViewInject(R.id.tv_player_current_music)
    private TextView tvCurrentMusic;
    @ViewInject(R.id.ibtn_player_share)
    private ImageButton ibtnShare;
    @ViewInject(R.id.tv_player_lrc)
    private TextView tvLrc;
    @ViewInject(R.id.ibtn_player_revolve_disc)
    private ImageButton ibtnRevoleDisc;
    @ViewInject(R.id.ibtn_player_music_play_or_pause)
    private ImageButton ibtnMusicPlayPause;
    @ViewInject(R.id.ibtn_player_music_next)
    private ImageButton ibtnMusicNext;
    @ViewInject(R.id.ibtn_player_music_previous)
    private ImageButton ibtnMusicPrevious;
    @ViewInject(R.id.ibtn_player_play_state)
    private ImageButton ibtnPlayState;
    @ViewInject(R.id.ibtn_player_music_list)
    private ImageButton ibtnMusicList;
    @ViewInject(R.id.tv_player_current_time)
    private TextView tvCurrentTime;
    @ViewInject(R.id.sb_player_progross)
    private SeekBar sbProgress;
    @ViewInject(R.id.tv_player_duration_time)
    private TextView tvDurationTime;
    @ViewInject(R.id.civ_player_music_image)
    private CircleImageView civMusicImage;
    @ViewInject(R.id.iv_player_background)
    private ImageView ivBackground;

    private Music music;
    private ImageLoader imageLoader;

    private BroadcastReceiver receiver;
    private IPresenterLrc presenterLrc;

    public PlayActivity() {
        super();
        imageLoader = new ImageLoader(MusicApplication.getQueue(), new BitmapCache());
        presenterLrc = new PresenterLrcImpl(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        x.view().inject(this);

        setData();
        setView();
        setListenter();
        registComponent();
    }

    private void registComponent() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_START_PLAY);
        intentFilter.addAction(ACTION_UPDATE_PROGRESS);
        receiver = new MusicBroadCastReceiver();
        registerReceiver(receiver, intentFilter);
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        setIntent(intent);
//        setData();
//        setView();
//        super.onNewIntent(intent);
//    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        setData();
        setView();
        super.onResume();
        //Log.i(TAG, "onResume: " + music.getTitle());
    }

    private void setView() {
        tvCurrentMusic.setText(music.getTitle());
        String imagePath = music.getPic_big();
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(civMusicImage, R.drawable.my_logo, R.drawable.my_logo);
        imageLoader.get(imagePath, imageListener);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.set_recycle_disc);
        LinearInterpolator i = new LinearInterpolator();
        animation1.setInterpolator(i);
        ibtnRevoleDisc.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.set_recycle_disc);
        LinearInterpolator i2 = new LinearInterpolator();
        animation2.setInterpolator(i2);
        civMusicImage.startAnimation(animation2);

    }

    private void setListenter() {
        ibtnBackMain.setOnClickListener(this);
    }

    private void setData() {
        music = MusicApplication.getMusic();
        presenterLrc.loadLrc(music.getLrclink());
        // Log.i(TAG, "setData: "+music.getTitle());


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_player_back_main:
                finish();
//                startActivity(new Intent(this, DetialMusicActivity.class));
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;

        }
    }

    @Override
    public void setLrc(List<LrcLine> lrcs) {
        MusicPlayer musicPlayer = MusicApplication.getMusicPlayer();
        musicPlayer.setLrc(lrcs);

    }

    private class MusicBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_START_PLAY.equals(action)) {

            } else if (ACTION_UPDATE_PROGRESS.equals(action)) {
                int currentTime = intent.getIntExtra("current", 0);
                int totalTime = intent.getIntExtra("total", 0);
                tvDurationTime.setText(DateFormatUtil.getDate(totalTime));
                tvCurrentTime.setText(DateFormatUtil.getDate(currentTime));
                sbProgress.setMax(totalTime);
                sbProgress.setProgress(currentTime);
                //更新歌词信息
                List<LrcLine> lines = MusicApplication.getMusicPlayer().getLrc();
                if (lines == null) { //歌词还没有下载成功
                    return;
                }
                //获取当前时间需要显示的歌词内容
                for (int i = 0; i < lines.size(); i++) {
                    LrcLine line = lines.get(i);
                    if (line.equalsTime(currentTime)) {
                        String content = line.getContent();
                        //设置TextView
                        tvLrc.setText(content);
                        return;
                    }


                }
            }
        }
    }
}