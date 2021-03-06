package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.LocalMusicListAdapter;
import com.wangban.yzbbanban.banmusicplayer.adapter.MyLocalMusicAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.entity.LrcLine;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.entity.Song;
import com.wangban.yzbbanban.banmusicplayer.entity.SongInfo;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;
import com.wangban.yzbbanban.banmusicplayer.entity.Url;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterDownload;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterLrc;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterDownloadImpl;
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
import com.wangban.yzbbanban.banmusicplayer.view.IViewDownLoad;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLrc;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.*;

public class PlayActivity extends BaseDestoryActivity implements IViewLrc, IViewNetDetial, AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener, Consts {

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
    private List<Song> songs;
    private List<Url> urls;

    private SongInfo songInfo;
    private int positionList;

    private String localMusicChangedId = "default";

    private int playState = REPEAT;
    //音乐类型（新歌、热歌。。。。搜索等）
    private int musicListType;

    private Animation anim;

    //设置音乐进度
    private int progress;
    private boolean isFromUser;
    //设置 local_list_adapter
    private LocalMusicListAdapter ladapter;

    //设置传递下载数据的 presenter
    private IPresenterDownload presenterDownload;

    public PlayActivity() {
        super();
        imageLoader = new ImageLoader(MusicApplication.getQueue(), new BitmapCache());
        presenterLrc = new PresenterLrcImpl(this);
        presenterDownload = new PresenterDownloadImpl();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        x.view().inject(this);
        setData();
        ibtnPlayState.setBackgroundResource(R.drawable.recycle_play);
        if (!player.isPlaying()) {
            discRecycle();
            removecRecycle();
        }
        MusicApplication.getMusicPlayer().setPlayState(RECYCLE);
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
    protected void onRestart() {
        super.onRestart();
        setData();
        if (musicListType == LOCAL) {
            //若为本地播放可直接注册广播
            registComponent();
//            LogUtil.logInfo(TAG, "PlayActivity onResume: ");
            setView();
        }
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
        if (musicListType != LOCAL) {
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
        } else {
//            LogUtil.logInfo(TAG, "设置LOCAL");
            String musicName = songs.get(positionList).getTitle();
            String musicArtist = songs.get(positionList).getArtist();
//            LogUtil.logInfo(TAG,"位置： "+positionList);
            tvCurrentMusicName.setText(musicName);
            tvCurrentMusicArtist.setText(musicArtist);

//            LogUtil.logInfo(TAG, songs.get(positionList).getAlbumArt());


            if (songs.get(positionList).getAlbumArt() == null) {
                // 没有图片
                ivBackground.setImageResource(R.drawable.my_logo);
                civMusicImage.setImageResource(R.drawable.my_logo);
            } else {
                // 存在图片的路径，则显示
                Bitmap bm = BitmapFactory.decodeFile(songs.get(positionList).getAlbumArt());
                civMusicImage.setImageBitmap(bm);
                Bitmap bitmap = BluredBitmap.createBlurBitmap(bm, 10);
                ivBackground.setImageBitmap(bitmap);
            }
        }
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
        ibtnShare.setOnClickListener(this);
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

        musicPlayerControl = MusicApplication.getMusicPlayer();
        musicListType = musicPlayerControl.getMusicListType();
        if (musicListType == 0) {
            MusicApplication.getMusicPlayer().setMusicListType(LOCAL);
        }
//        LogUtil.logInfo(TAG, "playsetData: " + musicListType);
        presenterNetDetial = new PresenterNetDetialImpl(this);
        localMusicChangedId = MusicApplication.getMusicPlayer().getPositionId();
//        LogUtil.logInfo(TAG,"setData: "+localMusicChangedId+"||");
        //TODO 添加类型
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
                    break;
                case LOCAL:
                    songs = musicPlayerControl.getLocalSongs();
                    break;

            }
            //判断传入类型 搜索、本地、其他榜单的音乐
            if (musicListType == SEARCH) {
                List<SongList> objects = songLists;
                ladapter = new LocalMusicListAdapter(this, objects);
                lvMusicList.setAdapter(ladapter);
            } else if (musicListType == LOCAL) {
                List<Song> objects = songs;
                ladapter = new LocalMusicListAdapter(this, objects);
                lvMusicList.setAdapter(ladapter);
            } else {
                List<Music> objects = musics;
                ladapter = new LocalMusicListAdapter(this, objects);
                lvMusicList.setAdapter(ladapter);
            }
            String id = null;
            //获取播放的音乐位置
            positionList = MusicApplication.getMusicPlayer().getPosition();
            if (musicListType == LOCAL) {
                id = String.valueOf(songs.get(positionList).getId());
            } else if (musicListType == SEARCH) {
                id = songLists.get(positionList).getSong_id();
            } else {
                music = musics.get(positionList);
                id = music.getSong_id();
            }
            //判断重新进入时是否播放的同一首歌曲，若是则不重新播放，不是则重新播放
            // （事先设定好了 position 会根据 position 来显示与播放音乐）
//            LogUtil.logInfo(TAG, "" + localMusicChangedId.equals(id));
            if (id.equals(localMusicChangedId)) {
//                LogUtil.logInfo(TAG, "playsetData:结束 ");
                return;
            } else {
//                LogUtil.logInfo(TAG, "判断与之前播放的音乐是否为同一首" + localMusicChangedId);
                if (musicListType == LOCAL) {
                    setView();
                } else {
//                    LogUtil.logInfo(TAG, "不是重新播放");
                    presenterNetDetial.setSong(id);
                }
                MusicApplication.getMusicPlayer().setPositionId(id);
            }
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
                if (musicListType == LOCAL) {
                    ToastUtil.showToast(this, "这是本地音乐");
                } else {
                    download();
                }
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
            //分享音乐
            case R.id.ibtn_player_share:
                String appPath = MY_APP;
                musicShare(appPath, null);
                break;
        }
    }

    /**
     * 分享音乐
     */
    private void musicShare(String appPat, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (uri != null) {
            intent.setType("image/*");
        } else {
            intent.setType("text/plain");
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, appPat);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }

    /**
     * 显示列表动画
     *
     * @param visible
     * @param s1
     * @param s2
     */
    private void listShow(int visible, int s1, int s2) {
        llList.setVisibility(visible);
        anim = new TranslateAnimation(0, 0, s1, s2);
        anim.setDuration(300);
        llList.startAnimation(anim);
    }

    /**
     * 显示背景动画
     *
     * @param visible
     * @param fromAlpha
     * @param toAlpha
     */
    private void viewShow(int visible, int fromAlpha, int toAlpha) {
        vBackground.setVisibility(visible);
        anim = new AlphaAnimation(fromAlpha, toAlpha);
        anim.setDuration(200);
        vBackground.startAnimation(anim);

    }


    /**
     * 执行下载
     * TODO
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
                String title = songInfo.getTitle();
//                LogUtil.logInfo(TAG, "onClick: " + fileLink);
//                启动 Service执行下载

//                Intent intent = new Intent(PlayActivity.this, DownloadService.class);
//                intent.putExtra("url", fileLink);
//                intent.putExtra("title", songInfo.getTitle());
//                intent.putExtra("bit", url.getFile_bitrate());
//                startService(intent);

                //启动 download 界面下载
//                List<DownloadDoc> downloadDocs = new ArrayList<DownloadDoc>();
                if (musicListType != LOCAL) {
                    DownloadDoc downloadDoc = new DownloadDoc();
                    downloadDoc.setUrl(fileLink);
                    downloadDoc.setTitle(title);
                    presenterDownload.sendDownLoadMessage(downloadDoc);
                }
//                downloadDocs.add(downloadDoc);

//                MusicApplication.getContext().setDownloadDoc(downloadDocs);
//                Intent intent2 = new Intent(PlayActivity.this, DownloadActivity.class);
//                intent2.putExtra("url", fileLink);
//                intent2.putExtra("title", songInfo.getTitle());
//                intent2.putExtra("bit", url.getFile_bitrate());
//                startActivity(intent2);
            }
        });
        AlertDialog dialog = builder.create();
        builder.show();


    }

    /**
     * 音乐播放完成时紧接着的操作播放
     */
    private void setPositionToPlay() {
        positionList = MusicApplication.getMusicPlayer().getPosition();
        if (musicListType != SEARCH && musicListType != LOCAL) {
//            LogUtil.logInfo(TAG, "else: ");
            presenterNetDetial.setSong(musics.get(positionList).getSong_id());

        } else if (musicListType == SEARCH) {
//            LogUtil.logInfo(TAG, "search: ");
            presenterNetDetial.setSong(songLists.get(positionList).getSong_id());
        } else if (musicListType == LOCAL) {
//            LogUtil.logInfo(TAG, "local: ");
            String url = songs.get(positionList).getPath();
            MusicSevice.MusicBinder.playMusic(url);
            setView();
        }
        //设置 songId 用于区分是否为播放相同的音乐
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
            //关闭广播更新的数据
            isFromUser = fromUser;
            this.progress = progress;
            tvCurrentTime.setText(DateFormatUtil.getDate(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        player.seekTo(progress);
        //开启广播更新的数据
        isFromUser = false;
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

    /**
     * 播放音乐
     *
     * @param data1 url
     * @param data2 songinfo
     */
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
        // LogUtil.logInfo(TAG, "" + position);
        //ToastUtil.showToast(this, "" + position);
        if (musicListType != SEARCH && musicListType != LOCAL) {
            playList(position, musics.get(position).getSong_id());
        } else if (musicListType == SEARCH) {
            playList(position, songLists.get(position).getSong_id());
        } else if (musicListType == LOCAL) {
            playLocalList(position, songs.get(position).getPath());
            return;
        }

        setData();

    }

    private void playLocalList(int position, String path) {
//        LogUtil.logInfo(TAG, "playLocalList: " + path);
        MusicApplication.getMusicPlayer().setPosition(position);
        MusicSevice.MusicBinder.playMusic(path);
        setData();
        setView();

    }

    private void playList(int position, String song_id) {
        MusicApplication.getMusicPlayer().setPosition(position);
        presenterNetDetial.setSong(song_id);
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
                if (!isFromUser) {
                    tvCurrentTime.setText(DateFormatUtil.getDate(currentTime));
                    sbProgress.setMax(totalTime);
                    sbProgress.setProgress(currentTime);
                }
                //更新歌词信息
                if (musicListType != LOCAL) {
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
}