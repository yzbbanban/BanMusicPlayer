package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.Song;
import com.wangban.yzbbanban.banmusicplayer.model.IModelLocalMusic;
import com.wangban.yzbbanban.banmusicplayer.model.IMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelLocalMusicImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IpresenterLocalMusic;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLocalMusic;

import java.util.List;

/**
 * Created by YZBbanban on 16/7/9.
 */
public class PresenterLocalMusic implements IpresenterLocalMusic {
    private IViewLocalMusic view;
    private IModelLocalMusic model;

    public PresenterLocalMusic(IViewLocalMusic view) {
        this.view = view;
        model = new ModelLocalMusicImpl();
    }

    @Override
    public void loadLocalMusic() {
        model.findAllLocalMusic(new IMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<Song> songs = (List<Song>) data;
                view.setData(songs);
                view.showData();
            }
        });

    }
}
