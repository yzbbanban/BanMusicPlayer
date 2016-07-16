package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;
import com.wangban.yzbbanban.banmusicplayer.model.IDataCallback;
import com.wangban.yzbbanban.banmusicplayer.model.IModelNetMusic;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelNetMusicImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class PresenterNetImpl implements IPresenterNet {

    private IModelNetMusic model;
    private IViewNet view;

    public PresenterNetImpl(IViewNet view) {
        this.view = view;
        model = new ModelNetMusicImpl();

    }

    @Override
    public void loadNewMusics() {
        model.findAllNewMusic(new IDataCallback() {
            @Override
            public void findAllData(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setNewText(musics);
            }
        });
    }

    @Override
    public void loadHotMusics() {
        model.findAllHotMusic(new IDataCallback() {
            @Override
            public void findAllData(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setHotText(musics);
            }
        });
    }

    @Override
    public void loadBillboardMusics() {
        model.findAllBillboardMusic(new IDataCallback() {
            @Override
            public void findAllData(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setBillText(musics);
            }
        });
    }

    @Override
    public void loadKTVMusics() {
        model.findAllKtvMusic(new IDataCallback() {
            @Override
            public void findAllData(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setKtvText(musics);
            }
        });
    }

    @Override
    public void loadSearchMusics(String songName) {
//        LogUtil.logInfo("supergirl", "loadSearchMusics: " + songName);
        model.findAllSearchMusic(songName,new IDataCallback() {
            @Override
            public void findAllData(Object data) {
                ArrayList<SongList> songLists = (ArrayList<SongList>) data;
                view.setSearchText(songLists);
            }
        });
    }
}
