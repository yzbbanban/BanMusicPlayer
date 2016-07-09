package com.wangban.yzbbanban.banmusicplayer.model;

/**
 * Created by YZBbanban on 16/6/23.
 */
public interface IModelNetMusic {
    void findAllNewMusic(IMusicCallback callback);
    void findAllHotMusic(IMusicCallback callback);
    void findAllBillboardMusic(IMusicCallback callback);
    void findAllKtvMusic(IMusicCallback callback);
    void findAllSearchMusic(String songName,IMusicCallback callback);

}
