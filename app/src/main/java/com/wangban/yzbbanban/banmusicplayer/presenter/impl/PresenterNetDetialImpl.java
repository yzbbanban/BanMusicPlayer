package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.model.impl.NetMusicModel;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class PresenterNetDetialImpl implements IPresenterNetDetial, Consts {
    private IViewNet view;
    private INetMusicModel model;
    private int type;

    public PresenterNetDetialImpl(IViewNet view, int type) {
        this.view = view;
        this.type = type;
        model = new NetMusicModel();

    }

    @Override
    public void loadAllMusics() {
        switch (type) {
            case NEW:
                model.findAllNewMusic(new INetMusicCallback() {
                    @Override
                    public void findAllMusic(Object data) {
                        List<Music> musics = (List<Music>) data;
                        view.setMusicData(musics);
                        view.showMusicData();
                    }
                });
                break;
            case HOT:
                model.findAllHotMusic(new INetMusicCallback() {
                    @Override
                    public void findAllMusic(Object data) {
                        List<Music> musics = (List<Music>) data;
                        view.setMusicData(musics);
                        view.showMusicData();
                    }
                });
                break;
            case BILLBOARD:
                model.findAllBillboardMusic(new INetMusicCallback() {
                    @Override
                    public void findAllMusic(Object data) {
                        List<Music> musics = (List<Music>) data;
                        view.setMusicData(musics);
                        view.showMusicData();
                    }
                });
                break;
            case KTV:
                model.findAllKtvMusic(new INetMusicCallback() {
                    @Override
                    public void findAllMusic(Object data) {
                        List<Music> musics = (List<Music>) data;
                        view.setMusicData(musics);
                        view.showMusicData();
                    }
                });
                break;

        }


    }
}
