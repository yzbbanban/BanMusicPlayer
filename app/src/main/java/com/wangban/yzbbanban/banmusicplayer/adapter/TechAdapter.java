package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/21.
 */
public class TechAdapter extends RecyclerView.Adapter<TechAdapter.MasonryView> {
    private List<TechNews> newses;
    private ViewGroup v;

    public TechAdapter(Context context, List<TechNews> newses) {
        this.newses = newses;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        this.v = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tech_list, null);

        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        TechNews news = newses.get(position);
        Glide.with(v.getContext()).load(news.getImagePath()).into(holder.ivTechPic);
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public class MasonryView extends RecyclerView.ViewHolder {
        TextView tvTechTitle;
        ImageView ivTechPic;
        TextView tvTechDetialMessage;

        public MasonryView(View itemView) {
            super(itemView);
            tvTechTitle = (TextView) itemView.findViewById(R.id.tv_tech_title);
            ivTechPic = (ImageView) itemView.findViewById(R.id.iv_tech_pic);
            tvTechDetialMessage = (TextView) itemView.findViewById(R.id.tv_tech_detial_message);
        }
    }
}
