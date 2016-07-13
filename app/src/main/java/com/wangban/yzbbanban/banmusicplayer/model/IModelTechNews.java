package com.wangban.yzbbanban.banmusicplayer.model;

/**
 * Created by YZBbanban on 16/7/11.
 */
public interface IModelTechNews {
    void findTechMessage(ITechCallback callback);
    void findTechMessageWithPage(int page,ITechCallback callback);
    void findTechDetailMessageWithPage(String DetailPath,ITechCallback callback);

}
