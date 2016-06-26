package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wangban.yzbbanban.banmusicplayer.R;

/**
 * Created by YZBbanban on 16/6/23.
 */
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

public class FragmentNetMusic extends Fragment {
    @ViewInject(R.id.rg_list_select)
    private RadioGroup radioGroupList;
    @ViewInject(R.id.net_viewPager)
    private ViewPager netViewPager;
    @ViewInject(R.id.rbtn_music_list)
    private RadioButton rbtnMusicList;
    @ViewInject(R.id.rbtn_radio_list)
    private RadioButton rbtnRadioList;
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net, null);
        x.view().inject(this, view);
        setData();
        setListeners();
        return view;
    }

    private void setListeners() {
        radioGroupList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {
                    case R.id.rbtn_music_list:
                        netViewPager.setCurrentItem(0);
                        break;
                    case R.id.rbtn_radio_list:
                        netViewPager.setCurrentItem(1);
                        break;
                }
            }
        });

        netViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton)radioGroupList.getChildAt(position)).setChecked(true);
//                switch (position) {
//                    case 0:
//                        rbtnMusicList.setChecked(true);
//                        break;
//                    case 1:
//                        rbtnRadioList.setChecked(true);
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setData() {

        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentNetMusicList());
        fragments.add(new FragmentNetRadioList());
        adapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        netViewPager.setAdapter(adapter);
        //netViewPager.setOffscreenPageLimit(3);
    }

}
