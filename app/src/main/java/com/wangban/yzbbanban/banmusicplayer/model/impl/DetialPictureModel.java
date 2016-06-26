package com.wangban.yzbbanban.banmusicplayer.model.impl;


import android.os.AsyncTask;

import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.DetialImage;
import com.wangban.yzbbanban.banmusicplayer.util.JsoupUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZBbanban on 16/6/6.
 */
public class DetialPictureModel implements Consts {

    public void findDetilImageGridView(final Callback callback, final String webPath) {
        AsyncTask<String, String, List<DetialImage>> task = new AsyncTask<String, String, List<DetialImage>>() {
            List<DetialImage> images = new ArrayList<DetialImage>();

            @Override
            protected List<DetialImage> doInBackground(String... params) {
                try {
                    //Log.i(TAG, "doInBackground: hello" + webPath);
                    images = JsoupUtil.downDetilLoadData(webPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return images;
            }
            @Override
            protected void onPostExecute(List<DetialImage> images) {
                callback.onImageDownload(images);
            }
        };
        task.execute();
    }

    public interface Callback {
        void onImageDownload(List<DetialImage> list);
    }


}
