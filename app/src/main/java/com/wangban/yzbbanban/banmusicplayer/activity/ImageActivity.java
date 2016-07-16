package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.ImageViewPageAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class ImageActivity extends AppCompatActivity implements Consts {
    @ViewInject(R.id.vp_images)
    private ViewPager viewPager;

    private ImageViewPageAdapter imagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        x.view().inject(this);
        setData();
        setListener();
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imagerAdapter.setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setData() {
        Intent intent=getIntent();
        int position=intent.getIntExtra(EXTRA_IMAGE_POSITION, 0);
        List<Image> images= MusicApplication.getImageInfo().getImages();
        imagerAdapter=new ImageViewPageAdapter(this,images);
        viewPager.setAdapter(imagerAdapter);
        viewPager.setCurrentItem(position);
    }


}
