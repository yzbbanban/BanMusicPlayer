package com.wangban.yzbbanban.banmusicplayer.presenter;

import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;

/**
 * Created by YZBbanban on 16/6/26.
 */
public interface IPresenterNet {

    void loadNewMusics();
    void loadHotMusics();
    void loadBillboardMusics();
    void loadKTVMusics();

}
