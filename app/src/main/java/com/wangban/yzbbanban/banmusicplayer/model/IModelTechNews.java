package com.wangban.yzbbanban.banmusicplayer.model;

/**
 * Created by YZBbanban on 16/7/11.
 */
public interface IModelTechNews {
    void findTechMessage(IDataCallback callback);
    void findTechMessageWithPage(int page,IDataCallback callback);
    void findTechDetailMessageWithPage(String DetailPath,IDataCallback callback);

}
