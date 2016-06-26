package com.wangban.yzbbanban.banmusicplayer.model.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.entity.QuestResultDetial;
import com.wangban.yzbbanban.banmusicplayer.entity.SongInfo;
import com.wangban.yzbbanban.banmusicplayer.entity.SongUrl;
import com.wangban.yzbbanban.banmusicplayer.model.INetDetialModel;
import com.wangban.yzbbanban.banmusicplayer.model.INetMusicCallback;
import com.wangban.yzbbanban.banmusicplayer.util.UrlFactory;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class NetDetialModelImpl implements INetDetialModel {
    private MusicPlayer musicPlayer;
    private List<Music> musics;
    private List<SongInfo> songInfos;
    private List<SongUrl> songUrls;

    public NetDetialModelImpl() {
        musicPlayer = MusicApplication.getMusicPlayer();
        musics=new ArrayList<Music>();
    }

    @Override
    public void getNextPosition() {

    }

    @Override
    public void getPreviousPosition() {

    }

    @Override
    public void setSongUrl(String songId) {

    }

    /**
     * 从 Application 中创建的 musics 中取出
     * @return
     */
    @Override
    public Object findAllNewMusic() {
        musics=musicPlayer.getNewList();
        return musics;
    }

    @Override
    public Object findAllHotMusic() {
        musics=musicPlayer.getHotList();
        return musics;
    }

    @Override
    public Object findAllBillMusic() {
        musics=musicPlayer.getBillboardList();
        return musics;
    }

    @Override
    public Object findAllKtvMusic() {
        musics=musicPlayer.getKtvList();
        return musics;
    }

    private void jsonPaserUrl(String songId,final INetMusicCallback callback){
        String url= UrlFactory.songUrl(songId);
        StringRequest request=new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                QuestResultDetial resultDetial=gson.fromJson(response, QuestResultDetial.class);
                songUrls=resultDetial.getSongurl();
                songInfos=resultDetial.getSonginfo();

                callback.findAllMusic(songUrls);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
