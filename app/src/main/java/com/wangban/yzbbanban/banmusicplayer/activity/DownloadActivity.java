package com.wangban.yzbbanban.banmusicplayer.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.wangban.yzbbanban.banmusicplayer.R;
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

public class DownloadActivity extends AppCompatActivity implements Consts, IViewDownLoad {
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

    private int currentProgress;
    private int maxProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        x.view().inject(this);

    }

    private void setData() {
//        intent2.putExtra("url", fileLink);
//        intent2.putExtra("title", songInfo.getTitle());
//        intent2.putExtra("bit", url.getFile_bitrate());
        String path = getIntent().getStringExtra("url");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File saveDir = Environment.getExternalStorageDirectory();
            download(path, saveDir);
        } else {
            ToastUtil.showToast(getApplicationContext(), "SD卡内存错误");
        }


    }

    //设置进度
    @Override
    public void setProgressMax(int progress) {
        this.maxProgress = progress;
    }

    @Override
    public void setProgressCurrent(int progress) {
        this.currentProgress = progress;
        showProgressBar(currentProgress);
    }

    private void showProgressBar(int currentProgress) {
        progressBar.setProgress(currentProgress);
    }

    //设置失败信息
    @Override
    public void sendFailureMessage() {
        ToastUtil.showToast(getApplicationContext(), "下载失败");
    }

    /**
     * 下载操作
     *
     * @param path    下载路径
     * @param saveDir 下载文件存于的目录
     */
    private void download(String path, File saveDir) {
        DownloadTask task = new DownloadTask(getApplicationContext(), saveDir, path);
        new Thread(task).start();
    }

}
