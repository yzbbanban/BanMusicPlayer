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

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.TechDetialActivity;
import com.wangban.yzbbanban.banmusicplayer.adapter.TechAdapter;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechDetialContent;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterTechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterTechNewsImpl;
import com.wangban.yzbbanban.banmusicplayer.ui.UpRefreshRecyclerView;
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
    private int refrashState = 1;
    /**
     * 数据更新时，重新发送请求
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    page = 0;
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
        setData();
        setListener();
        return view;
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

    /**
     * 设置刷新的监听器，设置颜色
     */
    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setUpRefreshListener(this);

    }

    private void setData() {
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
     * 无用处
     * @param content
     */
    @Override
    public void setTechNews(TechDetialContent content) {

    }

    /**
     * 显示列表项
     */
    @Override
    public void showTechNews() {
        //判断是否为第一页
        if (page <= 1) {
            adapter = new TechAdapter(techNewses);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }

        adapter.notifyDataSetChanged();
        if (refrashState == 1) {
            recyclerView.smoothScrollToPosition(0);
        }
        stopRefThe();
        adapter.setOnItemClickListener(new TechAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, TechNews doc) {

                Intent intent = new Intent(getContext(), TechDetialActivity.class);
                intent.putExtra(TECHNEWS, doc);
                startActivity(intent);

            }
        });

//        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
//        recyclerView.addItemDecoration(decoration);
    }

    /**
     * 下拉刷新数据
     */
    @Override
    public void onRefresh() {
        //状态位若为一，则更新后显示首位
        refrashState = 1;
        page = 0;
        handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

    }

    /**
     * 上拉加载数据
     */
    @Override
    public void onUpRefresh() {
        //状态位若为一，则更新后显示加载位置
        refrashState = 0;
//        LogUtil.logInfo(TAG, "page: " + page);
        swipeRefreshLayout.setRefreshing(true);
        page++;
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
