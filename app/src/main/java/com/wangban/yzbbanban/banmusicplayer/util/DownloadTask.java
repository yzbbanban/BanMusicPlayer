package com.wangban.yzbbanban.banmusicplayer.util;

import android.content.Context;

import com.wangban.yzbbanban.banmusicplayer.util.download.DownloadProgressListener;
import com.wangban.yzbbanban.banmusicplayer.util.download.FileDownloader;

import java.io.File;

/**
 * Created by YZBbanban on 16/7/17.
 */
public class DownloadTask implements Runnable {

    private String path;
    private File saveDir;
    private FileDownloader loader;
    private Context context;

    public DownloadTask(Context context, File saveDir, String path) {
        this.saveDir = saveDir;
        this.path = path;
        this.context = context;
    }

    public void exit() {
        if (loader != null) {
            loader.exit();
        }
    }

    DownloadProgressListener downloadProgressListener = new DownloadProgressListener() {
        @Override
        public void onDownloadSize(int size) {

        }
    };

    @Override
    public void run() {

        try {
            //实例化下载器
            loader = new FileDownloader(context, path, saveDir, 3);
            loader.download(downloadProgressListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
