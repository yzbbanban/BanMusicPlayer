package com.wangban.yzbbanban.banmusicplayer.model.impl;

import android.os.AsyncTask;

import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.model.IModelTechNews;
import com.wangban.yzbbanban.banmusicplayer.model.ITechCallback;
import com.wangban.yzbbanban.banmusicplayer.util.JsoupUtil;
import com.wangban.yzbbanban.banmusicplayer.util.UrlFactory;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;

/**
 * Created by YZBbanban on 16/7/11.
 */
public class ModelTechImpl implements IModelTechNews {

    @Override
    public void findTechMessage(final ITechCallback callback) {
        new AsyncTask<Void, Void, List<TechNews>>() {

            @Override
            protected List<TechNews> doInBackground(Void... params) {
                try {
                    String url = UrlFactory.TECH_MESSAGE;
                    List<TechNews> techNewses = JsoupUtil.downTechNews(url);
                    return techNewses;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<TechNews> techNewses) {
                callback.findTechMessage(techNewses);
            }
        };

    }
}
