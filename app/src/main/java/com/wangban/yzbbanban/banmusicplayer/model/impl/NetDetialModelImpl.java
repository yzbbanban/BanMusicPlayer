package com.wangban.yzbbanban.banmusicplayer.model.impl;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.model.INetDetialModel;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class NetDetialModelImpl implements INetDetialModel {
    private MusicPlayer musicPlayer;

    public NetDetialModelImpl() {
        musicPlayer = MusicApplication.getMusicPlayer();
    }

    @Override
    public void getNextPosition() {

    }

    @Override
    public void getPreviousPosition() {

    }
}
