package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.SongInfo;
import com.wangban.yzbbanban.banmusicplayer.entity.Url;
import com.wangban.yzbbanban.banmusicplayer.model.IModelNetDetial;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelNetDetialImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNetDetial;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNetDetial;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class PresenterNetDetialImpl implements IPresenterNetDetial, Consts {
    private IViewNetDetial view;
    private IModelNetDetial model;
    private int type;


    public PresenterNetDetialImpl(IViewNetDetial view, int type) {
        this.view = view;
        this.type = type;
        model = new ModelNetDetialImpl();

    }

    public PresenterNetDetialImpl(IViewNetDetial view) {
        this.view = view;
        model = new ModelNetDetialImpl();
    }

    /**
     * 根据类型获取 music
     */
    @Override
    public void loadAllMusics() {
        List<Music> musics = null;
//        List<SongList> songLists = null;

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
//            case SEARCH:
//                songLists = (List<SongList>) model.findAllSearchMusic();
        }
        if (musics != null) {
            view.setMusicData(musics);
            view.showMusicData();
        }
    }

    /**
     * 播放音乐的路径回调
     *
     * @param songId
     */
    @Override
    public void setSong(String songId) {
        model.setSong(songId, new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data, Object data2) {
                List<Url> urls = (List<Url>) data;
                SongInfo songInfo = (SongInfo) data2;
                view.playMusic(urls, songInfo);
            }


        });

    }


}
