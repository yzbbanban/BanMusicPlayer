package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.LrcLine;
import com.wangban.yzbbanban.banmusicplayer.model.ILrcModel;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.impl.LrcModelImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterLrc;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLrc;
import java.util.*;
/**
 * Created by YZBbanban on 16/7/2.
 */
public class PresenterLrcImpl implements IPresenterLrc{
    private IViewLrc view;
    private ILrcModel model;

    public PresenterLrcImpl(IViewLrc view) {
        this.view = view;
        model=new LrcModelImpl();
    }

    @Override
    public void loadLrc(String lrcUrl) {
        model.getLrc(lrcUrl,new INetMusicCallback() {
            @Override
            public void findAllMusic(Object data) {
                List<LrcLine> lrcLines= (List<LrcLine>) data;
                view.setLrc(lrcLines);
            }
        });
    }
}
