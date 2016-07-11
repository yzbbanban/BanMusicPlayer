package com.wangban.yzbbanban.banmusicplayer.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.TechAdapter;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterTechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterTechNewsImpl;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewTechNews;

import org.xutils.x;

import java.util.List;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class FragmentTech extends Fragment implements IViewTechNews, Consts {
    private View view;
    private RecyclerView recyclerView;
    private List<TechNews> techNewses;
    private TechAdapter adapter;
    private IPresenterTechNews presenterTechNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tech, null);
        x.view().inject(this, view);
        setView();
        return view;
    }

    private void setView() {
        presenterTechNews = new PresenterTechNewsImpl(this);
        presenterTechNews.loadNewsMessage();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_tech_message);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    public void setTechNews(List<TechNews> techNewses) {
        this.techNewses = techNewses;
//        LogUtil.logInfo(TAG,"setTechNews: "+techNewses.get(0).getImagePath());
    }

    @Override
    public void showTechNews() {
        adapter = new TechAdapter(techNewses);
        recyclerView.setAdapter(adapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
