package com.wangban.yzbbanban.banmusicplayer.model.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.entity.QuestResultImage;
import com.wangban.yzbbanban.banmusicplayer.model.IDataCallback;
import com.wangban.yzbbanban.banmusicplayer.model.IModelImage;
import com.wangban.yzbbanban.banmusicplayer.util.UrlFactory;
import java.util.*;
/**
 * Created by YZBbanban on 16/7/16.
 */
public class ModelImageImpl implements IModelImage {
    private List<Image> images;
    public ModelImageImpl() {
        images=MusicApplication.getImageInfo().getImages();
    }

    @Override
    public void findImageData(final int page, final IDataCallback callback) {
        String url= UrlFactory.getPicWithPage(page);
        final StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (page==1){
                    images=PaseJson(response);
                    MusicApplication.getImageInfo().setImages(images);
                    callback.findAllData(images);
                }else {
                    images.addAll(PaseJson(response));
                    MusicApplication.getImageInfo().setImages(images);
                    callback.findAllData(images);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MusicApplication.getQueue().add(request);
    }

    private List<Image> PaseJson(String response) {
        List<Image> imgs=new ArrayList<Image>();
        Gson gson=new Gson();
        QuestResultImage resultImage=gson.fromJson(response, QuestResultImage.class);
        imgs=resultImage.getResults();
        return imgs;
    }
}
