package com.wangban.yzbbanban.banmusicplayer.view;

import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import java.util.*;
/**
 * Created by YZBbanban on 16/7/17.
 */
public interface IViewDownLoad {
    void setProgressMax(int progress);
    void setProgressCurrent(int progress);
    void sendFailureMessage();
    void showMessage(List<DownloadDoc> downloadDocs);
}

