package com.wangban.yzbbanban.banmusicplayer.entity;

import java.io.Serializable;

/**
 * Created by YZBbanban on 16/7/21.
 */
public class DownloadDoc implements Serializable {
    private String url;
    private String title;
    private int progress;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
