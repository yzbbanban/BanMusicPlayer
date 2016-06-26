package com.wangban.yzbbanban.banmusicplayer.model.impl;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.QueryResult;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;
import java.util.*;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class NetMusicModel implements INetMusicModel {


    @Override
    public void findAllMusic(INetMusicCallback callback) {
        String url = "";
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Music> music=new ArrayList<Music>();
                Gson gson=new Gson();
                QueryResult result=gson.fromJson(response,QueryResult.class);
                music=result.getSong_list();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }
}
