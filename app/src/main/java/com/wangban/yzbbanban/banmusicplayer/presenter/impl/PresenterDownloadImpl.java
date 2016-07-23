package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import android.util.Log;

import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.model.IModelDownload;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelDownloadImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterDownload;
import com.wangban.yzbbanban.banmusicplayer.view.IViewDownLoad;
import com.wangban.yzbbanban.banmusicplayer.view.IViewShowDownloadList;

import java.util.*;


/**
 * Created by YZBbanban on 16/7/21.
 */
public class PresenterDownloadImpl implements IPresenterDownload {
    private IViewDownLoad view;
    private IModelDownload model;
    private IViewShowDownloadList viewList;

    public PresenterDownloadImpl() {
        model = new ModelDownloadImpl();
    }

    public PresenterDownloadImpl(IViewShowDownloadList viewlist) {
        this.viewList = viewlist;
        model = new ModelDownloadImpl();
    }


    public PresenterDownloadImpl(IViewDownLoad view) {
        this.view = view;
        model = new ModelDownloadImpl();
    }

    @Override
    public void findDownloadMessage() {
        List<DownloadDoc> downloadDocs = model.loadDownMusicMessage();
        Log.i("supergirl", "findDownloadMessage: " + downloadDocs.get(0).getTitle());
        viewList.showMessage(downloadDocs);
    }

    @Override
    public void sendDownLoadMessage(DownloadDoc doc) {
//        Log.i("supergirl", "sendDownLoadMessage: "+doc.getTitle());
        model.loadDownMusicMessage(doc);

    }


}
