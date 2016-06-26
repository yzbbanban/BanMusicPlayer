package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangban.yzbbanban.banmusicplayer.R;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class FragmentMusicPlayer extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_player,null);
        return view;
    }
}
