package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/21.
 */
public class TechAdapter extends RecyclerView.Adapter<TechAdapter.ViewHolder> implements View.OnClickListener {
    private List<TechNews> newses;
    private ViewGroup parent;

    public TechAdapter(List<TechNews> newses) {
        this.newses = newses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tech_list, parent, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TechNews news = newses.get(position);
        holder.itemView.setTag(news);
        Glide.with(parent.getContext()).load(news.getImagePath()).into(holder.ivTechPic);
        holder.tvTechTitle.setText(news.getTitle());
        holder.tvTechDetialContent.setText(news.getDetialContent());
    }

    @Override
    public int getItemCount() {
        if (newses != null) {
            return newses.size();
        }else {
            return 0;
        }
    }


    @Override
    public void onClick(View v) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onClick(v, (TechNews) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTechTitle;
        ImageView ivTechPic;
        TextView tvTechDetialContent;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvTechTitle = (TextView) itemView.findViewById(R.id.tv_tech_title);
            ivTechPic = (ImageView) itemView.findViewById(R.id.iv_tech_pic);
            tvTechDetialContent = (TextView) itemView.findViewById(R.id.tv_tech_detial_content);

        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, TechNews doc);
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
