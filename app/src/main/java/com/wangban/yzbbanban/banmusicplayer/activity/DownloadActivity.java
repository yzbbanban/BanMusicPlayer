package com.wangban.yzbbanban.banmusicplayer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.view.IViewDownLoad;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class DownloadActivity extends AppCompatActivity implements IViewDownLoad {
    @ViewInject(R.id.rv_download_list)
    private RecyclerView recyclerView;
    @ViewInject(R.id.btn_download_back)
    private Button btnDownloadBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        x.view().inject(this);
        setData();
    }

    private void setData() {

    }

    //设置进度
    @Override
    public void setProgressMessage(int progress) {

    }
    //设置失败信息
    @Override
    public void sendFailureMessage() {

    }
}
