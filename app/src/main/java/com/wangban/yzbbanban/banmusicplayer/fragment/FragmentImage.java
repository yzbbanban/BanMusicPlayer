package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.ImageActivity;
import com.wangban.yzbbanban.banmusicplayer.activity.TechDetialActivity;
import com.wangban.yzbbanban.banmusicplayer.adapter.ImageAdapter;
import com.wangban.yzbbanban.banmusicplayer.adapter.TechAdapter;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.Image;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPreseterImage;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterImageImpl;
import com.wangban.yzbbanban.banmusicplayer.ui.UpRefreshRecyclerView;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewImage;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class FragmentImage extends Fragment implements IViewImage, Consts, UpRefreshRecyclerView.UpRefreshListener, SwipeRefreshLayout.OnRefreshListener {
    private View view;
    @ViewInject(R.id.srl_beauty)
    private SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.rrv_image)
    private UpRefreshRecyclerView recyclerView;
    private ImageAdapter adapter;

    private List<Image> images;
    private IPreseterImage preseterImage;
    private int page = 1;
    private int refrashState;

    public FragmentImage() {
        preseterImage = new PresenterImageImpl(this);
    }

    /**
     * 数据更新时，重新发送请求
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    page = 1;
                    preseterImage.loadImageData(page);
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image, null);
        x.view().inject(this, view);
        setDate();
        setListeners();
        return view;
    }

    private void setListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setUpRefreshListener(this);
    }

    private void setDate() {
        preseterImage.loadImageData(page);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_red_light);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    /**
     * 数据显示后停止刷新图标的动画
     */
    private void stopRefThe() {
        // 必须关掉刷新，不然不更新数据
        recyclerView.onRefreshFinish();
        //关掉刷新的动画
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setImageData(List<Image> images) {
        this.images = images;
    }

    @Override
    public void showImageData() {
        //判断是否为第一页
        if (page == 1) {
            adapter = new ImageAdapter(images);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        adapter.notifyDataSetChanged();
        if (refrashState == 1) {
            recyclerView.smoothScrollToPosition(0);
        }
        stopRefThe();
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
//                ToastUtil.showToast(getContext(),""+position);
                Intent intent = new Intent(FragmentImage.this.getContext(),ImageActivity.class);
                intent.putExtra(EXTRA_IMAGE_POSITION, position);
                startActivity(intent);
            }
        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        //状态位若为一，则更新后显示首位
        refrashState = 1;
        page = 1;
        handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

    }

    /**
     * 上拉加载
     */
    @Override
    public void onUpRefresh() {
        //状态位若为一，则更新后显示加载位置
        page++;
        refrashState = 0;
//        LogUtil.logInfo(TAG, "page: " + page);
        swipeRefreshLayout.setRefreshing(true);
        preseterImage.loadImageData(page);
    }
}
