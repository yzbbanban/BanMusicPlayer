package com.wangban.yzbbanban.banmusicplayer.model;

/**
 * Created by YZBbanban on 16/6/23.
 */
public interface INetMusicModel {
    void findAllNewMusic(INetMusicCallback callback);
    void findAllHotMusic(INetMusicCallback callback);
    void findAllBillboardMusic(INetMusicCallback callback);
    void findAllKtvMusic(INetMusicCallback callback);
    void findAllSearchMusic(String songName,INetMusicCallback callback);

}
