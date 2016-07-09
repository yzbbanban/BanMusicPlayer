package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import android.util.Log;

import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;
import com.wangban.yzbbanban.banmusicplayer.model.IMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.model.impl.NetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class PresenterNetImpl implements IPresenterNet {

    private INetMusicModel model;
    private IViewNet view;

    public PresenterNetImpl(IViewNet view) {
        this.view = view;
        model = new NetMusicModel();

    }

    @Override
    public void loadNewMusics() {
        model.findAllNewMusic(new IMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setNewText(musics);
            }
        });
    }

    @Override
    public void loadHotMusics() {
        model.findAllHotMusic(new IMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setHotText(musics);
            }
        });
    }

    @Override
    public void loadBillboardMusics() {
        model.findAllBillboardMusic(new IMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setBillText(musics);
            }
        });
    }

    @Override
    public void loadKTVMusics() {
        model.findAllKtvMusic(new IMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setKtvText(musics);
            }
        });
    }

    @Override
    public void loadSearchMusics(String songName) {
//        LogUtil.logInfo("supergirl", "loadSearchMusics: " + songName);
        model.findAllSearchMusic(songName,new IMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                ArrayList<SongList> songLists = (ArrayList<SongList>) data;
                view.setSerchText(songLists);
            }
        });
    }
}
