package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.DetialNewMusicActivity;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Music;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterNet;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterNetImpl;
import com.wangban.yzbbanban.banmusicplayer.view.IViewNet;

/**
 * Created by YZBbanban on 16/6/23.
 */
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class FragmentNetMusic extends Fragment implements IViewNet, View.OnClickListener, Consts {
    @ViewInject(R.id.ll_new_list)
    private LinearLayout llNewList;
    @ViewInject(R.id.ll_hot_list)
    private LinearLayout llHotList;
    @ViewInject(R.id.ll_billboard_list)
    private LinearLayout llBillboardList;
    @ViewInject(R.id.ll_ktv_list)
    private LinearLayout llKtvList;
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
    private List<Music> newList;
    private List<Music> hotList;
    private List<Music> billboardList;
    private List<Music> ktvList;

    private IPresenterNet iPresenterNet;

    public FragmentNetMusic() {
        iPresenterNet = new PresenterNetImpl(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i(TAG, "onResume: fragmentNetMusic");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net, null);
        //Log.i(TAG, "onCreateView: fragmentNetMusic");
        x.view().inject(this, view);
        setData();
        setListeners();

        return view;
    }


    private void setListeners() {
        llNewList.setOnClickListener(this);
        llHotList.setOnClickListener(this);
        llBillboardList.setOnClickListener(this);
        llKtvList.setOnClickListener(this);
    }

    private void setData() {
        iPresenterNet.loadNewMusics();
        iPresenterNet.loadHotMusics();
        iPresenterNet.loadBillboardMusics();
        iPresenterNet.loadKTVMusics();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_new_list:

                intent = new Intent(this.getActivity(), DetialNewMusicActivity.class);
                type = NEW;
//                Log.i(TAG, "onClick: new");
                break;
            case R.id.ll_hot_list:
                intent = new Intent(this.getActivity(), DetialNewMusicActivity.class);
                type = HOT;
//                Log.i(TAG, "onClick: hot");
                break;
            case R.id.ll_billboard_list:
                intent = new Intent(this.getActivity(), DetialNewMusicActivity.class);
                type = BILLBOARD;
//                Log.i(TAG, "onClick: billboard");
                break;
            case R.id.ll_ktv_list:
                intent = new Intent(this.getActivity(), DetialNewMusicActivity.class);
                type = KTV;
//                Log.i(TAG, "onClick: ktv");
                break;
        }
        intent.putExtra("type", type);
        Log.i(TAG, "Fragment onClick: "+type);
        startActivity(intent);
    }

    @Override
    public void setNewText(List<Music> musics) {
        tvNewFirst.setText(musics.get(0).getTitle());
        tvNewSecond.setText(musics.get(1).getTitle());
        tvNewThird.setText(musics.get(2).getTitle());
    }

    @Override
    public void setHotText(List<Music> musics) {
        tvHotFirst.setText(musics.get(0).getTitle());
        tvHotSecond.setText(musics.get(1).getTitle());
        tvHotThird.setText(musics.get(2).getTitle());
    }

    @Override
    public void setBillText(List<Music> musics) {
        tvBillFirst.setText(musics.get(0).getTitle());
        tvBillSecond.setText(musics.get(1).getTitle());
        tvBillThird.setText(musics.get(2).getTitle());
    }

    @Override
    public void setKtvText(List<Music> musics) {
        tvKtvFirst.setText(musics.get(0).getTitle());
        tvKtvSecond.setText(musics.get(1).getTitle());
        tvKtvThird.setText(musics.get(2).getTitle());
    }
}
