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
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.entity.QueryResult;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicModel;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class NetMusicModel implements INetMusicModel, Consts {
    private MusicPlayer musicPlayer;
    private List<Music> musics;

    public NetMusicModel() {
        musicPlayer = MusicApplication.getMusicPlayer();
        musics = new ArrayList<Music>();
    }

    @Override
    public void findAllNewMusic(INetMusicCallback callback) {
        String url = NEW_MUSIC_LIST;
        jsonPaser(NEW, url, callback);
    }

    @Override
    public void findAllHotMusic(INetMusicCallback callback) {
        String url = HOT_MUSIC_LIST;
        jsonPaser(HOT, url, callback);
    }

    @Override
    public void findAllBillboardMusic(INetMusicCallback callback) {
        String url = BILLBOARD_MUSIC_LIST;
        jsonPaser(BILLBOARD, url, callback);
    }

    @Override
    public void findAllKtvMusic(INetMusicCallback callback) {
        musics = musicPlayer.getKtvList();
        String url = KTV_MUSIC_LIST;
        jsonPaser(KTV, url, callback);
    }

    private void jsonPaser(final int type, String url, final INetMusicCallback callback) {
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i(TAG, "onResponse: "+response);
                Gson gson = new Gson();
                QueryResult result = gson.fromJson(response, QueryResult.class);
                musics = result.getSong_list();
                switch (type) {
                    case NEW:
                        musicPlayer.setNewList(musics);
                        break;
                    case HOT:
                        musicPlayer.setHotList(musics);
                        break;
                    case BILLBOARD:
                        musicPlayer.setBillboardList(musics);
                        break;
                    case KTV:
                        musicPlayer.setKtvList(musics);
                        break;
                }
                callback.findAllMusic(musics);
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