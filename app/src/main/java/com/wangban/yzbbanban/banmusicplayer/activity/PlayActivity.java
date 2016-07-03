package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
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
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterLrcImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetDetialImpl;
import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;
import com.wangban.yzbbanban.banmusicplayer.ui.CircleImageView;
import com.wangban.yzbbanban.banmusicplayer.util.BitmapCache;
import com.wangban.yzbbanban.banmusicplayer.util.DateFormatUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLrc;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class PlayActivity extends AppCompatActivity implements IViewLrc, IViewNetDetial, SeekBar.OnSeekBarChangeListener, View.OnClickListener, Consts {

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

    private Animation animation;

    private Music music;
    private ImageLoader imageLoader;

    private BroadcastReceiver receiver;
    private IPresenterLrc presenterLrc;

    private MediaPlayer player = MusicApplication.getContext().getPlayer();

    private List<Music> musics;

    private MusicPlayer musicPlayerControl;

    private IPresenterNetDetial presenterNetDetial;


    int position;

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
        ibtnPlayState.setBackgroundResource(R.drawable.recycle_play);
        setData();
        setView();
        setListenter();
        registComponent();
    }

    /**
     * 注册接收的广播
     */
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

    /**
     * 当 activity 销毁时执行取消注册广播
     */
    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();

    }

    /**
     * activity重新加载时 更新界面
     */
    @Override
    protected void onResume() {
        setData();
        setView();
        super.onResume();
        //Log.i(TAG, "onResume: " + music.getTitle());
    }

    /**
     * 设置控件
     */
    private void setView() {
        //设置播放的歌曲名
        tvCurrentMusic.setText(music.getTitle());
        //设置播放歌曲的图片信息
        String imagePath = music.getPic_big();
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(civMusicImage, R.drawable.my_logo, R.drawable.my_logo);
        imageLoader.get(imagePath, imageListener);
        //显示动画
        discRecycle();


    }

    /**
     * 设置播放按钮样式
     */
    private void playPauseModel() {
        if ( player.isPlaying()) {
            ibtnMusicPlayPause.setBackgroundResource(R.drawable.play_nomal);
        } else {
            ibtnMusicPlayPause.setBackgroundResource(R.drawable.pause_nomal);
        }
    }

    /**
     * 动画显示
     */
    private void discRecycle() {

        //disc 内部图片的播放动画
        animation = AnimationUtils.loadAnimation(this, R.anim.set_recycle_disc);
        LinearInterpolator i = new LinearInterpolator();
        animation.setInterpolator(i);
        ibtnRevoleDisc.startAnimation(animation);

        //disc 的播放动画
        animation = AnimationUtils.loadAnimation(this, R.anim.set_recycle_disc);
        LinearInterpolator i2 = new LinearInterpolator();
        animation.setInterpolator(i2);
        civMusicImage.startAnimation(animation);

    }

    private void removecRecycle() {
        ibtnRevoleDisc.clearAnimation();
        civMusicImage.clearAnimation();

    }

    /**
     * 设置监听器
     */
    private void setListenter() {
        ibtnBackMain.setOnClickListener(this);
        ibtnMusicNext.setOnClickListener(this);
        ibtnMusicPlayPause.setOnClickListener(this);
        ibtnMusicPrevious.setOnClickListener(this);
        sbProgress.setOnSeekBarChangeListener(this);
    }

    /**
     * 配置 Lrc 歌词数据
     */
    private void setData() {
        int musicListType = 0;
        musicPlayerControl = MusicApplication.getMusicPlayer();
        musicListType = musicPlayerControl.getMusicListType();
        presenterNetDetial = new PresenterNetDetialImpl(this, musicListType);
        if (musicListType != 0) {
            switch (musicListType) {
                case NEW:
                    musics = musicPlayerControl.getNewList();
                    break;
                case HOT:
                    musics = musicPlayerControl.getHotList();
                    break;
                case BILLBOARD:
                    musics = musicPlayerControl.getBillboardList();
                    break;
                case KTV:
                    musics = musicPlayerControl.getKtvList();
                    break;

            }
            //获取播放的音乐位置
            int positionList = MusicApplication.getMusicPlayer().getPosition();
            music = musics.get(positionList);
            //获取歌词数据
            presenterLrc.loadLrc(music.getLrclink());
            // Log.i(TAG, "setData: "+music.getTitle());
        } else {
            return;
        }

    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回按钮
            case R.id.ibtn_player_back_main:
                finish();
//                startActivity(new Intent(this, DetialMusicActivity.class));
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;
            //暂停播放按钮
            case R.id.ibtn_player_music_play_or_pause:
                if (player.isPlaying()) {
                    MusicSevice.MusicBinder.playOrPause();
                    ibtnMusicPlayPause.setBackgroundResource(R.drawable.pause_nomal);
                    removecRecycle();
                } else {
                    MusicSevice.MusicBinder.playOrPause();
                    ibtnMusicPlayPause.setBackgroundResource(R.drawable.play_nomal);
                    discRecycle();
                }
                break;
            //上一曲按钮
            case R.id.ibtn_player_music_previous:
                musicPlayerControl.preMusic();
                setPositionToPlay();

                break;
            //下一曲按钮
            case R.id.ibtn_player_music_next:
                musicPlayerControl.nextMusic();
                setPositionToPlay();
                break;
        }
    }

    private void setPositionToPlay() {
        position = MusicApplication.getMusicPlayer().getPosition();
        presenterNetDetial.setSongUrl(musics.get(position).getSong_id());
    }


    /**
     * 设置歌词数据源
     *
     * @param lrcs
     */
    @Override
    public void setLrc(List<LrcLine> lrcs) {
        musicPlayerControl.setLrc(lrcs);
        playPauseModel();
    }

    /**
     * 设置进度条
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            player.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {


    }

    /**
     * 无用
     */
    @Override
    public void setMusicData(List<Music> musics) {

    }

    /**
     * 无用
     */
    @Override
    public void showMusicData() {

    }

    @Override
    public void playMusic(String url) {
        MusicSevice.MusicBinder.playMusic(url);
    }

    /**
     * 设置接收广播的数据
     */
    private class MusicBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //当接收的是播放状态广播室
            if (ACTION_START_PLAY.equals(action)) {

            }
            //当时进度更新状态时
            else if (ACTION_UPDATE_PROGRESS.equals(action)) {

                setView();
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