package com.wangban.yzbbanban.banmusicplayer.entity;

/**
 * Created by YZBbanban on 16/6/23.
 */
import java.util.*;
public class QuestResultDetial {
    private int error_code;
    private List<SongInfo> songinfo;
    private List<SongUrl> songurl;

    public QuestResultDetial() {
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongInfo> getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(List<SongInfo> songinfo) {
        this.songinfo = songinfo;
    }

    public List<SongUrl> getSongurl() {
        return songurl;
    }

    public void setSongurl(List<SongUrl> songurl) {
        this.songurl = songurl;
    }
}
