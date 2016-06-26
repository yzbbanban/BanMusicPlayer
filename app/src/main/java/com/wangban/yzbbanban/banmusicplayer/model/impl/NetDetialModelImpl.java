package com.wangban.yzbbanban.banmusicplayer.model.impl;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.model.INetDetialModel;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class NetDetialModelImpl implements INetDetialModel {
    private MusicPlayer musicPlayer;
    private List<Music> musics;

    public NetDetialModelImpl() {
        musicPlayer = MusicApplication.getMusicPlayer();
        musics=new ArrayList<Music>();
    }

    @Override
    public void getNextPosition() {

    }

    @Override
    public void getPreviousPosition() {

    }

    @Override
    public Object findAllNewMusic() {
        musics=musicPlayer.getNewList();
        return musics;
    }

    @Override
    public Object findAllHotMusic() {
        musics=musicPlayer.getHotList();
        return musics;
    }

    @Override
    public Object findAllBillMusic() {
        musics=musicPlayer.getBillboardList();
        return musics;
    }

    @Override
    public Object findAllKtvMusic() {
        musics=musicPlayer.getKtvList();
        return musics;
    }


}
