package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.model.impl.NetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class PresenterNet implements IPresenterNet{

    private INetMusicModel model;

    public PresenterNet() {
        model=new NetMusicModel();
    }

    @Override
    public void loadNewMusics() {
        model.findAllNewMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {

            }
        });
    }

    @Override
    public void loadHotMusics() {
        model.findAllHotMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {

            }
        });
    }

    @Override
    public void loadBillboardMusics() {
        model.findAllBillboardMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {

            }
        });
    }

    @Override
    public void loadKTVMusics() {
        model.findAllKtvMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {

            }
        });
    }
}
