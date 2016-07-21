package com.wangban.yzbbanban.banmusicplayer.model.impl;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.model.IModelDownload;

import java.util.List;

/**
 * Created by YZBbanban on 16/7/21.
 */
public class ModelDownloadImpl implements IModelDownload {

    @Override
    public List<DownloadDoc> loadDownMusicMessage() {
        List<DownloadDoc> downloadDocs = MusicApplication.getContext().getDownloadDoc();

        return downloadDocs;
    }
}
