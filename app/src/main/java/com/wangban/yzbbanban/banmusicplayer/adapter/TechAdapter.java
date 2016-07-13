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
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/21.
 */
public class TechAdapter extends RecyclerView.Adapter<TechAdapter.ViewHolder> implements View.OnClickListener {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tech_list, parent, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TechNews news = newses.get(position);
        holder.itemView.setTag(news);
        Glide.with(v.getContext()).load(news.getImagePath()).into(holder.ivTechPic);
        holder.tvTechTitle.setText(news.getTitle());
        holder.tvTechDetialContent.setText(news.getDetialContent());
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    /**
     * 设置根据获取详细数据
     *
     * @param position
     * @return
     */
    public Object getItem(int position) {
        if (position < getItemCount()) {
            return getItems().get(position);
        }
        return null;
    }

    /**
     * 直接获取集合数据
     *
     * @return
     */
    public List<TechNews> getItems() {
        if (newses == null) {
            newses = new ArrayList<TechNews>();
        }
        return newses;
    }

    /**
     * 设置集合数据
     *
     * @param newses
     */
    public void setItems(List<TechNews> newses) {
        this.newses = newses;
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

    public static interface OnRecyclerViewItemClickListener {
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
