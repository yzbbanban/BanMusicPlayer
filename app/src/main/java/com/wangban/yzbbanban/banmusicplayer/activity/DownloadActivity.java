package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.DownloadAdapter;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterDownload;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterDownloadImpl;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
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

    private void deleteData(int position) {
        MusicApplication.getInfo().deleteData(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(List<DownloadDoc> downloadDocs) {
//        Log.i(TAG, "activity_showMessage: " + downloadDocs.get(0).getTitle());
        adapter = new DownloadAdapter(this, (ArrayList<DownloadDoc>) downloadDocs, listView);
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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                ToastUtil.showToast(getApplicationContext(),"longClick: "+position);
                dialogDiaplay(position);

                return false;
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

    /**
     * 长按 Item 的弹出窗口
     * @param position
     */
    private void dialogDiaplay(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.delete_nomal);
        builder.setTitle("选择");
        builder.setMessage("您要删除吗");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData(position);
            }
        });
        AlertDialog dialog=builder.create();
        builder.show();
    }


}
