package com.wangban.yzbbanban.banmusicplayer.activity;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;
import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
import com.wangban.yzbbanban.banmusicplayer.util.download.DownloadProgressListener;
import com.wangban.yzbbanban.banmusicplayer.util.download.DownloadTask;
import com.wangban.yzbbanban.banmusicplayer.util.download.FileDownloader;
import com.wangban.yzbbanban.banmusicplayer.view.IViewDownLoad;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

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

    private int currentProgress;
    private int maxProgress;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:
                    int pro = msg.arg1;
                    showProgressBar(pro);
                    break;
                case DOWNLOAD_FAILURE:
                    ToastUtil.showToast(getApplicationContext(), "下载失败");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        x.view().inject(this);
        setListeners();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        setData();
        super.onNewIntent(intent);
    }

    private void setListeners() {
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
        btnDownloadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopDownLoad();
            }
        });
        btnDownloadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DownloadActivity.this, PlayActivity.class));
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
    }

    private void stopDownLoad() {
        task.exit();
    }

    private void setData() {

//        intent2.putExtra("url", fileLink);
//        intent2.putExtra("title", songInfo.getTitle());
//        intent2.putExtra("bit", url.getFile_bitrate());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String title = getIntent().getStringExtra("title");
            String path = getIntent().getStringExtra("url")+"_"+title;


            File saveDir = Environment.getExternalStorageDirectory();
            LogUtil.logInfo(TAG, "saveDir: " + saveDir);
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
        msg.what = DOWNLOAD;
        msg.arg1 = currentProgress;
        handler.sendMessage(msg);

    }

    private void showProgressBar(int currentProgress) {
        progressBar.setProgress(currentProgress);
        int p = currentProgress * 100 / maxProgress;
        textView.setText(p + "%");
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
    }

}
