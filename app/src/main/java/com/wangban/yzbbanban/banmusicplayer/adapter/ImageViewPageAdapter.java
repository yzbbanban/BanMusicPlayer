package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.LayerDrawable;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.*;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.ImageActivity;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.ui.PhotoView;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;

/**
 * Created by YZBbanban on 16/7/16.
 */
public class ImageViewPageAdapter extends PagerAdapter implements Consts, View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<Image> images;
    private LayoutInflater layoutInflater;
    private PhotoView view;
    private int position;

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
        Image image = images.get(position);
        view = new PhotoView(context);
        view.setBackgroundResource(R.color.colorOrage);
        view.setOnLongClickListener(this);
        view.setOnClickListener(this);
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setBitmap(SET_BITMAP, view, image.getUrl());
        container.addView(view);
        return view;
    }

    private void setBitmap(final int type, final PhotoView photoView, String imageUrl) {

        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().loadImage(imageUrl, options, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                switch (type) {
                    case SET_BITMAP:
                        if (photoView != null) {
                            photoView.setImageBitmap(loadedImage);
                        }
                        break;
                    case SET_WALLPAPER:
                        try {
                            setWallPaper(loadedImage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case SAVE_BITMAP:
                        try {
                            saveImage(loadedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void onClick(View v) {
        ((ImageActivity) context).finish();
        ((ImageActivity) context).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

    }

    @Override
    public boolean onLongClick(View v) {
//        ToastUtil.showToast(context, "" + getPosition());
//        int position=getPosition();
        dialogMenu(getPosition());
        return false;
    }


    /**
     * system suggest method
     *
     * @throws IOException
     */
    private void saveImage(Bitmap bitmap) throws IOException {


        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "title", "description");
        ToastUtil.showToast(context, "保存成功");
    }

    private void setWallPaper(Bitmap b) throws IOException {
        WallpaperManager wpManager = WallpaperManager.getInstance(context);
        wpManager.setBitmap(b);
        ToastUtil.showToast(context, "壁纸设置成功");

    }


    private AlertDialog.Builder builder;

    private void dialogMenu(final int position) {
        builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.icon_menu2).setTitle("图片设置").setMessage("您要保存吗？");
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setBitmap(SAVE_BITMAP, null, images.get(position).getUrl());

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton("设为壁纸", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setBitmap(SET_WALLPAPER, null, images.get(position).getUrl());
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
