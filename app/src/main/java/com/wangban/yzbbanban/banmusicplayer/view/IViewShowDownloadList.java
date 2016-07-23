package com.wangban.yzbbanban.banmusicplayer.view;

import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;

import java.util.List;

/**
 * Created by YZBbanban on 16/7/23.
 */

public interface IViewShowDownloadList {
    void showMessage(List<DownloadDoc> downloadDocs);
}
