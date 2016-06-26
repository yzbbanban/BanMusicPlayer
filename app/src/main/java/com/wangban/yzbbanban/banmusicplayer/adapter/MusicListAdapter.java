package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;

import java.util.ArrayList;

/**
 * Created by YZBbanban on 16/6/26.
 */
public class MusicListAdapter extends BaseAdapter<Music> {
    private ViewHolder holder;
    public MusicListAdapter(Context context, ArrayList<Music> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_music_list,null);
            holder=new ViewHolder();
            holder.tvNumber= (TextView) convertView.findViewById(R.id.tv_music_list_number);
            holder.tvName= (TextView) convertView.findViewById(R.id.tv_music_list_name);
            holder.tvArtist= (TextView) convertView.findViewById(R.id.tv_music_list_artist);
            convertView.setTag(holder);
        }
        Music music=getData().get(position);
        holder= (ViewHolder) convertView.getTag();
        holder.tvNumber.setText(""+(position+1));
        holder.tvName.setText(music.getTitle());
        holder.tvArtist.setText(music.getArtist_name());

        return convertView;
    }
    class ViewHolder{
        TextView tvNumber;
        TextView tvName;
        TextView tvArtist;
    }
}
