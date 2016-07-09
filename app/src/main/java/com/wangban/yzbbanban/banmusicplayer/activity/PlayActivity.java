package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.LocalMusicListAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.LrcLine;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.entity.SongInfo;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;
import com.wangban.yzbbanban.banmusicplayer.entity.Url;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterLrc;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterLrcImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetDetialImpl;
import com.wangban.yzbbanban.banmusicplayer.service.DownloadService;
import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;
import com.wangban.yzbbanban.banmusicplayer.ui.CircleImageView;
import com.wangban.yzbbanban.banmusicplayer.util.BitmapCache;
import com.wangban.yzbbanban.banmusicplayer.util.BluredBitmap;
import com.wangban.yzbbanban.banmusicplayer.util.DateFormatUtil;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLrc;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

public class PlayActivity extends AppCompatActivity implements IViewLrc, IViewNetDetial, AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener, Consts {

    @ViewInject(R.id.ibtn_player_back_main)
    private ImageButton ibtnBackMain;
    @ViewInject(R.id.tv_player_current_music_name)
    private TextView tvCurrentMusicName;
    @ViewInject(R.id.tv_player_current_music_artist)
    private TextView tvCurrentMusicArtist;
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
    @ViewInject(R.id.ibtn_player_download)
    private ImageButton ibtnDownload;
    @ViewInject(R.id.ibtn_player_like)
    private ImageButton ibtnLike;
    @ViewInject(R.id.lv_player_music_list)
    private ListView lvMusicList;
    @ViewInject(R.id.ll_player_list)
    private LinearLayout llList;
    @ViewInject(R.id.btn_player_music_list_back)
    private Button btnMusicListBack;
    @ViewInject(R.id.v_player_music_background)
    private View vBackground;


    private Animation animation;

    private Music music;
    private ImageLoader imageLoader;

    private BroadcastReceiver receiver;
    private IPresenterLrc presenterLrc;

    private MediaPlayer player = MusicApplication.getContext().getPlayer();


    private MusicPlayer musicPlayerControl;

    private IPresenterNetDetial presenterNetDetial;


    private List<Music> musics;
    private List<SongList> songLists;
    private List<Url> urls;

    private SongInfo songInfo;
    private int position;

    private int playState = REPEAT;
    //音乐类型（新歌、热歌。。。。搜索等）
    private int musicListType;

    private Animation anim;

    //设置 local_list_adapter
    private LocalMusicListAdapter ladapter;

