package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;

import java.util.ArrayList;

/**
 * Created by YZBbanban on 16/7/7.
 */
public class LocalMusicListAdapter extends BaseAdapter<Object> {
    public LocalMusicListAdapter(Context context, Object data) {
        super(context, (ArrayList<Object>) data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.item_local_music_list, null);
            holder = new ViewHolder();
            holder.tvLocalMusicListName = (TextView) convertView.findViewById(R.id.tv_local_music_list_name);
            holder.tvLocalMusicListArtist = (TextView) convertView.findViewById(R.id.tv_local_music_list_artist);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        if (MusicApplication.getMusicPlayer().getMusicListType() == Consts.SEARCH) {
            SongList songList = (SongList) getItem(position);
            holder.tvLocalMusicListName.setText(songList.getTitle());
            holder.tvLocalMusicListArtist.setText(songList.getAuthor());
        } else {
            Music music = (Music) getItem(position);
            holder.tvLocalMusicListName.setText(music.getTitle());
            holder.tvLocalMusicListArtist.setText(music.getAuthor());
        }


        return convertView;
    }

    class ViewHolder {
        TextView tvLocalMusicListName;
        TextView tvLocalMusicListArtist;
    }
}
