package com.wangban.yzbbanban.banmusicplayer.presenter.impl;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechDetialContent;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.model.IDataCallback;
import com.wangban.yzbbanban.banmusicplayer.model.IModelTechNews;
import com.wangban.yzbbanban.banmusicplayer.model.impl.ModelTechImpl;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterTechNews;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewTechNews;

import java.util.List;

/**
 * Created by YZBbanban on 16/7/11.
 */
public class PresenterTechNewsImpl implements IPresenterTechNews, Consts {
    private IViewTechNews view;
    private IModelTechNews model;

    public PresenterTechNewsImpl(IViewTechNews view) {
        this.view = view;
        model = new ModelTechImpl();
    }

    @Override
    public void loadNewsMessage() {
        model.findTechMessage(new IDataCallback() {
            @Override
            public void findAllData(Object object) {
                List<TechNews> techNewses = (List<TechNews>) object;
//                LogUtil.logInfo(TAG, "setTechNews: " + techNewses.get(0).getImagePath());
                view.setTechNews(techNewses);
                view.showTechNews();
            }
        });
    }

    @Override
    public void loadNewsMessageWithPage(int page) {

        model.findTechMessageWithPage(page, new IDataCallback() {
            @Override
            public void findAllData(Object object) {
                List<TechNews> techNewses = (List<TechNews>) object;
                view.setTechNews(techNewses);
                view.showTechNews();
            }
        });

    }

    @Override
    public void loadNewsDetialMessageWithPage(String detialPath) {
        model.findTechDetailMessageWithPage(detialPath,new IDataCallback() {
            @Override
            public void findAllData(Object object) {
                TechDetialContent content= (TechDetialContent) object;
                view.setTechNews(content);

            }
        });

    }
}
