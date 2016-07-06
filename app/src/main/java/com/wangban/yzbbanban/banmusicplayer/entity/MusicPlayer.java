package com.wangban.yzbbanban.banmusicplayer.entity;


import com.wangban.yzbbanban.banmusicplayer.consts.Consts;

import java.io.Serializable;
import java.util.*;

/**
 * Created by YZBbanban on 16/6/26.
 * 用来存放数据，集合，以及音乐的位置操作，为 music 的控制器
 */

public class MusicPlayer implements Serializable, Consts {
    private List<Music> newLists;
    private List<Music> hotLists;
    private List<Music> billboardLists;
    private List<Music> ktvLists;
    private List<LrcLine> lrcs;
    private List<SongList> songLists;

    //播放位置
    private int position;
    //    private int searchPosition;
    private int musicListType;

    //播放状态
    private int playState;

    public MusicPlayer() {

    }
//
//    public int getSearchPosition() {
//        return searchPosition;
//    }
//
//    public void setSearchPosition(int searchPosition) {
//        this.searchPosition = searchPosition;
//    }

    public List<SongList> getSongLists() {
        return songLists;
    }

    public void setSongLists(List<SongList> songLists) {
        this.songLists = songLists;
    }

    public int getPlayState() {
        return playState;
    }

    public void setPlayState(int playState) {
        this.playState = playState;
    }

    public List<LrcLine> getLrcs() {
        return lrcs;
    }

    public void setLrcs(List<LrcLine> lrcs) {
        this.lrcs = lrcs;
    }

    public List<Music> getNewLists() {
        return newLists;
    }

    public void setNewLists(List<Music> newLists) {
        this.newLists = newLists;
    }

    public List<Music> getHotLists() {
        return hotLists;
    }

    public void setHotLists(List<Music> hotLists) {
        this.hotLists = hotLists;
    }

    public List<Music> getBillboardLists() {
        return billboardLists;
    }

    public void setBillboardLists(List<Music> billboardLists) {
        this.billboardLists = billboardLists;
    }

    public List<Music> getKtvLists() {
        return ktvLists;
    }

    public void setKtvLists(List<Music> ktvLists) {
        this.ktvLists = ktvLists;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // 换上一首
    public void preMusic() {
        position = position == 0 ? 0 : position - 1;
    }

    // 换下一首
    public void nextMusic() {
        if (playState == RECYCLE) {
            switch (musicListType) {
                case NEW:
                    position = position == newLists.size() - 1 ? 0 : position + 1;
                    break;
                case HOT:
                    position = position == hotLists.size() - 1 ? 0 : position + 1;
                    break;
                case BILLBOARD:
                    position = position == billboardLists.size() - 1 ? 0 : position + 1;
                    break;
                case KTV:
                    position = position == ktvLists.size() - 1 ? 0 : position + 1;
                    break;
                case SEARCH:
                    position = position == songLists.size() - 1 ? 0 : position + 1;
            }
        } else if (playState == REPEAT) {
            switch (musicListType) {
                case NEW:
                    position++;
                    position--;
                    break;
                case HOT:
                    position++;
                    position--;
                    break;
                case BILLBOARD:
                    position++;
                    position--;
                    break;
                case KTV:
                    position++;
                    position--;
                    break;
                case SEARCH:
                    position++;
                    position--;
                    break;
            }
        } else if (playState == RANDOM) {
            switch (musicListType) {
                case NEW:
                    position = new Random().nextInt(newLists.size());
                    break;
                case HOT:
                    position = new Random().nextInt(hotLists.size());
                    break;
                case BILLBOARD:
                    position = new Random().nextInt(billboardLists.size());
                    break;
                case KTV:
                    position = new Random().nextInt(ktvLists.size());
                    break;
                case SEARCH:
                    position = new Random().nextInt(songLists.size());
                    break;
            }
        }


    }

    // 获取当前音乐
    public Object getCurrentMusic(int type) {
        Music music = new Music();
        SongList songList = new SongList();
        switch (type) {
            case NEW:
                music = newLists.get(position);
                break;
            case HOT:
                music = hotLists.get(position);
                break;
            case BILLBOARD:
                music = billboardLists.get(position);
                break;
            case KTV:
                music = ktvLists.get(position);
                break;
            case SEARCH:
                songList = songLists.get(position);
                return songList;
        }

        return music;
    }

    //设置当前播放列表类型
    public void setMusicListType(int musicListType) {
        this.musicListType = musicListType;

    }

    public int getMusicListType() {
        return musicListType;
    }

}
