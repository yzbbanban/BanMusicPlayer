package com.wangban.yzbbanban.banmusicplayer.entity;
import java.util.*;
/**
 * Created by YZBbanban on 16/6/23.
 */
public class QueryResult {
    private Billboard billboard;
    private int error_code;
    private List<Music> song_list;

    public QueryResult() {
    }

    public QueryResult(Billboard billboard, int error_code, List<Music> song_list) {
        this.billboard = billboard;
        this.error_code = error_code;
        this.song_list = song_list;
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<Music> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<Music> song_list) {
        this.song_list = song_list;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "billboard=" + billboard +
                ", error_code=" + error_code +
                ", song_list=" + song_list +
                '}';
    }
}
