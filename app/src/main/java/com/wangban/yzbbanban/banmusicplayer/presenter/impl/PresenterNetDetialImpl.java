package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import android.util.Log;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.model.INetDetialModel;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.impl.NetDetialModelImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class PresenterNetDetialImpl implements IPresenterNetDetial, Consts {
    private IViewNetDetial view;
    private INetDetialModel model;
    private int type;


    public PresenterNetDetialImpl(IViewNetDetial view, int type) {
        this.view = view;
        this.type = type;
        model = new NetDetialModelImpl();

    }

    public PresenterNetDetialImpl(IViewNetDetial view) {
        this.view = view;
        model = new NetDetialModelImpl();
    }

    /**
     * 根据类型获取 music
     */
    @Override
    public void loadAllMusics() {
        List<Music> musics = null;
        switch (type) {
            case NEW:
                musics = (List<Music>) model.findAllNewMusic();
                break;
            case HOT:
                musics = (List<Music>) model.findAllHotMusic();
                break;
            case BILLBOARD:
                musics = (List<Music>) model.findAllBillMusic();
                break;
            case KTV:
                musics = (List<Music>) model.findAllKtvMusic();
                break;
        }
        if (musics != null) {
            view.setMusicData(musics);
            view.showMusicData();
        }
    }

    /**
     * 播放音乐的路径回调
     * @param songId
     */
    @Override
    public void setSongUrl(String songId) {
        model.setSongUrl(songId, new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                //Log.i(TAG, "findAllMusic: " + data);
                String url = (String) data;
                view.playMusic(url);
            }
        });

    }


}
