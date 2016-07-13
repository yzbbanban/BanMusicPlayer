package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.TechDetialActivity;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/21.
 */
public class TechAdapter extends RecyclerView.Adapter<TechAdapter.ViewHolder> {
    private List<TechNews> newses;
    private ViewGroup v;
    private Context context;

    public TechAdapter(Context context, List<TechNews> newses) {
        this.newses = newses;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.v = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tech_list, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TechNews news = newses.get(position);
        Glide.with(v.getContext()).load(news.getImagePath()).into(holder.ivTechPic);
        holder.tvTechTitle.setText(news.getTitle());
        holder.tvTechDetialContent.setText(news.getDetialContent());
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTechTitle;
        ImageView ivTechPic;
        TextView tvTechDetialContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTechTitle = (TextView) itemView.findViewById(R.id.tv_tech_title);
            ivTechPic = (ImageView) itemView.findViewById(R.id.iv_tech_pic);
            tvTechDetialContent = (TextView) itemView.findViewById(R.id.tv_tech_detial_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    LogUtil.logInfo("supergirl", "当前点击的位置：" + getLayoutPosition());
                    Intent intent = new Intent(context, TechDetialActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }
    /**
     * 设置头界面
     */
//    @Override
//    public void onViewAttachedToWindow(ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//        if (lp != null
//                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
//            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
//            p.setFullSpan(holder.getLayoutPosition() == 0);
//        }
//    }
}
