package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.model.impl.NetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class PresenterNetImpl implements IPresenterNet {
    private IViewNet view;
    private INetMusicModel model;

    public PresenterNetImpl(IViewNet view) {
        this.view = view;
        model=new NetMusicModel();
    }
    @Override
    public void loadAllMusics() {
        model.findAllMusic(new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Music> musics= (List<Music>) data;
                view.setMusicData(musics);
                view.showMusicData();
            }
        });


    }
}
