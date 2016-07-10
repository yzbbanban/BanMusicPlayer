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
public class ModelLocalMusicImpl implements IModelLocalMusic, Consts {
    private Context context;

    public ModelLocalMusicImpl() {
        context = MusicApplication.getContext();
    }

    @Override
    public List<Song> findAllLocalMusic() {
        List<Song> songs = new ArrayList<Song>();
        // 1. 准备ContentResolver
        ContentResolver cr = context.getContentResolver();

        // 2. 准备Uri
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        // 3. 执行查询，获取Cursor
        String[] projection = {"_id", // 0
                "_data", // 1
                "_size", // 2
                "title", // 3
                "duration", // 4
                "album_artist", // 5
                "album", // 6
                "artist", // 7
                "album_key" // 8
        };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor c = cr.query(uri, projection, selection, selectionArgs,
                sortOrder);

        // 4. 遍历Cursor，得到List集合
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
            song.setAlbumKey(c.getString(8));
            song.setAlbumArt(getAlbumArtByKey(song.getAlbumKey()));
            songs.add(song);

        }

        // 5. 释放资源
        c.close();
        MusicApplication.getMusicPlayer().setLocalSongs(songs);
//        LogUtil.logInfo(TAG,"Model_songs: "+songs.get(0).getTitle());
        return songs;
    }

    /**
     * 根据album_key查找对应的album_art
     *
     * @param albumKey
     * @return album_key对应的album_art，如果没有对应的值，则返回null
     */
    private String getAlbumArtByKey(String albumKey) {
        // 检查参数
        if (albumKey == null) {
            return null;
        }

        // 1. 准备返回值
        String albumArt = null;

        // 2. 准备ContentResolver
        ContentResolver cr = context.getContentResolver();

        // 3. 准备Uri
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

        // 4. 执行查询，获取Cursor
        String[] projection = {"album_art"};
        String selection = "album_key=?";
        String[] selectionArgs = {albumKey};
        String sortOrder = null;
        Cursor c = cr.query(uri, projection, selection, selectionArgs, sortOrder);

        // 5. 分析Cursor，获取数据
        if (c.moveToFirst()) {
            albumArt = c.getString(c.getColumnIndex("album_art"));
        }

        // 6. 释放资源
        c.close();

        // 7. 返回
        return albumArt;
    }
}
