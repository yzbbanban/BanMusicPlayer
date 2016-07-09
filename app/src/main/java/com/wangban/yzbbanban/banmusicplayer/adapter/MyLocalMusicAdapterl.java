package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.Song;

import java.util.ArrayList;

/**
 * Created by YZBbanban on 16/7/9.
 */
public class MyLocalMusicAdapterl extends BaseAdapter<Song> {

    public MyLocalMusicAdapterl(Context context, ArrayList<Song> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=getLayoutInflater().inflate(R.layout.item_local_music_list,null);
            

        }


        return convertView;
    }

    class ViewHolder {
        TextView iv_my_local_is_play;
        TextView tv_my_local_music_title;
        TextView tv_my_local_music_artist;
        TextView tv_my_local_music_Album;
        TextView tv_my_local_music_duration_list;

    }
}
