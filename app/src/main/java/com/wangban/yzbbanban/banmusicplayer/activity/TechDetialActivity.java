package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.TechNews;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class TechDetialActivity extends AppCompatActivity implements Consts,View.OnClickListener{
    @ViewInject(R.id.iv_tech_content_title)
    private ImageView ivCOntentTitle;
    @ViewInject(R.id.btn_tech_content_back)
    private Button btnContentBack;
    @ViewInject(R.id.tv_tech_content_detail)
    private TextView tvContentDetial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_detial);
        x.view().inject(this);
        setData();

    }

    private void setData() {
        Intent intent=getIntent();
        TechNews techNews= (TechNews) intent.getSerializableExtra(TECHNEWS);
        LogUtil.logInfo(TAG,techNews.getTitle());
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
}
