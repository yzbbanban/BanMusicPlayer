package com.wangban.yzbbanban.banmusicplayer.model.impl;


import android.os.AsyncTask;
import android.util.Log;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.util.JsoupUtil;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZBbanban on 16/6/6.
 */
public class MainPictureModel implements Consts {

    public MainPictureModel() {
    }


    public void findImageGridView(final Callback callback, final String webPath) {
        AsyncTask<String, String, List<Image>> task = new AsyncTask<String, String, List<Image>>() {
            List<Image> images = new ArrayList<Image>();

            @Override
            protected List<Image> doInBackground(String... params) {
                try {
                    //LogUtil.logInfo(TAG, "doInBackground: " + webPath);
                    images = JsoupUtil.downLoadData(webPath);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return images;
            }

            @Override
            protected void onPostExecute(List<Image> images) {
                callback.onImageDownload(images);
            }
        };
        task.execute();
    }

    public interface Callback {
        void onImageDownload(List<Image> list);
    }
}
