package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.model.IModelTechNews;
import com.wangban.yzbbanban.banmusicplayer.model.ITechCallback;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelTechImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterTechNews;
import com.wangban.yzbbanban.banmusicplayer.view.IViewTechNews;

import java.util.List;

/**
 * Created by YZBbanban on 16/7/11.
 */
public class PresenterTechNewsImpl implements IPresenterTechNews{
    private IViewTechNews view;
    private IModelTechNews model;

    public PresenterTechNewsImpl(IViewTechNews view) {
        this.view = view;
        model=new ModelTechImpl();
    }

    @Override
    public void loadNewsMessage() {
        model.findTechMessage(new ITechCallback() {
            @Override
            public void findTechMessage(List<TechNews> techNewses) {
                view.setTechNews(techNewses);
                view.showTechNews();
            }
        });
    }
}
