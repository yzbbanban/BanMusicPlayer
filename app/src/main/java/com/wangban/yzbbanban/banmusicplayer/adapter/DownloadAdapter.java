package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangban.yzbbanban.banmusicplayer.R;
import com.wangban.yzbbanban.banmusicplayer.entity.DownloadDoc;
import com.wangban.yzbbanban.banmusicplayer.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by YZBbanban on 16/7/21.
 */
public class DownloadAdapter extends BaseAdapter<DownloadDoc> {

    public DownloadAdapter(Context context, ArrayList<DownloadDoc> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DownloadDoc downloadDoc = (DownloadDoc) getItem(position);
        ViewHoler holer;
        MyListener myListener = null;
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.item_download, null);
            holer = new ViewHoler();
            myListener = new MyListener(position);
            holer.tv_download_music_title = (TextView) convertView.findViewById(R.id.tv_download_music_title);
            holer.tv_download_progress = (TextView) convertView.findViewById(R.id.tv_download_progress);
            holer.pb_download_progress = (ProgressBar) convertView.findViewById(R.id.pb_download_progress);
            holer.btn_download_start_or_stop = (Button) convertView.findViewById(R.id.btn_download_start_or_stop);
            convertView.setTag(holer);
        }
        holer = (ViewHoler) convertView.getTag();
        holer.tv_download_music_title.setText(downloadDoc.getTitle());
        holer.btn_download_start_or_stop.setTag("btn" + position);
        holer.pb_download_progress.setTag("pb"+position);
        holer.btn_download_start_or_stop.setOnClickListener(myListener);
        return convertView;
    }

    class ViewHoler {
        TextView tv_download_music_title;
        TextView tv_download_progress;
        ProgressBar pb_download_progress;
        Button btn_download_start_or_stop;
    }

    class MyListener implements View.OnClickListener {
        int position;

        public MyListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            ToastUtil.showToast(getContext(),"第"+position+"个");
        }
    }
}
