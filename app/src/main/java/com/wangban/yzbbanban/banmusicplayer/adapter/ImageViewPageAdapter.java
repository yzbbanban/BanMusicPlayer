package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.LayerDrawable;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.*;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.ImageActivity;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.ui.PhotoView;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class ImageViewPageAdapter extends PagerAdapter implements View.OnClickListener,View.OnLongClickListener{
    private Context context;
    private List<Image> images;
    private LayoutInflater layoutInflater;
    private PhotoView view;
    private int position;
    private List<Bitmap> bitmaps;

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
        view = new PhotoView(context);
        view.setBackgroundResource(R.color.colorOrage);
        view.setOnLongClickListener(this);
        view.setOnClickListener(this);
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setBitmap(view,image.getUrl());
        container.addView(view);
        return view;
    }

    private void setBitmap(final PhotoView photoView,String imageUrl) {

        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().loadImage(imageUrl, options, new SimpleImageLoadingListener(){

            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                bitmaps.add(loadedImage);
                LogUtil.logInfo("supergirl","bitmap: "+bitmaps.get(0).toString());
                photoView.setImageBitmap(loadedImage);
            }

        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setPosition(int position){
        this.position=position;
    }
    public int getPosition(){
        return position;
    }

    @Override
    public void onClick(View v) {
        ((ImageActivity)context).finish();
        ((ImageActivity) context).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

    }

    @Override
    public boolean onLongClick(View v) {
        ToastUtil.showToast(context,""+getPosition());
        return false;
    }




    /**
     * system suggest method
     *
     * @throws IOException
     */
    private void saveImage(Bitmap bitmap) throws IOException {


        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "title", "description");

    }

    private void setWallPaper(Bitmap b) throws IOException {
        WallpaperManager wpManager = WallpaperManager.getInstance(context);
        wpManager.setBitmap(b);
    }


}
