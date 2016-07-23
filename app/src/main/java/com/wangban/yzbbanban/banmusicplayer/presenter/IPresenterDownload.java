package com.wangban.yzbbanban.banmusicplayer.presenter;

import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;

/**
 * Created by YZBbanban on 16/7/21.
 */
public interface IPresenterDownload {
    void findDownloadMessage();
    void sendDownLoadMessage(DownloadDoc doc);
}
