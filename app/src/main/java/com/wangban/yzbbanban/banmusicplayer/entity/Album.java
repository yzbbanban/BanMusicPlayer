package com.wangban.yzbbanban.banmusicplayer.entity;

/**
 * Created by YZBbanban on 16/7/4.
 */
public class Album {
    private String album_id;
    private String pic_big;
    private String pic_small;
    private String publishcompany;
    private String publishtime;
    private String title;

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getPic_big() {
        return pic_big;
    }

    public void setPic_big(String pic_big) {
        this.pic_big = pic_big;
    }

    public String getPic_small() {
        return pic_small;
    }

    public void setPic_small(String pic_small) {
        this.pic_small = pic_small;
    }

    public String getPublishcompany() {
        return publishcompany;
    }

    public void setPublishcompany(String publishcompany) {
        this.publishcompany = publishcompany;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
