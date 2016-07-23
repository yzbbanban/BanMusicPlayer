package com.wangban.yzbbanban.banmusicplayer.model.impl;

import android.util.Log;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadInfo;
import com.wangban.yzbbanban.banmusicplayer.model.IModelDownload;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/21.
 */
public class ModelDownloadImpl implements IModelDownload {
    private DownloadInfo info;

    public ModelDownloadImpl() {
        this.info = MusicApplication.getInfo();
    }

    @Override
    public void loadDownMusicMessage(DownloadDoc doc) {
//        Log.i("supergirl", "loadDownMusicMessage: "+doc.getTitle());
        info.downloadItems(doc);
    }

    @Override
    public List<DownloadDoc> loadDownMusicMessage() {
        return null;
    }
}