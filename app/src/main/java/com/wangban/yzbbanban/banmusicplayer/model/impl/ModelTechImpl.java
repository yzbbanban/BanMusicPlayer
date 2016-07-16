package com.wangban.yzbbanban.banmusicplayer.model.impl;

import android.os.AsyncTask;
import android.widget.ListView;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechDetialContent;
import com.wangban.yzbbanban.banmusicplayer.entity.TechMessage;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.model.IDataCallback;
import com.wangban.yzbbanban.banmusicplayer.model.IModelTechNews;
import com.wangban.yzbbanban.banmusicplayer.util.JsoupUtil;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.util.UrlFactory;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;

/**
 * Created by YZBbanban on 16/7/11.
 */
public class ModelTechImpl implements IModelTechNews, Consts {

    private TechMessage message;

    public ModelTechImpl() {
        this.message = MusicApplication.getMessage();
    }

    @Override
    public void findTechMessage(final IDataCallback callback) {
        new AsyncTask<Void, Void, List<TechNews>>() {

            @Override
            protected List<TechNews> doInBackground(Void... params) {
                try {
                    String url = UrlFactory.TECH_MESSAGE;

                    List<TechNews> techNewses = JsoupUtil.downTechNews(url);
                    message.setTechNewses(techNewses);
//                    LogUtil.logInfo(TAG, "fidTechNews: " + techNewses.get(0).getImagePath());
                    return techNewses;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<TechNews> techNewses) {
                callback.findAllData(techNewses);
            }
        }.execute();

    }

    @Override
    public void findTechMessageWithPage(final int page, final IDataCallback callback) {

        new AsyncTask<Void, Void, List<TechNews>>() {

            @Override
            protected List<TechNews> doInBackground(Void... params) {
                try {
                    String url = UrlFactory.getTechMessageWithPage(page);
                    message.getTechNewses().addAll(JsoupUtil.downTechNews(url));
                    List<TechNews> techNewses = message.getTechNewses();

                    //LogUtil.logInfo(TAG,"message: "+techNewses.size());

                    message.setTechNewses(techNewses);
                    return techNewses;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<TechNews> techNewses) {
                callback.findAllData(techNewses);
            }


        }.execute();
    }

    @Override
    public void findTechDetailMessageWithPage(final String detailPath, final IDataCallback callback) {
        new AsyncTask<Void, Void, TechDetialContent>() {

            @Override
            protected TechDetialContent doInBackground(Void... params) {
                try {
                    TechDetialContent content = JsoupUtil.downDetialTechNews(detailPath);
                    return content;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(TechDetialContent content) {

                callback.findAllData(content);

            }
        }.execute();
    }
}