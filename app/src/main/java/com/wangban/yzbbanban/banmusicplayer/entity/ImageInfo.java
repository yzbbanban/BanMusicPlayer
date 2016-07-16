package com.wangban.yzbbanban.banmusicplayer.entity;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class ImageInfo {
    private List<Image> images;

    public ImageInfo() {
    }

    public ImageInfo(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
