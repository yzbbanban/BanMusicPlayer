package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentTech;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentImage;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentMusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentNetMusic;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.vp_main)
    private ViewPager viewPager;
    @ViewInject(R.id.rg_player_bottom)
    private RadioGroup radioGroup;
    @ViewInject(R.id.rbtn_net_music)
    private RadioButton rbtnNetMusic;
    @ViewInject(R.id.rbtn_local_music)
    private RadioButton rbtnLocalMusic;
    @ViewInject(R.id.rbtn_image)
    private RadioButton rbtnImage;
    @ViewInject(R.id.rbtn_book)
    private RadioButton rbtnTech;
    private List<Fragment> fragments;

    private FragmentPagerAdapter adapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        setData();

        setListener();
    }


    @Override
    protected void onResume() {
        Intent intent = getIntent();
        if (intent != null) {
            int type = intent.getIntExtra("Fragment", 0);
            if (type == -1) {
                viewPager.setCurrentItem(0);
            } else if (type == -2) {
                viewPager.setCurrentItem(1);
            }

        }
        super.onResume();
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_net_music:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rbtn_local_music:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rbtn_image:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rbtn_book:
                        viewPager.setCurrentItem(3);
                        break;

                }
            }
        });


    }

    private void setData() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentNetMusic());
        fragments.add(new FragmentMusicPlayer());
        fragments.add(new FragmentImage());
        fragments.add(new FragmentTech());

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbtnNetMusic.setChecked(true);
                        break;
                    case 1:
                        rbtnLocalMusic.setChecked(true);
                        break;
                    case 2:
                        rbtnImage.setChecked(true);
                        break;
                    case 3:
                        rbtnTech.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //is back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("警告");
            builder.setMessage("您确定要退出吗");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MusicApplication app= (MusicApplication) getApplication();
                    app.finishActivity();
                }
            });
            builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            builder.setNeutralButton("主界面", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                }
            });
            //set other layout cannot use
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
