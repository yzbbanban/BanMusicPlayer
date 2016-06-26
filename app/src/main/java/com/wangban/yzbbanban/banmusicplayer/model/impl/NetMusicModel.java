package com.wangban.yzbbanban.banmusicplayer.model.impl;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.QueryResult;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;
import java.util.*;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class NetMusicModel implements INetMusicModel ,Consts{


    @Override
    public void findAllMusic(final INetMusicCallback callback) {
        String url =NEW_MUSIC_LIST;
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i(TAG, "onResponse: "+response);
                List<Music> music=new ArrayList<Music>();
                Gson gson=new Gson();
                QueryResult result=gson.fromJson(response,QueryResult.class);
                music=result.getSong_list();
                callback.findAllMusic(music);
                //Log.i(TAG, "onResponse: "+music.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        MusicApplication.getQueue().add(request);
    }
}
