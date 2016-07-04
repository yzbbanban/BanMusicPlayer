package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.model.impl.NetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
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
        model.findAllNewMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setNewText(musics);
            }
        });
    }

    @Override
    public void loadHotMusics() {
        model.findAllHotMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setHotText(musics);
            }
        });
    }

    @Override
    public void loadBillboardMusics() {
        model.findAllBillboardMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setBillText(musics);
            }
        });
    }

    @Override
    public void loadKTVMusics() {
        model.findAllKtvMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setKtvText(musics);
            }
        });
    }

    @Override
    public void loadSearchMusics(String songName) {
        model.findAllSearchMusic(songName,new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics = (List<Music>) data;
                view.setSerchText(musics);
            }
        });
    }
}
