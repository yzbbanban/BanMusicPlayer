package com.wangban.yzbbanban.banmusicplayer.entity;
import java.util.*;
/**
 * Created by YZBbanban on 16/6/26.
 * 用来存放数据，集合，以及音乐的位置操作
 */

public class MusicPlayer {
    private List<Music> newList;
    private List<Music> hotList;
    private List<Music> billboardList;
    private List<Music> ktvList;

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






}
