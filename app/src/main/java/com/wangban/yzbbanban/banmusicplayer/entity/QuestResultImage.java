package com.wangban.yzbbanban.banmusicplayer.entity;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class QuestResultImage {
    private boolean error;
    private List<Image> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Image> getResults() {
        return results;
    }

    public void setResults(List<Image> results) {
        this.results = results;
    }
}
