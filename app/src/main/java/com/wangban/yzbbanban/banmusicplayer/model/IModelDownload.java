package com.wangban.yzbbanban.banmusicplayer.model;

import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/21.
 */
public interface IModelDownload {
    void loadDownMusicMessage(DownloadDoc doc);

    List<DownloadDoc> loadDownMusicMessage();


}
