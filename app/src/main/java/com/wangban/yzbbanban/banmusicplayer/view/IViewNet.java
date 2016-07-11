package com.wangban.yzbbanban.banmusicplayer.view;

import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;

import java.util.*;
/**
 * Created by YZBbanban on 16/6/26.
 */
public interface IViewNet {
    void setNewText(List<Music> musics);
    void setHotText(List<Music> musics);
    void setBillText(List<Music> musics);
    void setKtvText(List<Music> musics);
    void setSearchText(ArrayList<SongList> songLists);
    void playMusic(String songUrl);
}
