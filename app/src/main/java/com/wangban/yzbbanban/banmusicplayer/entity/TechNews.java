package com.wangban.yzbbanban.banmusicplayer.entity;

/**
 * Created by YZBbanban on 16/7/11.
 */
public class TechNews {
    private String title;
    private String imagePath;
    private String message;

    public TechNews() {
    }

    public TechNews(String title, String imagePath, String message) {
        this.title = title;
        this.imagePath = imagePath;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
