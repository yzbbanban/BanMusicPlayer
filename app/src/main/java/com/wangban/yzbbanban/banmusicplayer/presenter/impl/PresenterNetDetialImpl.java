package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.model.INetDetialModel;
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
        view.setMusicData(musics);
        view.showMusicData();
    }
}
