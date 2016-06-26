package com.wangban.yzbbanban.banmusicplayer.entity;

/**
 * Created by YZBbanban on 16/6/23.
 */

import java.util.*;

public class SongUrl {
    private List<Url> url;

    public SongUrl() {
    }

    public SongUrl(List<Url> url) {
        this.url = url;
    }

    public List<Url> getUrl() {
        return url;
    }

    public void setUrl(List<Url> url) {
        this.url = url;
    }
}
