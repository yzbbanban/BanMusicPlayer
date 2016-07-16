package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.model.IDataCallback;
import com.wangban.yzbbanban.banmusicplayer.model.IModelImage;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelImageImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPreseterImage;
import com.wangban.yzbbanban.banmusicplayer.view.IViewImage;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class PresenterImageImpl implements IPreseterImage {
    private IViewImage view;
    private IModelImage model;

    public PresenterImageImpl(IViewImage view) {
        this.view = view;
        this.model = new ModelImageImpl();
    }

    @Override
    public void loadImageData(int page) {
        model.findImageData(page, new IDataCallback() {
            @Override
            public void findAllData(Object data) {
                List<Image> images = (List<Image>) data;
                view.setImageData(images);
                view.showImageData();
            }
        });
    }
}
