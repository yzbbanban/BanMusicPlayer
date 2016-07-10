package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.LocalMusicActivity;
import com.wangban.yzbbanban.banmusicplayer.activity.PlayActivity;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Song;
import com.wangban.yzbbanban.banmusicplayer.presenter.IpresenterLocalMusic;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterLocalMusic;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewLocalMusic;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class FragmentMusicPlayer extends Fragment implements Consts, IViewLocalMusic, View.OnClickListener {
    @ViewInject(R.id.tv_my_download_music_count)
    private TextView tvDownloadCount;
    @ViewInject(R.id.tv_my_local_music_more)
    private TextView tvLocalMusicMore;
    @ViewInject(R.id.tv_my_local_music_count)
    private TextView tvLocalCount;
    @ViewInject(R.id.ibtn_my_local_music)
    private ImageButton ibtnLocalMusic;
    @ViewInject(R.id.ll_my_local_music)
    private LinearLayout llLocalMusic;
    @ViewInject(R.id.ll_my_download_music)
    private LinearLayout llDownloadMusic;

    private Intent intent;
    private View view;
    private List<Song> songs;
    private IpresenterLocalMusic presenterLocalMusic;
    private PopupMenu musicListMenu;

    public FragmentMusicPlayer() {
        presenterLocalMusic = new PresenterLocalMusic(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player, null);
        x.view().inject(this, view);
        presenterLocalMusic.loadLocalMusic();
        setView();
        setListeners();
        return view;
    }


    private void setView() {

    }

    private void setListeners() {
        tvLocalMusicMore.setOnClickListener(this);
        ibtnLocalMusic.setOnClickListener(this);
        llDownloadMusic.setOnClickListener(this);
        llLocalMusic.setOnClickListener(this);
    }

    @Override
    public void setData(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public void showData() {
//        tvDownloadCount.setText(""+songs.size() + 1);
        tvLocalCount.setText("" + (songs.size() + 1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_my_local_music_more:
                showMenuList(view);
                break;
            case R.id.ibtn_my_local_music:

                intent = new Intent(getActivity(), PlayActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

                break;
            case R.id.ll_my_download_music:

                break;
            case R.id.ll_my_local_music:
                MusicApplication.getMusicPlayer().setMusicListType(LOCAL);
                intent = new Intent(getActivity(), LocalMusicActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);

                break;
        }
    }

    private void showMenuList(View view) {
        musicListMenu = new PopupMenu(getContext(), view);
        musicListMenu.getMenuInflater().inflate(R.menu.main, musicListMenu.getMenu());
        //code can also finish mission
        //SubMenu subMenu = musicListMenu.getMenu().addSubMenu("新建列表").setIcon(R.drawable.new_music_list);


        musicListMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
              


                return false;
            }
        });
        musicListMenu.show();

    }

}
