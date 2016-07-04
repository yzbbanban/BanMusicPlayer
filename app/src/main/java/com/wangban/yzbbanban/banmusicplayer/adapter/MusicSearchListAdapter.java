package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.entity.SongList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZBbanban on 16/7/4.
 */
public class MusicSearchListAdapter extends BaseAdapter<SongList> {
    public MusicSearchListAdapter(Context context, ArrayList<SongList> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        SongList songList = (SongList) getItem(position);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.item_music_list, null);
            holder = new ViewHolder();
            holder.tvMusicListNumber = (TextView) convertView.findViewById(R.id.tv_music_list_number);
            holder.tvMusicListName = (TextView) convertView.findViewById(R.id.tv_music_list_name);
            holder.tvMusicListArtist = (TextView) convertView.findViewById(R.id.tv_music_list_artist);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.tvMusicListNumber.setText("" + (position + 1));
        holder.tvMusicListName.setText(songList.getTitle());
        holder.tvMusicListArtist.setText(songList.getAuthor());
        return convertView;
    }

    class ViewHolder {
        TextView tvMusicListNumber;
        TextView tvMusicListName;
        TextView tvMusicListArtist;


    }
}

