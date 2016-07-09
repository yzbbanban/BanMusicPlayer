package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.Song;
import com.wangban.yzbbanban.banmusicplayer.model.IModelLocalMusic;
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
        model=new ModelLocalMusicImpl();
    }

    @Override
    public void loadLocalMusic() {
        List<Song> songs=model.findAllLocalMusic();
        view.setData(songs);
        view.showData();
    }
}
