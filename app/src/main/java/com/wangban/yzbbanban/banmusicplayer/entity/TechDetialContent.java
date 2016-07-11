package com.wangban.yzbbanban.banmusicplayer.entity;

/**
 * Created by YZBbanban on 16/7/12.
 */
public class TechDetialContent {
    private String content;
    private String imagePath;

    public TechDetialContent() {
    }

    public TechDetialContent(String content, String imagePath) {
        this.content = content;
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
