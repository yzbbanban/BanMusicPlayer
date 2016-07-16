package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;

import com.bumptech.glide.Glide;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.ui.PhotoView;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class ImageViewPageAdapter extends PagerAdapter {
    private Context context;
    private List<Image> images;
    private LayoutInflater layoutInflater;
    private PhotoView photoView;

    public ImageViewPageAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Image image=images.get(position);
        View view = layoutInflater.inflate(R.layout.item_image_detial, container, false);
        photoView= (PhotoView) view.findViewById(R.id.pv_image);
        Glide.with(context).load(image.getUrl()).into(photoView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
