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
import com.wangban.yzbbanban.banmusicplayer.entity.QuestResultDetial;
import com.wangban.yzbbanban.banmusicplayer.entity.SongInfo;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;
import com.wangban.yzbbanban.banmusicplayer.entity.Url;
import com.wangban.yzbbanban.banmusicplayer.model.INetDetialModel;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.util.UrlFactory;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/26.
 * 获取歌曲数据信息
 */
public class NetDetialModelImpl implements INetDetialModel, Consts {
    private MusicPlayer musicPlayer;
    private List<Music> musics;
    private List<SongInfo> songInfos;

    public NetDetialModelImpl() {
        musicPlayer = MusicApplication.getMusicPlayer();
        musics = new ArrayList<Music>();
    }

    @Override
    public void getNextPosition() {

    }

    @Override
    public void getPreviousPosition() {

    }



    @Override
    public void setSong(String songId, final INetMusicCallback callback) {
        final String url = UrlFactory.songUrl(songId);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Log.i(TAG, "onResponse: "+response);
                    Gson gson = new Gson();
                    QuestResultDetial resultDetial = gson.fromJson(response, QuestResultDetial.class);
                    List<Url> urls=  resultDetial.getSongurl().getUrl();

                    SongInfo songInfo=resultDetial.getSonginfo();
                    if (urls==null){
                    }
                    Log.i(TAG, "onResponse: " + urls.get(0).getFile_link());
                    callback.findAllMusic(urls,songInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MusicApplication.getQueue().add(request);

    }

    /**
     * 从 Application 中创建的 musics 中取出
     *
     * @return
     */
    @Override
    public Object findAllNewMusic() {
        musics = musicPlayer.getNewLists();
        return musics;
    }

    @Override
    public Object findAllHotMusic() {
        musics = musicPlayer.getHotLists();
        return musics;
    }

    @Override
    public Object findAllBillMusic() {
        musics = musicPlayer.getBillboardLists();
        return musics;
    }

    @Override
    public Object findAllKtvMusic() {
        musics = musicPlayer.getKtvLists();
        return musics;
    }

//    @Override
//    public Object findAllSearchMusic() {
//        List<SongList> songLists=musicPlayer.getSongLists();
//        return songLists;
//    }


}
