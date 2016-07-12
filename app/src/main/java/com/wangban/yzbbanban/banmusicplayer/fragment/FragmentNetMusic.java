package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.DetialMusicActivity;
import com.wangban.yzbbanban.banmusicplayer.activity.PlayActivity;
import com.wangban.yzbbanban.banmusicplayer.adapter.MusicSearchListAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;
import com.wangban.yzbbanban.banmusicplayer.entity.Url;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetDetialImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetImpl;
import com.wangban.yzbbanban.banmusicplayer.service.MusicSevice;
import com.wangban.yzbbanban.banmusicplayer.util.FramAnimationUtil;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

/**
 * Created by YZBbanban on 16/6/23.
 */
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class FragmentNetMusic extends Fragment implements IViewNet, IViewNetDetial, AdapterView.OnItemClickListener, View.OnClickListener, Consts {
    @ViewInject(R.id.ll_new_list)
    private LinearLayout llNewList;
    @ViewInject(R.id.ll_hot_list)
    private LinearLayout llHotList;
    @ViewInject(R.id.ll_billboard_list)
    private LinearLayout llBillboardList;
    @ViewInject(R.id.ll_ktv_list)
    private LinearLayout llKtvList;
    @ViewInject(R.id.ll_net_fragment)
    private LinearLayout llNetFragment;
    @ViewInject(R.id.et_search)
    private EditText etSearch;
    @ViewInject(R.id.lv_search_music)
    private ListView lvSearchMusic;
    //new
    @ViewInject(R.id.tv_new_list_first)
    private TextView tvNewFirst;
    @ViewInject(R.id.tv_new_list_second)
    private TextView tvNewSecond;
    @ViewInject(R.id.tv_new_list_third)
    private TextView tvNewThird;
    //hot
    @ViewInject(R.id.tv_hot_list_first)
    private TextView tvHotFirst;
    @ViewInject(R.id.tv_hot_list_second)
    private TextView tvHotSecond;
    @ViewInject(R.id.tv_hot_list_third)
    private TextView tvHotThird;
    //billboard
    @ViewInject(R.id.tv_billboard_list_first)
    private TextView tvBillFirst;
    @ViewInject(R.id.tv_billboard_list_second)
    private TextView tvBillSecond;
    @ViewInject(R.id.tv_billboard_list_third)
    private TextView tvBillThird;
    //ktv
    @ViewInject(R.id.tv_ktv_list_first)
    private TextView tvKtvFirst;
    @ViewInject(R.id.tv_ktv_list_second)
    private TextView tvKtvSecond;
    @ViewInject(R.id.tv_ktv_list_third)
    private TextView tvKtvThird;
    @ViewInject(R.id.ibtn_local_music)
    private ImageButton ibtnLocalMusic;
    @ViewInject(R.id.ibtn_music_search)
    private ImageButton ibtnMusicSearch;

    private Intent intent;
    private int type;
    private List<Music> newList;
    private List<Music> hotList;
    private List<Music> billboardList;
    private List<Music> ktvList;

    private MusicSearchListAdapter musicSearchListAdapter;

    private IPresenterNet presenterNet;

    private IPresenterNetDetial presenterNetDetial;

    private Animation animation;

//    private MediaPlayer player;
    /**
     * 创建 music 控制器
     */
    private MusicPlayer musicPlayerContrl;

    public FragmentNetMusic() {
        presenterNet = new PresenterNetImpl(this);
        presenterNetDetial = new PresenterNetDetialImpl(this);

    }

    /**
     * 重新加载时，加载数据
     */
    @Override
    public void onResume() {
        super.onResume();
        FramAnimationUtil.framAnimationContrl(ibtnLocalMusic);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net, null);
        //LogUtil.logInfo(TAG, "onCreateView: fragmentNetMusic");
        x.view().inject(this, view);
        setData();
        setListeners();
        return view;
    }

    /**
     * 设置监听器
     */
    private void setListeners() {
        llNewList.setOnClickListener(this);
        llHotList.setOnClickListener(this);
        llBillboardList.setOnClickListener(this);
        llKtvList.setOnClickListener(this);
        ibtnLocalMusic.setOnClickListener(this);
        ibtnMusicSearch.setOnClickListener(this);
        lvSearchMusic.setOnItemClickListener(this);

    }


    /**
     * 设置数据源
     */
    private void setData() {
        musicPlayerContrl = MusicApplication.getMusicPlayer();
        presenterNet.loadNewMusics();
        presenterNet.loadHotMusics();
        presenterNet.loadBillboardMusics();
        presenterNet.loadKTVMusics();
    }

    /**
     * 设置点击事件，显示相应数据的列表
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_new_list:
                setIntentType(NEW);
                break;
            case R.id.ll_hot_list:
                setIntentType(HOT);
                break;
            case R.id.ll_billboard_list:
                setIntentType(BILLBOARD);
                break;
            case R.id.ll_ktv_list:
                setIntentType(KTV);
                break;
            case R.id.ibtn_local_music:
                if (MusicApplication.getContext().getPlayer().isPlaying()) {
                    intentPlayActivity();
                    return;
                } else {
                    // ToastUtil(getActivity(), "当前没有播放歌曲");
                    intentPlayActivity();
                }
                break;
            case R.id.ibtn_music_search:
                lvSearchMusic.setVisibility(View.VISIBLE);
                animation = new TranslateAnimation(0, 0, llNetFragment.getHeight(), lvSearchMusic.getHeight() + llNetFragment.getHeight());
                animation.setDuration(300);
                animation.start();
                String songName = etSearch.getText().toString().trim();
                if (songName != null) {
//                    LogUtil.logInfo(TAG, "songName: " + songName);
                    presenterNet.loadSearchMusics(songName);
                }
                break;


        }
        //LogUtil.logInfo(TAG, "Fragment onClick: " + type);


    }

    /**
     * 根据联网的数据类型跳转界面，并在跳转的界面中显示相应的数据
     */
    private void setIntentType(int type) {
        intent = new Intent(this.getActivity(), DetialMusicActivity.class);
        musicPlayerContrl.setMusicListType(type);
//               LogUtil.logInfo(TAG, "onClick: "+type);
        startActivity();
    }

    /**
     * 启动 activity 并显示动画
     */
    private void startActivity() {
        startActivity(intent);
        this.getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    /**
     * 启动 playActivity
     */
    private void intentPlayActivity() {
        intent = new Intent(this.getActivity(), PlayActivity.class);
        startActivity();
    }

    /**
     * 设置界面显示的第一、二、三首曲名
     *
     * @param musics
     */
    @Override
    public void setNewText(List<Music> musics) {
        setRankList(musics, tvNewFirst, tvNewSecond, tvNewThird);
    }

    @Override
    public void setHotText(List<Music> musics) {
        setRankList(musics, tvHotFirst, tvHotSecond, tvHotThird);
    }

    @Override
    public void setBillText(List<Music> musics) {
        setRankList(musics, tvBillFirst, tvBillSecond, tvBillThird);
    }

    @Override
    public void setKtvText(List<Music> musics) {
        setRankList(musics, tvKtvFirst, tvKtvSecond, tvKtvThird);
    }

    /**
     * 封装排名方法
     *
     * @param musics
     * @param tvFirst
     * @param tvSecond
     * @param tvThird
     */
    private static void setRankList(List<Music> musics, TextView tvFirst, TextView tvSecond, TextView tvThird) {
        tvFirst.setText(musics.get(0).getTitle());
        tvSecond.setText(musics.get(1).getTitle());
        tvThird.setText(musics.get(2).getTitle());
        MusicApplication.getMusicPlayer().setKtvLists(musics);
    }

    @Override
    public void setSearchText(ArrayList<SongList> songLists) {
        musicSearchListAdapter = new MusicSearchListAdapter(getContext(), songLists);
        lvSearchMusic.setAdapter(musicSearchListAdapter);
    }

    @Override
    public void playMusic(String songUrl) {

    }

    //无功能
    @Override
    public void setMusicData(List<Music> musics) {

    }

    @Override
    public void showMusicData() {

    }

    @Override
    public void playMusic(Object data1, Object data2) {
        //LogUtil.logInfo(TAG, "playMusic:111111 ");
        String songUrl = ((List<Url>) data1).get(0).getFile_link();
        if (songUrl != null) {
            MusicSevice.MusicBinder.playMusic(songUrl);
            intent = new Intent(getActivity(), PlayActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else {
            ToastUtil.showToast(getActivity(), "喵~~无此歌曲，抱歉");
        }
    }

    /**
     * 播放音乐
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//      LogUtil.logInfo(TAG, "onItemClick: "+position);
//      ToastUtil.showToast(getContext(),""+position);
        List<SongList> songLists = MusicApplication.getMusicPlayer().getSongLists();
        MusicApplication.getMusicPlayer().setMusicListType(SEARCH);
        MusicApplication.getMusicPlayer().setPosition(position);
        String songId = songLists.get(position).getSong_id();
        //LogUtil.logInfo(TAG, "onItemClick: " + songId);
        presenterNetDetial.setSong(songId);
        lvSearchMusic.setVisibility(View.GONE);
        animation = new TranslateAnimation(0, 0, lvSearchMusic.getHeight() + llNetFragment.getHeight(), llNetFragment.getHeight());
        animation.setDuration(300);
        animation.start();


    }

}
