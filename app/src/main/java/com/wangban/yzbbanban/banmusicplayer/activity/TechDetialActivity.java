package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechDetialContent;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterTechNews;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterTechNewsImpl;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewTechNews;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class TechDetialActivity extends AppCompatActivity implements Consts,IViewTechNews,View.OnClickListener{
    @ViewInject(R.id.tv_tech_content_title)
    private TextView tvContentTitle;
    @ViewInject(R.id.btn_tech_content_back)
    private Button btnContentBack;
    @ViewInject(R.id.tv_tech_content_detail)
    private TextView tvContentDetial;
    @ViewInject(R.id.iv_tech_content_zoom)
    private ImageView ivContentZoom;

    private String detialPath;
    private IPresenterTechNews presenterTechNews;
    private TechNews techNews;
    public TechDetialActivity() {
        presenterTechNews=new PresenterTechNewsImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_detial);
        x.view().inject(this);
        setData();
        setListeners();

    }

    private void setListeners() {
        btnContentBack.setOnClickListener(this);
    }

    private void setData() {
        Intent intent=getIntent();
        techNews= (TechNews) intent.getSerializableExtra(TECHNEWS);
        detialPath=techNews.getDetialPath();
        presenterTechNews.loadNewsDetialMessageWithPage(detialPath);

//        LogUtil.logInfo(TAG,techNews.getTitle());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tech_content_back:
                finish();
                overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;
        }
    }

    @Override
    public void setTechNews(List<TechNews> techNews) {

    }

    @Override
    public void setTechNews(TechDetialContent content) {
        String imagePath=content.getImagePath();
//        LogUtil.logInfo(TAG,"image: "+imagePath);
        String text=content.getContent();
        tvContentTitle.setText(techNews.getTitle());
        Glide.with(this).load(imagePath).into(ivContentZoom);

        tvContentDetial.setText(text);

    }

    @Override
    public void showTechNews() {

    }
}
