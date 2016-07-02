package com.wangban.yzbbanban.banmusicplayer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.Log;
import android.widget.Toast;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.consts.Consts;

/**
 * 执行下载音乐操作的Service
 */
public class DownloadService extends IntentService implements Consts {

    private static final int NOTIFICATION_ID = 100;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public DownloadService(String name) {
        super(name);
    }

    public DownloadService() {
        super("download");
    }

    /**
     * 该方法在工作线程中执行，在该方法中
     * 可以直接运行耗时代码
     * 什么时候执行该方法
     * 当调用 startService 启动当前 service 后
     * 将会把需要完成耗时操作存入消息队列
     * 该消息队列会被工作线程中的 Looper 轮循
     * 并且顺序执行
     * <p/>
     * 可以用过 intent 对象传递过来
     * intent.putExtra()
     * intent,getXXXExtra()
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        String bit = intent.getStringExtra("bit");
        try {
            String fileName = "_" + bit + "/" + title + ".mp3";
            File target = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), fileName);
            if (target.exists()) {
                Log.i(TAG, "onHandleIntent: 已经下载过了");
                return;
            }
            if (!target.getParentFile().exists()) {
                target.getParentFile().mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(target);
            sendNotification("音乐开始下载", "", 100, 0, true);
            HttpURLConnection conn= (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            InputStream is=conn.getInputStream();
            //边读边保存到文件中
            byte[] buffer=new byte[1024*100];
            int length=0;
            int current=0;
            //服务端返回的总数据量
            int total=Integer.parseInt(conn.getHeaderField("Content-Length"));
            while ((length=is.read(buffer))!=-1){
                //向文件输出流中写出相应数据
                fos.write(buffer, 0, length);
                fos.flush();
                current+=length;
                //使用通知更新进度条
                sendNotification("","",total,current,false);

            }
            fos.close();
            //重新出现通知的滚动消息
            cancelNotification();
            sendNotification("音乐下载完成","音乐下载完成",0,0,false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cancelNotification() {
        NotificationManager manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manger.cancel(NOTIFICATION_ID);
    }

    /**
     * 发送通知
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification(String ticker, String text, int max, int progress, boolean i) {
        NotificationManager manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Builder(this);
        builder.setTicker(ticker).setContentTitle("音乐下载")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_laba);
        builder.setProgress(max, progress, i);
        manger.notify(NOTIFICATION_ID,builder.build());


    }
}



