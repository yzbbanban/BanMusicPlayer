package com.wangban.yzbbanban.banmusicplayer.model;

import com.wangban.yzbbanban.banmusicplayer.entity.Music;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/26.
 * 获取position的位置
 */
public interface IModelNetDetial {
    void getNextPosition();

    void getPreviousPosition();

    void setSong(String songId,INetMusicCallback callback);

    Object findAllNewMusic();

    Object findAllHotMusic();

    Object findAllBillMusic();

    Object findAllKtvMusic();

//    Object findAllSearchMusic();


}
