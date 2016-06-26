package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangban.yzbbanban.banmusicplayer.R;

/**
 * Created by YZBbanban on 16/6/25.
 */
public class FragmentNetMusicList extends Fragment{
    private static final String TAG="supergirl";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_net_top_list,null);
        Log.i(TAG, "onCreateView: net_top_show");
        return view;
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView: net_top_destoryview");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: net_top_destory");
        super.onDestroy();
    }
}
