package com.wangban.yzbbanban.banmusicplayer.entity;

import java.io.Serializable;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class Url {
    boolean can_load;

    private int can_see;
    private int down_type;
    private int file_bitrate;
    private int file_duration;
    private int file_size;
    private int original;
    private double preload;
    private int is_udition_url;
    private int free;
    private int song_file_id;

    private double replay_gain;

    private String file_link;
    private String file_extension;
    private String hash;
    private String show_link;

    public Url() {
    }

    public Url(boolean can_load, int can_see, int down_type, int file_bitrate, int file_duration, int file_size, int original, int preload, int is_udition_url, int free, int song_file_id, double replay_gain, String file_link, String file_extension, String hash, String show_link) {
        this.can_load = can_load;
        this.can_see = can_see;
        this.down_type = down_type;
        this.file_bitrate = file_bitrate;
        this.file_duration = file_duration;
        this.file_size = file_size;
        this.original = original;
        this.preload = preload;
        this.is_udition_url = is_udition_url;
        this.free = free;
        this.song_file_id = song_file_id;
        this.replay_gain = replay_gain;
        this.file_link = file_link;
        this.file_extension = file_extension;
        this.hash = hash;
        this.show_link = show_link;
    }

    public boolean isCan_load() {
        return can_load;
    }

    public void setCan_load(boolean can_load) {
        this.can_load = can_load;
    }

    public int getCan_see() {
        return can_see;
    }

    public void setCan_see(int can_see) {
        this.can_see = can_see;
    }

    public int getDown_type() {
        return down_type;
    }

    public void setDown_type(int down_type) {
        this.down_type = down_type;
    }

    public int getFile_bitrate() {
        return file_bitrate;
    }

    public void setFile_bitrate(int file_bitrate) {
        this.file_bitrate = file_bitrate;
    }

    public int getFile_duration() {
        return file_duration;
    }

    public void setFile_duration(int file_duration) {
        this.file_duration = file_duration;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public double getPreload() {
        return preload;
    }

    public void setPreload(double preload) {
        this.preload = preload;
    }

    public int getIs_udition_url() {
        return is_udition_url;
    }

    public void setIs_udition_url(int is_udition_url) {
        this.is_udition_url = is_udition_url;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getSong_file_id() {
        return song_file_id;
    }

    public void setSong_file_id(int song_file_id) {
        this.song_file_id = song_file_id;
    }

    public double getReplay_gain() {
        return replay_gain;
    }

    public void setReplay_gain(double replay_gain) {
        this.replay_gain = replay_gain;
    }

    public String getFile_link() {
        return file_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }

    public String getFile_extension() {
        return file_extension;
    }

    public void setFile_extension(String file_extension) {
        this.file_extension = file_extension;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getShow_link() {
        return show_link;
    }

    public void setShow_link(String show_link) {
        this.show_link = show_link;
    }
}
