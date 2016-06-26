package com.wangban.yzbbanban.banmusicplayer.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentBook;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentImage;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentMusicPlayer;
import com.wangban.yzbbanban.banmusicplayer.fragment.FragmentNetMusic;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.fl_main)
    private FrameLayout flContainer;
    @ViewInject(R.id.rg_player_bottom)
    private RadioGroup radioGroup;
    @ViewInject(R.id.rbtn_net_music)
    private RadioButton rbtnNetMusic;
    @ViewInject(R.id.rbtn_local_music)
    private RadioButton rbtnLocalMusic;
    @ViewInject(R.id.rbtn_image)
    private RadioButton rbtnImage;
    @ViewInject(R.id.rbtn_book)
    private RadioButton rbtnBook;
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
    private Object lastFragment;
    private int lastPosition;


    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_net_music:
                        selectFragment(0);
                        break;
                    case R.id.rbtn_local_music:
                        selectFragment(1);
                        break;
                    case R.id.rbtn_image:
                        selectFragment(2);
                        break;
                    case R.id.rbtn_book:
                        selectFragment(3);
                        break;

                }
            }
        });


    }
    private void selectFragment(int position) {
        //destort another fragment,falg set null
        if (lastFragment != null) {
            adapter.destroyItem(flContainer, lastPosition, lastFragment);
            lastFragment = null;
        }

        //set local frgment
        Object fragment = adapter.instantiateItem(flContainer, position);
        //
        adapter.setPrimaryItem(flContainer, 0, fragment);
        //
        adapter.finishUpdate(flContainer);
        //
        lastFragment = fragment;
        lastPosition = position;
    }

    private void setData() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentNetMusic());
        fragments.add(new FragmentMusicPlayer());
        fragments.add(new FragmentImage());
        fragments.add(new FragmentBook());

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        };

        selectFragment(1);


    }

}
