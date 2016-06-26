package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.DetialMusicActivity;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;

/**
 * Created by YZBbanban on 16/6/23.
 */
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

public class FragmentNetMusic extends Fragment implements  View.OnClickListener, Consts {
    @ViewInject(R.id.ibtn_new_list)
    private ImageButton ibtnNewList;
    @ViewInject(R.id.ibtn_hot_list)
    private ImageButton ibtnHotList;
    @ViewInject(R.id.ibtn_hito_list)
    private ImageButton ibtnHitoList;
    @ViewInject(R.id.ibtn_ktv_list)
    private ImageButton ibtnKtvList;

    public FragmentNetMusic() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: fragmentNetMusic");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net, null);
        Log.i(TAG, "onCreateView: fragmentNetMusic");
        x.view().inject(this, view);
        setData();
        setListeners();
        return view;
    }

    private void setListeners() {
        ibtnNewList.setOnClickListener(this);
        ibtnHotList.setOnClickListener(this);
        ibtnHitoList.setOnClickListener(this);
        ibtnKtvList.setOnClickListener(this);
    }

    private void setData() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_new_list:
                startActivity(new Intent(this.getActivity(), DetialMusicActivity.class));
                Log.i(TAG, "onClick: new");
                break;
            case R.id.ibtn_hot_list:
                Log.i(TAG, "onClick: hot");
                break;
            case R.id.ibtn_hito_list:
                Log.i(TAG, "onClick: hito");
                break;
            case R.id.ibtn_ktv_list:
                Log.i(TAG, "onClick: ktv");
                break;

        }
    }
}
