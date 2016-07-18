package com.wangban.yzbbanban.banmusicplayer.view;

/**
 * Created by YZBbanban on 16/7/17.
 */
public interface IViewDownLoad {
    void setProgressMax(int progress);
    void setProgressCurrent(int progress);
    void sendFailureMessage();
}
