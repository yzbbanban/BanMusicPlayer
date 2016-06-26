package com.wangban.yzbbanban.banmusicplayer.entity;

/**
 * Created by YZBbanban on 16/6/23.
 */
import java.util.*;
public class QuestResultDetial {
    private int error_code;
    private List<Songinfo> songinfo;
    private List<SongUrl> songurl;

    public QuestResultDetial() {
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<Songinfo> getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(List<Songinfo> songinfo) {
        this.songinfo = songinfo;
    }

    public List<SongUrl> getSongurl() {
        return songurl;
    }

    public void setSongurl(List<SongUrl> songurl) {
        this.songurl = songurl;
    }
}
