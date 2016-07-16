package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.ui.PhotoView;

import java.util.*;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>implements View.OnClickListener {
    private List<Image> images;
    private ViewGroup parent;

    public ImageAdapter(List<Image> images) {
        this.images = images;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        Image image=images.get(position);
        holder.itemView.setTag(image);
        holder.textView.setText(image.getDesc());
        Glide.with(parent.getContext()).load(image.getUrl()).into(holder.photoView);

    }

    @Override
    public int getItemCount() {
        if (images != null) {
            return images.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onClick(View v) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onClick(v, (Image) v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private PhotoView photoView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            photoView = (PhotoView) itemView.findViewById(R.id.pv_grid);
            textView = (TextView) itemView.findViewById(R.id.tv_image_describe);


        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, Image doc);
    }

}
