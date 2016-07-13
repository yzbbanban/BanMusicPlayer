package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.TechAdapter;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterTechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterTechNewsImpl;
import com.wangban.yzbbanban.banmusicplayer.ui.UpRefreshRecyclerView;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewTechNews;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/23.
 * 显示 tech 列表
 */
public class FragmentTech extends Fragment implements IViewTechNews, UpRefreshRecyclerView.UpRefreshListener, Consts, SwipeRefreshLayout.OnRefreshListener {
    private View view;
    @ViewInject(R.id.rv_tech_message)
    private UpRefreshRecyclerView recyclerView;
    @ViewInject(R.id.swipe_tech)
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<TechNews> techNewses;
    private TechAdapter adapter;
    private IPresenterTechNews presenterTechNews;

    private int page;
    /**
     * 数据更新时，重新发送请求
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    presenterTechNews.loadNewsMessage();
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tech, null);
        x.view().inject(this, view);
        setView();
        setListener();
        return view;
    }

    /**
     * 数据显示后停止刷新图标的动画
     */
    private void stopRefThe() {
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 设置刷新的监听器，设置颜色
     */
    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setUpRefreshListener(this);
    }

    private void setView() {
        presenterTechNews = new PresenterTechNewsImpl(this);
        presenterTechNews.loadNewsMessage();
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_red_light);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }


    /**
     * 设置列表项数据
     *
     * @param techNewses
     */
    @Override
    public void setTechNews(List<TechNews> techNewses) {
        this.techNewses = techNewses;
//        LogUtil.logInfo(TAG,"setTechNews: "+techNewses.get(0).getImagePath());
    }

    /**
     * 显示列表项
     */
    @Override
    public void showTechNews() {
        if (page <= 1) {
            adapter = new TechAdapter(getContext(), techNewses);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(0);
        stopRefThe();

//        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
//        recyclerView.addItemDecoration(decoration);
    }

    /**
     * 下拉刷新数据
     */
    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

    }

    @Override
    public void onUpRefresh() {
        page++;
        LogUtil.logInfo(TAG,"page: "+page);
        swipeRefreshLayout.setRefreshing(true);
        presenterTechNews.loadNewsMessageWithPage(page);

    }

    /**
     * 设置间距，下拉刷新会重置数据并且叠加，所以不用了。
     */
//    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
//        private int space;
//
//        public SpacesItemDecoration(int space) {
//            this.space = space;
//        }
//
//        //设置每个控件之间的距离
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            outRect.left = space;
//            outRect.right = space;
//            outRect.bottom = space;
//            if (parent.getChildAdapterPosition(view) == 0) {
//                outRect.top = space;
//            }
//        }
//    }
}
