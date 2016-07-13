package com.wangban.yzbbanban.banmusicplayer.entity;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/12.
 */
public class TechMessage {
    private List<TechNews> techNewses;
    private List<TechDetialContent> contents;
    private int techPosition;


    public int getTechPosition() {
        return techPosition;
    }

    public void setTechPosition(int techPosition) {
        this.techPosition = techPosition;
    }

    public List<TechNews> getTechNewses() {
        return techNewses;
    }

    public void setTechNewses(List<TechNews> techNewses) {
        this.techNewses = techNewses;
    }

    public List<TechDetialContent> getContents() {
        return contents;
    }

    public void setContents(List<TechDetialContent> contents) {
        this.contents = contents;
    }
}
