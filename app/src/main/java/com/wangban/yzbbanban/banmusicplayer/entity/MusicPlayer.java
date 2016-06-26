package com.wangban.yzbbanban.banmusicplayer.entity;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;

import java.util.*;
/**
 * Created by YZBbanban on 16/6/26.
 * 用来存放数据，集合，以及音乐的位置操作
 */

public class MusicPlayer implements Consts{
    private List<Music> newList;
    private List<Music> hotList;
    private List<Music> billboardList;
    private List<Music> ktvList;
    //播放位置
    private int position;

    public MusicPlayer() {

    }

    public List<Music> getNewList() {
        return newList;
    }

    public void setNewList(List<Music> newList) {
        this.newList = newList;
    }

    public List<Music> getHotList() {
        return hotList;
    }

    public void setHotList(List<Music> hotList) {
        this.hotList = hotList;
    }

    public List<Music> getBillboardList() {
        return billboardList;
    }

    public void setBillboardList(List<Music> billboardList) {
        this.billboardList = billboardList;
    }

    public List<Music> getKtvList() {
        return ktvList;
    }

    public void setKtvList(List<Music> ktvList) {
        this.ktvList = ktvList;
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
    public void nextMusic(int type) {
        switch (type) {
            case NEW:
                position = position == newList.size() - 1 ? 0 : position + 1;
                break;
            case HOT:
                position = position == hotList.size() - 1 ? 0 : position + 1;
                break;
            case BILLBOARD:
                position = position == billboardList.size() - 1 ? 0 : position + 1;
                break;
            case KTV:
                position = position == ktvList.size() - 1 ? 0 : position + 1;
                break;
        }

    }

    // 获取当前音乐
    public Music getCurrentMusic(int type) {
        Music music=new Music();
        switch (type) {
            case NEW:
                music=newList.get(position);
                break;
            case HOT:
                music=hotList.get(position);
                break;
            case BILLBOARD:
                music=billboardList.get(position);
                break;
            case KTV:
                music=ktvList.get(position);
                break;
        }

        return music;
    }

}
