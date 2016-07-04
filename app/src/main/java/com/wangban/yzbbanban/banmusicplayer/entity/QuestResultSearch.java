package com.wangban.yzbbanban.banmusicplayer.entity;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/4.
 */
public class QuestResultSearch {

    private Album album;
    private int is_album;
    private int is_artist;
    private Pages pages;
    private String query;
    private String rs_words;
    private List<SongList> song_list;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getIs_album() {
        return is_album;
    }

    public void setIs_album(int is_album) {
        this.is_album = is_album;
    }

    public int getIs_artist() {
        return is_artist;
    }

    public void setIs_artist(int is_artist) {
        this.is_artist = is_artist;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRs_words() {
        return rs_words;
    }

    public void setRs_words(String rs_words) {
        this.rs_words = rs_words;
    }

    public List<SongList> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<SongList> song_list) {
        this.song_list = song_list;
    }
}
