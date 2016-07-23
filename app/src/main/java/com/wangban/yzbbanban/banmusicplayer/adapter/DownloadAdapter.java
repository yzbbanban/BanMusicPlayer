package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.activity.DownloadActivity;
import com.wangban.yzbbanban.banmusicplayer.app.MusicApplication;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.presenter.IPresenterDownload;
import com.wangban.yzbbanban.banmusicplayer.presenter.impl.PresenterDownloadImpl;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;
import com.wangban.yzbbanban.banmusicplayer.util.download.DownloadTask;
import com.wangban.yzbbanban.banmusicplayer.view.IViewDownLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.wangban.yzbbanban.banmusicplayer.consts.Consts.DOWNLOAD;
import static com.wangban.yzbbanban.banmusicplayer.consts.Consts.DOWNLOAD_FAILURE;
import static com.wangban.yzbbanban.banmusicplayer.consts.Consts.MY_APP;
import static com.wangban.yzbbanban.banmusicplayer.consts.Consts.NOTIFICATION_ID;

/**
 * Created by YZBbanban on 16/7/21.
 */
public class DownloadAdapter extends BaseAdapter<DownloadDoc> implements IViewDownLoad {
    private Context context;
    private IPresenterDownload presenter;
    private DownloadTask task;
    private ViewHoler holer;

    private ListView listView;
    private int currentProgress;
    private int maxProgress;
    private int currentPosition;
    private ProgressBar pb;


    //设置传递下载数据的 presenter
    private IPresenterDownload presenterDownload;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:
                    int pro = msg.getData().getInt("size");
                    showProgressBar(pro, currentPosition);
                    if (pb.getProgress() == pb.getMax()) {
                        ToastUtil.showToast(context, "下载完成");
//                        cancelNotification();
//                        sendNotification("音乐下载完成", "音乐下载完成", 0, 0, false);
                    }
                    break;
                case DOWNLOAD_FAILURE:
                    ToastUtil.showToast(context, "下载失败,此歌曲不提供下载");
                    break;
            }
        }
    };

    public DownloadAdapter(Context context, ArrayList<DownloadDoc> data, ListView listView) {
        super(context, data);
        this.context = context;
        this.listView = listView;
        this.presenterDownload = new PresenterDownloadImpl(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DownloadDoc downloadDoc = (DownloadDoc) getItem(position);

        MyListener myListener = null;
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.item_download, null);
            holer = new ViewHoler();
            myListener = new MyListener(position);
            holer.tv_download_music_title = (TextView) convertView.findViewById(R.id.tv_download_music_title);
            holer.tv_download_progress = (TextView) convertView.findViewById(R.id.tv_download_progress);
            holer.pb_download_progress = (ProgressBar) convertView.findViewById(R.id.pb_download_progress);
            holer.btn_download_start = (Button) convertView.findViewById(R.id.btn_download_start);
            holer.btn_download_stop = (Button) convertView.findViewById(R.id.btn_download_stop);
            holer.ibtn_download_delete= (ImageButton) convertView.findViewById(R.id.ibtn_download_delete);

            holer.tv_download_progress.setTag("tv" + position);
            holer.btn_download_start.setTag("btn_start" + position);
            holer.btn_download_stop.setTag("btn_stop" + position);
            holer.pb_download_progress.setTag("pb" + position);
            holer.ibtn_download_delete.setTag("ibtn" + position);

            holer.btn_download_start.setOnClickListener(myListener);
            holer.btn_download_stop.setOnClickListener(myListener);
            holer.ibtn_download_delete.setOnClickListener(myListener);

            convertView.setTag(holer);
        }
        holer = (ViewHoler) convertView.getTag();
        holer.tv_download_music_title.setText(downloadDoc.getTitle());


        return convertView;
    }

    class ViewHoler {
        TextView tv_download_music_title;
        TextView tv_download_progress;
        ProgressBar pb_download_progress;
        Button btn_download_start;
        Button btn_download_stop;
        ImageButton ibtn_download_delete;
    }

    class MyListener implements View.OnClickListener {
        int position;

        public MyListener(int position) {
            this.position = position;
            currentPosition = position;
        }

        //TODO
        @Override
        public void onClick(View v) {
            Button btnStart = (Button) listView.findViewWithTag("btn_start" + position);
            Button btnStop = (Button) listView.findViewWithTag("btn_stop" + position);
            switch (v.getId()) {
                case R.id.btn_download_start:
//                    ToastUtil.showToast(getContext(), "第" + position + "个");

                    DownloadDoc doc = ((DownloadDoc) getItem(position));
                    startDownload(doc.getUrl());

                    ToastUtil.showToast(context, doc.getTitle() + "\n" + doc.getUrl());
//
//                  Log.i("supergirl", "onClick: "+doc.getUrl());
                    btnStart.setEnabled(false);
                    btnStop.setEnabled(true);
                    break;
                case R.id.btn_download_stop:
                    stopDownLoad();
                    ToastUtil.showToast(getContext(), "第" + position + "个停止");
                    btnStart.setEnabled(true);
                    btnStop.setEnabled(false);
                    break;
                case R.id.ibtn_download_delete:

                    break;

            }
        }
    }

    public void setPresenter(IPresenterDownload presenter) {
        this.presenter = presenter;
    }

    //TODO
    private void startDownload(String url) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File saveDir = Environment.getExternalStorageDirectory();
            download(url, saveDir);
        } else {
            ToastUtil.showToast(context, "SD卡内存错误");
        }


    }

    private void stopDownLoad() {
        task.exit();
    }

    //TODO
    private void showProgressBar(int currentPosition, int currentProgress) {
        pb = (ProgressBar) listView.findViewWithTag("pb" + currentPosition);
        holer.pb_download_progress.setProgress(currentProgress);
        int p = currentProgress * 100 / maxProgress;
        TextView tv = (TextView) listView.findViewWithTag("tv" + currentPosition);
        tv.setText(p + "%");
//        sendNotification("", "", maxProgress, currentProgress, false);
    }
    //TODO

    /**
     * 下载操作
     *
     * @param path    下载路径
     * @param saveDir 下载文件存于的目录
     */
    private void download(String path, File saveDir) {
        task = new DownloadTask(context, saveDir, path, this);
        new Thread(task).start();
//        sendNotification("音乐开始下载", "", 100, 0, true);
    }

    //设置进度
    @Override
    public void setProgressMax(int progress) {
        this.maxProgress = progress;
        pb.setMax(progress);
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

    //设置失败信息
    @Override
    public void sendFailureMessage() {
        handler.sendEmptyMessage(DOWNLOAD_FAILURE);
    }

//    /**
//     * 发送通知
//     */
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    public void sendNotification(String ticker, String text, int max, int progress, boolean i) {
//        NotificationManager manager = (NotificationManager)
//                MusicApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
//        Notification.Builder builder = new Notification.Builder(MusicApplication.getContext());
//        builder.setTicker(ticker)
//                .setContentTitle("音乐下载")
//                .setContentText(text)
//                .setSmallIcon(R.drawable.thanks);
//        builder.setProgress(max, progress, i);
//        manager.notify(NOTIFICATION_ID, builder.build());
//    }
//
//    //清除通知
//    public void cancelNotification() {
//        NotificationManager manager = (NotificationManager)
//                MusicApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
//        manager.cancel(NOTIFICATION_ID);
//    }


}
