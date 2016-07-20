package com.wangban.yzbbanban.banmusicplayer.util.download;

import android.content.Context;

import com.wangban.yzbbanban.banmusicplayer.util.LogUtil;
import com.wangban.yzbbanban.banmusicplayer.view.IViewDownLoad;

import java.io.File;

/**
 * Created by YZBbanban on 16/7/17.
 */
public class DownloadTask implements Runnable {

    private String path;
    private File saveDir;
    private FileDownloader loader;
    private Context context;
    private IViewDownLoad view;

    public DownloadTask(Context context, File saveDir, String path,IViewDownLoad view) {
        this.saveDir = saveDir;
        this.path = path;
        this.context = context;
        this.view= view;
    }



    public void exit() {
        if (loader != null) {
            loader.exit();
        }
    }

    @Override
    public void run() {

        try {
            //实例化下载器
//            LogUtil.logInfo("supergirl","Task_path: "+path);
            loader = new FileDownloader(context, path, saveDir, 3);
            view.setProgressMax(loader.getFileSize());
            loader.download(new DownloadProgressListener() {
                @Override
                public void onDownloadSize(int size) {
                    view.setProgressCurrent(size);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            view.sendFailureMessage();
        }

    }
}
