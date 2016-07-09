package com.wangban.yzbbanban.banmusicplayer.model;

import com.wangban.yzbbanban.banmusicplayer.entity.Song;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/9.
 */
public interface IModelLocalMusic {
    List<Song> findAllLocalMusic();
}
