package com.wangban.yzbbanban.banmusicplayer.view;

import com.wangban.yzbbanban.banmusicplayer.entity.Song;

import java.util.List;

/**
 * Created by YZBbanban on 16/7/9.
 */
public interface IViewLocalMusic {
    void setData(List<Song> songs);

    void showData();

}
