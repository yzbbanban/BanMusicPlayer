package com.wangban.yzbbanban.banmusicplayer.model;

/**
 * Created by YZBbanban on 16/6/23.
 */
public interface IModelNetMusic {
    void findAllNewMusic(IDataCallback callback);
    void findAllHotMusic(IDataCallback callback);
    void findAllBillboardMusic(IDataCallback callback);
    void findAllKtvMusic(IDataCallback callback);
    void findAllSearchMusic(String songName,IDataCallback callback);

}
