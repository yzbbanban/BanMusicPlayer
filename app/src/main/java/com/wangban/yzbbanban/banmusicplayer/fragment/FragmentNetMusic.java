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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

public class FragmentNetMusic extends Fragment implements View.OnClickListener, Consts {
    @ViewInject(R.id.ibtn_new_list)
    private ImageButton ibtnNewList;
    @ViewInject(R.id.ibtn_hot_list)
    private ImageButton ibtnHotList;
    @ViewInject(R.id.ibtn_billboard_list)
    private ImageButton ibtnBillboardList;
    @ViewInject(R.id.ibtn_ktv_list)
    private ImageButton ibtnKtvList;
    @ViewInject(R.id.et_search)
    private EditText etSearch;
    //new
    @ViewInject(R.id.tv_new_list_first)
    private TextView tvNewFirst;
    @ViewInject(R.id.tv_new_list_second)
    private TextView tvNewSecond;
    @ViewInject(R.id.tv_new_list_third)
    private TextView tvNewThird;
    //hot
    @ViewInject(R.id.tv_hot_list_first)
    private TextView tvHotFirst;
    @ViewInject(R.id.tv_hot_list_second)
    private TextView tvHotSecond;
    @ViewInject(R.id.tv_hot_list_third)
    private TextView tvHotThird;
    //billboard
    @ViewInject(R.id.tv_billboard_list_first)
    private TextView tvBillFirst;
    @ViewInject(R.id.tv_billboard_list_second)
    private TextView tvBillSecond;
    @ViewInject(R.id.tv_billboard_list_third)
    private TextView tvBillThird;
    //ktv
    @ViewInject(R.id.tv_ktv_list_first)
    private TextView tvKtvFirst;
    @ViewInject(R.id.tv_ktv_list_second)
    private TextView tvKtvSecond;
    @ViewInject(R.id.tv_ktv_list_third)
    private TextView tvKtvThird;

    private Intent intent;
    private int type;


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
        ibtnBillboardList.setOnClickListener(this);
        ibtnKtvList.setOnClickListener(this);
    }

    private void setData() {



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ibtn_new_list:

                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = NEW;
//                Log.i(TAG, "onClick: new");
                break;
            case R.id.ibtn_hot_list:
                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = HOT;
//                Log.i(TAG, "onClick: hot");
                break;
            case R.id.ibtn_billboard_list:
                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = BILLBOARD;
//                Log.i(TAG, "onClick: hito");
                break;
            case R.id.ibtn_ktv_list:
                intent = new Intent(this.getActivity(), DetialMusicActivity.class);
                type = KTV;
//                Log.i(TAG, "onClick: ktv");
                break;
        }
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
