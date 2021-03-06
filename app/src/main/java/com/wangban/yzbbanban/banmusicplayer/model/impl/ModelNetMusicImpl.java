package com.wangban.yzbbanban.banmusicplayer.model.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.MusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.entity.QueryResult;
import com.wangban.yzbbanban.banmusicplayer.entity.QuestResultSearch;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;
import com.wangban.yzbbanban.banmusicplayer.model.IDataCallback;
import com.wangban.yzbbanban.banmusicplayer.model.IModelNetMusic;
import com.wangban.yzbbanban.banmusicplayer.util.UrlFactory;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/23.
 * 获取百度 Api 接口中的数据
 */
public class ModelNetMusicImpl implements IModelNetMusic, Consts {
    private MusicPlayer musicPlayer;
    private List<Music> musics;


    public ModelNetMusicImpl() {
        musicPlayer = MusicApplication.getMusicPlayer();
        musics = new ArrayList<Music>();
    }

    @Override
    public void findAllNewMusic(IDataCallback callback) {
        String url = UrlFactory.NEW_MUSIC_LIST;
        jsonPaser(NEW, url, callback);
    }

    @Override
    public void findAllHotMusic(IDataCallback callback) {
        String url = UrlFactory.HOT_MUSIC_LIST;
        jsonPaser(HOT, url, callback);
    }

    @Override
    public void findAllBillboardMusic(IDataCallback callback) {
        String url = UrlFactory.BILLBOARD_MUSIC_LIST;
        jsonPaser(BILLBOARD, url, callback);
    }

    @Override
    public void findAllKtvMusic(IDataCallback callback) {
        musics = musicPlayer.getKtvLists();
        String url = UrlFactory.KTV_MUSIC_LIST;
        jsonPaser(KTV, url, callback);
    }

    @Override
    public void findAllSearchMusic(String songName, final IDataCallback callback) {
        String url = UrlFactory.searchSongList(songName);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//              LogUtil.logInfo(TAG, "onResponse json数据: " + response);
                Gson gson = new Gson();
                QuestResultSearch resultSearch = gson.fromJson(response, QuestResultSearch.class);
                List<SongList> songLists = resultSearch.getSong_list();

                MusicApplication.getMusicPlayer().setSongLists(songLists);

//             LogUtil.logInfo(TAG, "songList: " + songLists.get(0).getTitle());
                callback.findAllData(songLists);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //LogUtil.logInfo(TAG, "onErrorResponse: 获取失败");
            }
        });
        MusicApplication.getQueue().add(request);
    }

    private void jsonPaser(final int type, String url, final IDataCallback callback) {
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i(TAG, "onResponse: "+response);
                Gson gson = new Gson();
                QueryResult result = gson.fromJson(response, QueryResult.class);
                musics = result.getSong_list();
                switch (type) {
                    case NEW:
                        musicPlayer.setNewLists(musics);
                        break;
                    case HOT:
                        musicPlayer.setHotLists(musics);
                        break;
                    case BILLBOARD:
                        musicPlayer.setBillboardLists(musics);
                        break;
                    case KTV:
                        musicPlayer.setKtvLists(musics);
                        break;
                }
                callback.findAllData(musics);
//                LogUtil.logInfo(TAG, "onResponse: " + musics.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //LogUtil.logInfo(TAG, "onErrorResponse: 获取失败");
            }
        });

        MusicApplication.getQueue().add(request);
    }


}
