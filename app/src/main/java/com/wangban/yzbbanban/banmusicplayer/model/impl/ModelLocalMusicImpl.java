package com.wangban.yzbbanban.banmusicplayer.model.impl;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Song;
import com.wangban.yzbbanban.banmusicplayer.model.IModelLocalMusic;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/9.
 */
public class ModelLocalMusicImpl implements IModelLocalMusic ,Consts{
    private Context context;

    public ModelLocalMusicImpl() {
        context = MusicApplication.getContext();
    }

    @Override
    public List<Song> findAllLocalMusic() {
        List<Song> songs = new ArrayList<Song>();
        ContentResolver cr = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {"_id", // 0
                "_data", // 1
                "_size", // 2
                "title", // 3
                "duration", // 4
                "album_artist", // 5
                "album", // 6
                "artist" // 7
        };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor c = cr.query(uri, projection, selection, selectionArgs,
                sortOrder);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Song song = new Song();
            song.setId(c.getLong(0));
            song.setPath(c.getString(1));
            song.setSize(c.getInt(2));
            song.setTitle(c.getString(3));
            song.setDuration(c.getInt(4));
            song.setAlbumArtist(c.getString(5));
            song.setAlbum(c.getString(6));
            song.setArtist(c.getString(7));
            songs.add(song);
        }
        c.close();
        MusicApplication.getMusicPlayer().setLocalSongs(songs);
        LogUtil.logInfo(TAG,"Model_songs: "+songs.get(0).getTitle());
        return songs;
    }
}
