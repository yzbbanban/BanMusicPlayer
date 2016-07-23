package com.wangban.yzbbanban.banmusicplayer.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.DownloadAdapter;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterDownload;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterDownloadImpl;
import com.wangban.yzbbanban.banmusicplayer.view.IViewShowDownloadList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.*;

public class DownloadActivity extends BaseDestoryActivity implements Consts, IViewShowDownloadList {
    @ViewInject(R.id.lv_download_list)
    private ListView listView;
    @ViewInject(R.id.btn_download_back)
    private Button btnDownloadBack;


    private List<DownloadDoc> downloadDocs;

    private DownloadAdapter adapter;
    private IPresenterDownload presenter;



    public DownloadActivity() {
        presenter = new PresenterDownloadImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        x.view().inject(this);
        setListeners();
        presenter.findDownloadMessage();

    }


    @Override
    public void showMessage(List<DownloadDoc> downloadDocs) {
//        Log.i(TAG, "activity_showMessage: " + downloadDocs.get(0).getTitle());
        adapter = new DownloadAdapter(this, (ArrayList<DownloadDoc>) downloadDocs,listView);
        listView.setAdapter(adapter);
    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        setIntent(intent);
//        if (intent != null) {
//            startDownload();
//        }
//        super.onNewIntent(intent);
//    }

    private void setListeners() {
        btnDownloadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
    }










    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
