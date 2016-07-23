package com.wangban.yzbbanban.banmusicplayer.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.adapter.DownloadAdapter;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterDownload;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterDownloadImpl;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
import com.wangban.yzbbanban.banmusicplayer.util.download.DownloadTask;
import com.wangban.yzbbanban.banmusicplayer.view.IViewDownLoad;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.*;

public class DownloadActivity extends BaseDestoryActivity implements Consts, IViewDownLoad {
    @ViewInject(R.id.lv_download_list)
    private ListView listView;
    @ViewInject(R.id.btn_download_back)
    private Button btnDownloadBack;
    @ViewInject(R.id.pg_download)
    private ProgressBar progressBar;
    @ViewInject(R.id.btn_down)
    private Button btnDown;
    @ViewInject(R.id.btn_stop)
    private Button btnStop;
    @ViewInject(R.id.tv_progress)
    private TextView textView;

    private DownloadTask task;

    private List<DownloadDoc> downloadDocs;

    private DownloadAdapter adapter;
    private IPresenterDownload presenter;

    private int currentProgress;
    private int maxProgress;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:
                    int pro = msg.getData().getInt("size");
                    showProgressBar(pro);
                    if (progressBar.getProgress() == progressBar.getMax()) {
                        ToastUtil.showToast(DownloadActivity.this, "下载完成");
                        cancelNotification();
                        sendNotification("音乐下载完成", "音乐下载完成", 0, 0, false);
                    }
                    break;
                case DOWNLOAD_FAILURE:
                    ToastUtil.showToast(getApplicationContext(), "下载失败,此歌曲不提供下载");
                    break;
            }
        }
    };

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
        adapter = new DownloadAdapter(this, (ArrayList<DownloadDoc>) downloadDocs);
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
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopDownLoad();
            }
        });
        btnDownloadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                changeActivity();
                finish();
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
    }

    private void changeActivity() {
        startActivity(new Intent(DownloadActivity.this, PlayActivity.class));
        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
    }

    private void stopDownLoad() {
        task.exit();
    }

    private void startDownload() {

//        intent2.putExtra("url", fileLink);
//        intent2.putExtra("title", songInfo.getTitle());
//        intent2.putExtra("bit", url.getFile_bitrate());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            String title = getIntent().getStringExtra("title");
            String path = getIntent().getStringExtra("url");

            File saveDir = Environment.getExternalStorageDirectory();
//            LogUtil.logInfo(TAG, "saveDir: " + saveDir);
            download(path, saveDir);
        } else {
            ToastUtil.showToast(getApplicationContext(), "SD卡内存错误");
        }


    }

    //设置进度
    @Override
    public void setProgressMax(int progress) {
        this.maxProgress = progress;
        progressBar.setMax(progress);
//        LogUtil.logInfo(TAG,"MAX: "+maxProgress);
    }

    @Override
    public void setProgressCurrent(int progress) {
        this.currentProgress = progress;
//        LogUtil.logInfo(TAG,"MAX: "+currentProgress);
        Message msg = Message.obtain();
        msg.getData().putInt("size", currentProgress);
        msg.what = DOWNLOAD;
        handler.sendMessage(msg);


    }

    private void showProgressBar(int currentProgress) {
        progressBar.setProgress(currentProgress);
        int p = currentProgress * 100 / maxProgress;
        textView.setText(p + "%");
        sendNotification("", "", maxProgress, currentProgress, false);
    }

    //设置失败信息
    @Override
    public void sendFailureMessage() {
        handler.sendEmptyMessage(DOWNLOAD_FAILURE);
    }


    /**
     * 下载操作
     *
     * @param path    下载路径
     * @param saveDir 下载文件存于的目录
     */
    private void download(String path, File saveDir) {
        task = new DownloadTask(getApplicationContext(), saveDir, path, this);
        new Thread(task).start();
        sendNotification("音乐开始下载", "", 100, 0, true);
    }

    /**
     * 发送通知
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification(String ticker, String text, int max, int progress, boolean i) {
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker(ticker)
                .setContentTitle("音乐下载")
                .setContentText(text)
                .setSmallIcon(R.drawable.thanks);
        builder.setProgress(max, progress, i);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    //清除通知
    public void cancelNotification() {
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_ID);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            changeActivity();
            finish();
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
