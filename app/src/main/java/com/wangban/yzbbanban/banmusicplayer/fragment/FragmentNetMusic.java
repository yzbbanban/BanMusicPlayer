package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.DetialMusicActivity;
import com.wangban.yzbbanban.banmusicplayer.activity.PlayActivity;
import com.wangban.yzbbanban.banmusicplayer.adapter.MusicListAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetImpl;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;

/**
 * Created by YZBbanban on 16/6/23.
 */
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class FragmentNetMusic extends Fragment implements IViewNet, View.OnClickListener, Consts {
    @ViewInject(R.id.ll_new_list)
    private LinearLayout llNewList;
    @ViewInject(R.id.ll_hot_list)
    private LinearLayout llHotList;
    @ViewInject(R.id.ll_billboard_list)
    private LinearLayout llBillboardList;
    @ViewInject(R.id.ll_ktv_list)
    private LinearLayout llKtvList;
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

    private Intent intent;
    private int type;
    private List<Music> newList;
    private List<Music> hotList;
    private List<Music> billboardList;
    private List<Music> ktvList;

    private MusicListAdapter musicListAdapter;

    private IPresenterNet PresenterNet;

    /**
     * 创建 music 控制器
     */
    private MusicPlayer musicPlayerContrl;

    public FragmentNetMusic() {
        PresenterNet = new PresenterNetImpl(this);

    }

    /**
     * 重新加载时，加载数据
     */
    @Override
    public void onResume() {
        super.onResume();
        AnimationDrawable animationDrawable = null;
        //Log.i(TAG, "onResume: fragmentNetMusic");
        if (MusicApplication.getContext().getPlayer().isPlaying()) {
            animationDrawable = (AnimationDrawable) ibtnLocalMusic.getBackground();
            animationDrawable.setOneShot(false);
            animationDrawable.start();
        } else {
            ibtnLocalMusic.clearAnimation();
        }
//        else {
//            animationDrawable = (AnimationDrawable) ibtnLocalMusic.getBackground();
//            Log.i(TAG, "onResume: stop 帧动画");
//            animationDrawable.setOneShot(true);
//            animationDrawable.start();
//        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net, null);
        //Log.i(TAG, "onCreateView: fragmentNetMusic");
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
    }


    /**
     * 设置数据源
     */
    private void setData() {
        musicPlayerContrl = MusicApplication.getMusicPlayer();
        PresenterNet.loadNewMusics();
        PresenterNet.loadHotMusics();
        PresenterNet.loadBillboardMusics();
        PresenterNet.loadKTVMusics();
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

                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = NEW;
                musicPlayerContrl.setMusicListType(type);
//                Log.i(TAG, "onClick: new");
                break;
            case R.id.ll_hot_list:
                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = HOT;
                musicPlayerContrl.setMusicListType(type);
//                Log.i(TAG, "onClick: hot");
                break;
            case R.id.ll_billboard_list:
                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = BILLBOARD;
                musicPlayerContrl.setMusicListType(type);
//                Log.i(TAG, "onClick: billboard");
                break;
            case R.id.ll_ktv_list:
                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = KTV;
                musicPlayerContrl.setMusicListType(type);
//                Log.i(TAG, "onClick: ktv");
                break;
            case R.id.ibtn_local_music:
                if (MusicApplication.getContext().getPlayer().isPlaying()) {
                    intentPlayActivity();
                    return;
                } else {
                    Toast.makeText(getActivity(), "当前没有播放歌曲", Toast.LENGTH_SHORT).show();
                    intentPlayActivity();
                }
                break;
            case R.id.ibtn_music_search:
                lvSearchMusic.setVisibility(View.VISIBLE);
                String songName=etSearch.getText().toString().trim();
                PresenterNet.loadSearchMusics(songName);
                break;


        }
        //Log.i(TAG, "Fragment onClick: " + type);
        startActivity(intent);
        this.getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);

    }

    private void intentPlayActivity() {
        intent = new Intent(this.getActivity(), PlayActivity.class);
        startActivity(intent);
        this.getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    /**
     * 设置界面显示的第一、二、三首曲名
     *
     * @param musics
     */
    @Override
    public void setNewText(List<Music> musics) {
        tvNewFirst.setText(musics.get(0).getTitle());
        tvNewSecond.setText(musics.get(1).getTitle());
        tvNewThird.setText(musics.get(2).getTitle());
        MusicApplication.getMusicPlayer().setNewList(musics);
    }

    @Override
    public void setHotText(List<Music> musics) {
        tvHotFirst.setText(musics.get(0).getTitle());
        tvHotSecond.setText(musics.get(1).getTitle());
        tvHotThird.setText(musics.get(2).getTitle());
        MusicApplication.getMusicPlayer().setHotList(musics);
    }

    @Override
    public void setBillText(List<Music> musics) {
        tvBillFirst.setText(musics.get(0).getTitle());
        tvBillSecond.setText(musics.get(1).getTitle());
        tvBillThird.setText(musics.get(2).getTitle());
        MusicApplication.getMusicPlayer().setBillboardList(musics);
    }

    @Override
    public void setKtvText(List<Music> musics) {
        tvKtvFirst.setText(musics.get(0).getTitle());
        tvKtvSecond.setText(musics.get(1).getTitle());
        tvKtvThird.setText(musics.get(2).getTitle());
        MusicApplication.getMusicPlayer().setKtvList(musics);
    }

    @Override
    public void setSerchText(List<Music> musics) {
        musicListAdapter = new MusicListAdapter(getContext(), (ArrayList<Music>) musics);
        lvSearchMusic.setAdapter(musicListAdapter);
    }


}
