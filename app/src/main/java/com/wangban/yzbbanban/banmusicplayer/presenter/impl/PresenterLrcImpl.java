package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.LrcLine;
import com.wangban.yzbbanban.banmusicplayer.model.IModelLrc;
import com.wangban.yzbbanban.banmusicplayer.model.IMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelLrcImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterLrc;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLrc;
import java.util.*;
/**
 * Created by YZBbanban on 16/7/2.
 */
public class PresenterLrcImpl implements IPresenterLrc{
    private IViewLrc view;
    private IModelLrc model;

    public PresenterLrcImpl(IViewLrc view) {
        this.view = view;
        model=new ModelLrcImpl();
    }

    @Override
    public void loadLrc(String lrcUrl) {
        model.getLrc(lrcUrl,new IMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<LrcLine> lrcLines= (List<LrcLine>) data;
                view.setLrc(lrcLines);
            }
        });
    }
}