    private String localMusicChangedId;

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
        if (!player.isPlaying()) {
            discRecycle();
            removecRecycle();
        }
        MusicApplication.getMusicPlayer().setPlayState(RECYCLE);
        setData();
        setListenter();

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
        //显示动画
        ibtnPlayState.setBackgroundResource(R.drawable.recycle_play);
        if (!player.isPlaying()) {
            discRecycle();
            removecRecycle();
        } else {
            discRecycle();
        }
        super.onResume();
//        LogUtil.logInfo(TAG, "onResume: " + music.getTitle());
    }

    /**
     * 设置控件
     */

    private void setView() {
        //设置播放的歌曲名
        String musicName = songInfo.getTitle();
        String musicArtist = songInfo.getAuthor();
        tvCurrentMusicName.setText(musicName);
        tvCurrentMusicArtist.setText(musicArtist);
        //设置播放歌曲的图片信息
        String imagePath = songInfo.getAlbum_500_500();
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(civMusicImage, R.drawable.my_logo, R.drawable.my_logo);
        imageLoader.get(imagePath, imageListener);
        String url = songInfo.getArtist_500_500();
        //设置背景图片的路径
        ImageRequest imageRequest = new ImageRequest(
                url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Bitmap bitmap = BluredBitmap.createBlurBitmap(response, 10);

                ivBackground.setImageBitmap(bitmap);

            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ivBackground.setImageResource(R.drawable.my_logo);
            }
        });
        MusicApplication.getQueue().add(imageRequest);
    }

    /**
     * 设置播放按钮样式,没用到
     */
    private void playPauseModel() {
        if (player.isPlaying()) {
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
        ibtnPlayState.setOnClickListener(this);
        ibtnDownload.setOnClickListener(this);
        ibtnLike.setOnClickListener(this);
        ibtnMusicList.setOnClickListener(this);
        btnMusicListBack.setOnClickListener(this);
        vBackground.setOnClickListener(this);
        sbProgress.setOnSeekBarChangeListener(this);
        lvMusicList.setOnItemClickListener(this);


    }

    /**
     * 配置 Lrc 歌词数据
     */
    private void setData() {
        llList.setVisibility(View.INVISIBLE);
        vBackground.setVisibility(View.INVISIBLE);
        musicPlayerControl = MusicApplication.getMusicPlayer();
        musicListType = musicPlayerControl.getMusicListType();
//        LogUtil.LogInfo(TAG, "playsetData: " + musicListType);
        presenterNetDetial = new PresenterNetDetialImpl(this);
        try {
            switch (musicListType) {
                case NEW:
                    musics = musicPlayerControl.getNewLists();
                    break;
                case HOT:
                    musics = musicPlayerControl.getHotLists();
                    break;
                case BILLBOARD:
                    musics = musicPlayerControl.getBillboardLists();
                    break;
                case KTV:
                    musics = musicPlayerControl.getKtvLists();
                    break;
                case SEARCH:
                    songLists = musicPlayerControl.getSongLists();

            }
            //判断传入类型
            if (musicListType == SEARCH) {
                List<SongList> objects = songLists;
                ladapter = new LocalMusicListAdapter(this, objects);
                lvMusicList.setAdapter(ladapter);
            } else {
                List<Music> objects = musics;
                ladapter = new LocalMusicListAdapter(this, objects);
                lvMusicList.setAdapter(ladapter);
            }
            //获取播放的音乐位置
            int positionList = MusicApplication.getMusicPlayer().getPosition();
            String id = null;
            if (musicListType != SEARCH) {
                music = musics.get(positionList);
                id = music.getSong_id();
            } else {
                id = songLists.get(positionList).getSong_id();
            }
            //  LogUtil.logInfo(TAG, "playsetData: " + id);

            if (localMusicChangedId != id) {
                presenterNetDetial.setSong(id);
                localMusicChangedId = id;
            } else {
                return;
            }
//            url = music.getPic_big();
            //LLogUtil.logInfo(TAG, "setData: pic_big: " + url);
            //LogUtil.logInfo(TAG, "setData: pic_sam: " + music.getPic_small());

            //LogUtil.logInfo(TAG, "setData: "+music.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //当前歌曲完成时播放下一曲
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                musicPlayerControl.nextMusic();
                setPositionToPlay();
                discRecycle();

            }
        });

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
                //             finish();
                startActivity(new Intent(this, MainActivity.class));
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
            //设置播放模式：单曲循环、随机、列表循环
            case R.id.ibtn_player_play_state:
                if (playState == REPEAT) {
                    ibtnPlayState.setBackgroundResource(R.drawable.repeat_play);
                    ToastUtil.showToast(this, "重复");
                    MusicApplication.getMusicPlayer().setPlayState(REPEAT);

                    playState = RANDOM;
                } else if (playState == RANDOM) {
                    ibtnPlayState.setBackgroundResource(R.drawable.randowm_play);
                    ToastUtil.showToast(this, "随机");
                    MusicApplication.getMusicPlayer().setPlayState(RANDOM);
                    playState = RECYCLE;
                } else if (playState == RECYCLE) {
                    ibtnPlayState.setBackgroundResource(R.drawable.recycle_play);
                    ToastUtil.showToast(this, "循环");
                    MusicApplication.getMusicPlayer().setPlayState(RECYCLE);
                    playState = REPEAT;
                }
                break;
            //下载音乐
            case R.id.ibtn_player_download:
                download();
                break;
            //点击列表播放
            case R.id.ibtn_player_music_list:
                Display disp = this.getWindowManager().getDefaultDisplay();
                int s = disp.getHeight();
                listShow(View.VISIBLE, s + llList.getHeight(), s - llList.getHeight());
                viewShow(View.VISIBLE, 0, 1);
                break;
            //点击关闭按钮列表消失
            case R.id.btn_player_music_list_back:
                llList.setVisibility(View.INVISIBLE);
                listShow(View.INVISIBLE, 0, llList.getHeight());
                viewShow(View.GONE, 1, 0);
                break;
            //点击 view 列表消失，与上一个 button 的功能一致
            case R.id.v_player_music_background:
                listShow(View.INVISIBLE, 0, llList.getHeight());
                viewShow(View.GONE, 1, 0);
                break;
        }
    }

    private void listShow(int visible, int s1, int s2) {
        llList.setVisibility(visible);
        anim = new TranslateAnimation(0, 0, s1, s2);
        anim.setDuration(300);
        llList.startAnimation(anim);
    }

    private void viewShow(int visible, int fromAlpha, int toAlpha) {
        vBackground.setVisibility(visible);
        anim = new AlphaAnimation(fromAlpha, toAlpha);
        anim.setDuration(200);
        vBackground.startAnimation(anim);

    }


    /**
     * 执行下载
     */
    private void download() {
        String[] songData = new String[urls.size()];
        //集合中的数据转成字符串
        for (int i = 0; i < urls.size(); i++) {
            final Url url = urls.get(i);
            double size = 100.0 * url.getFile_size() / 1024 / 1024;
            songData[i] = Math.floor(size) / 100 + "M";
        }
        //弹出AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择您要下载的版本").setItems(songData, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Url url = urls.get(which);
                String fileLink = url.getShow_link();
//                LogUtil.logInfo(TAG, "onClick: " + fileLink);
                //启动 Service执行下载
                Intent intent = new Intent(PlayActivity.this, DownloadService.class);
                intent.putExtra("url", fileLink);
                intent.putExtra("title", songInfo.getTitle());
                intent.putExtra("bit", url.getFile_bitrate());
                startService(intent);
            }
        });
        AlertDialog dialog = builder.create();
        builder.show();


    }

    private void setPositionToPlay() {
        position = MusicApplication.getMusicPlayer().getPosition();
        if (musicListType != SEARCH) {
            presenterNetDetial.setSong(musics.get(position).getSong_id());
        } else {
            presenterNetDetial.setSong(songLists.get(position).getSong_id());
        }
        setData();
    }


    /**
     * 设置歌词数据源
     *
     * @param lrcs
     */
    @Override
    public void setLrc(List<LrcLine> lrcs) {
        musicPlayerControl.setLrcs(lrcs);
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
    public void playMusic(Object data1, Object data2) {
        urls = (List<Url>) data1;
        songInfo = (SongInfo) data2;

        String url = urls.get(0).getFile_link();
        MusicSevice.MusicBinder.playMusic(url);
        //获取歌词数据
        presenterLrc.loadLrc(songInfo.getLrclink());
        //LogUtil.logInfo(TAG, "playMusic: " + songInfo.getLrclink());
        setView();
        registComponent();

    }

    /**
     * 点击列表播放音乐
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO
        if (musicListType != SEARCH) {
            platList(position, musics.get(position).getSong_id());
        } else {
            platList(position, songLists.get(position).getSong_id());
        }
        setData();
    }

    private void platList(int position, String song_id) {
        presenterNetDetial.setSong(song_id);
        MusicApplication.getMusicPlayer().setPosition(position);
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
                if (!animation.isInitialized()) {
                    discRecycle();
                } else {
                    return;
                }
            }
            //当时进度更新状态时
            else if (ACTION_UPDATE_PROGRESS.equals(action)) {
                //设置 disc 的旋转
                if (!player.isPlaying()) {
                    removecRecycle();
                }
                ibtnMusicPlayPause.setBackgroundResource(R.drawable.play_nomal);
                int currentTime = intent.getIntExtra("current", 0);
                int totalTime = intent.getIntExtra("total", 0);
                tvDurationTime.setText(DateFormatUtil.getDate(totalTime));
                tvCurrentTime.setText(DateFormatUtil.getDate(currentTime));
                sbProgress.setMax(totalTime);
                sbProgress.setProgress(currentTime);
                //更新歌词信息
                List<LrcLine> lines = MusicApplication.getMusicPlayer().getLrcs();
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