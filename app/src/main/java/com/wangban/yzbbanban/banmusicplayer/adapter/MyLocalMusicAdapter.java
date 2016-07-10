package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.Song;

import java.util.ArrayList;

/**
 * Created by YZBbanban on 16/7/9.
 */
public class MyLocalMusicAdapter extends BaseAdapter<Song> {

    public MyLocalMusicAdapter(Context context, ArrayList<Song> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getLayoutInflater().inflate(R.layout.item_my_local_music_list, null);
            holder.ivMyLocalIsPlay = (ImageView) convertView.findViewById(R.id.iv_my_local_is_play);
            holder.tvMyLocalMusicTitle = (TextView) convertView.findViewById(R.id.tv_my_local_music_title);
            holder.tvMyLocalMusicArtist = (TextView) convertView.findViewById(R.id.tv_my_local_music_artist);
            holder.tvMyLocalMusicAlbum = (TextView) convertView.findViewById(R.id.tv_my_local_music_Album);
           // holder.ibtnMyLocalMusicMore = (ImageButton) convertView.findViewById(R.id.ibtn_my_local_music_more);
            convertView.setTag(holder);
        }
        Song song = (Song) getItem(position);
        holder = (ViewHolder) convertView.getTag();
        holder.tvMyLocalMusicTitle.setText(song.getTitle());
        holder.tvMyLocalMusicArtist.setText(song.getArtist());
        holder.tvMyLocalMusicAlbum.setText(song.getAlbum());


        return convertView;
    }

    class ViewHolder {
        ImageView ivMyLocalIsPlay;
        TextView tvMyLocalMusicTitle;
        TextView tvMyLocalMusicArtist;
        TextView tvMyLocalMusicAlbum;
        //ImageButton ibtnMyLocalMusicMore;

    }
}
