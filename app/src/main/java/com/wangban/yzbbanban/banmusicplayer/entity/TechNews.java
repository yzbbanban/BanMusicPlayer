package com.wangban.yzbbanban.banmusicplayer.entity;

import java.io.Serializable;

/**
 * Created by YZBbanban on 16/7/11.
 */
public class TechNews implements Serializable{
    private String title;
    private String imagePath;
    private String detialPath;
    private String detialContent;

    public TechNews() {
    }

    public TechNews(String title, String imagePath, String detialPath, String detialContent) {
        this.title = title;
        this.imagePath = imagePath;
        this.detialPath = detialPath;
        this.detialContent = detialContent;
    }

    public String getDetialContent() {
        return detialContent;
    }

    public void setDetialContent(String detialContent) {
        this.detialContent = detialContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDetialPath() {
        return detialPath;
    }

    public void setDetialPath(String detialPath) {
        this.detialPath = detialPath;
    }
}
